package game.actors.friendlies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.ResetManager;
import game.enums.*;
import game.actions.attackActions.AttackAction;
import game.actions.actorActions.ResetAction;
import game.economy.runes.manager.RuneManager;
import game.actors.archetypes.Archetype;
import game.Resettable;
import game.items.FlaskOfCrimsonTears;
import game.items.RemembranceOfTheGrafted;
import utils.FancyMessage;

/**
 * Class representing the Player. It implements the Resettable interface.
 * It carries around a club to attack a hostile creature in the Lands Between.
 * Modified by: Kok Tim Ming
 * @author Adrian Kristanto
 */
public class Player extends Actor implements Resettable{

	private final Menu menu = new Menu();
	private Archetype archetype;

	private Location currentLocation;
	private Location respawnLocation;

	/**
	 * Constructor. Overrides archetype hitPoints, can be used for debugging, or for
	 * other game mechanics in the future.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 * @param archetype   Archetype to initialise player with
	 */
	public Player(String name, char displayChar, int hitPoints, Archetype archetype, Location spawnLocation) {
		super(name, displayChar, hitPoints);
		initPlayer(archetype,spawnLocation);
	}

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param archetype   Archetype to initialise player with
	 */
	public Player(String name, char displayChar, Archetype archetype, Location spawnLocation) {
		super(name, displayChar, archetype.hitPoints());
		initPlayer(archetype,spawnLocation);
	}

	/**
	 * Helper method to initialise player
	 * @param archetype Archetype to initialise player with
	 */
	private void initPlayer(Archetype archetype, Location spawnLocation){
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(ActorType.FRIENDLY);
		this.addCapability(Capability.TRANSACTABLE);
		this.addCapability(NPCBehavior.PROVOKER);
		this.addCapability(Capability.GRACE_RESTABLE);
		this.addCapability(ActorType.PLAYABLE);
		// initialise archetypes, save as attribute to increase max HP based on archetype (composition)
		this.archetype = archetype;
		this.addWeaponToInventory(this.archetype.weapon());
		this.addItemToInventory(new FlaskOfCrimsonTears());
		this.addItemToInventory(new RemembranceOfTheGrafted());

		this.respawnLocation = spawnLocation;
		this.currentLocation = spawnLocation;

		// register in runeManager
		RuneManager.getInstance().collector().register(this);

		// hack
		RuneManager.getInstance().updateRune(this,10000);
		ResetManager.getInstance().register(this);
	}

	/**
	 * Prints out current Player state by: name, HP, max HP, runes
	 */
	public void displayStatus(){
		System.out.format("%s (%d/%d) %s\n",this.name,this.hitPoints,this.maxHitPoints,RuneManager.getInstance().collector().getRune(this));
	}

	/**
	 * Display this class's status, try to revive, then displays menu of actions.
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		displayStatus();

		// try to revive player
		if(this.hasCapability(Status.DEATH_INBOUND)){
			return enterRevival(map);
		}

		// only save current location now, so when enter revival, it is the previous location
		currentLocation = map.locationOf(this);

		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();
		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
		ActionList actions = new ActionList();
		if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
			// add intrinsic weapon
			actions.add(new AttackAction(this, direction));

			// for every weapon get skill, then add to possible actions
			for(WeaponItem weapon : otherActor.getWeaponInventory()){
				actions.add(weapon.getSkill(this, direction));
			}
		}
		return actions;
	}

	/**
	 * Hurt method. Revivables will cheat death, as shown in example.
	 * @param points number of hitpoints to deduct.
	 */
	@Override
	public void hurt(int points) {
		super.hurt(points);
		if(hitPoints <= 0){
			hitPoints = 1;
			this.addCapability(Status.DEATH_INBOUND);
		}
	}

	/**
	 * Moves Player to respawnLocation and heals to max HP
	 * @return A string related to reviving
	 */
	public String revive() {
		if(!respawnLocation.containsAnActor()){
			respawnLocation.map().moveActor(this, respawnLocation);
		}
		// call revivable revive
		this.heal(this.getMaxHp());
		this.removeCapability(Status.DEATH_INBOUND);
		return "Bearing plot armor, Tarnished revives.";
	}

	/**
	 * Enters Actor revival state
	 * @param map GameMap of actor
	 * @return Action
	 */
	public Action enterRevival(GameMap map) {
		for (String line : FancyMessage.YOU_DIED.split("\n")) {
			new Display().println(line);
			try {
				Thread.sleep(200);
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
		RuneManager.getInstance().droppedRune().clear();
		new ResetAction().execute(this,map);

		// Drop rune
		RuneManager.getInstance().dropRuneItem(this,currentLocation);
		this.revive();
		return new DoNothingAction();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void reset() {
		this.heal(this.getMaxHp());
	}

}
