package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.behaviors.AttackBehaviour;
import game.behaviors.WanderBehaviour;
import game.dialogues.Dialogue;
import game.dialogues.DialogueAction;
import game.dialogues.dialogueLibrary.ShutenDoujiDialogue;
import game.enums.ActorType;
import game.enums.NPCBehavior;
import game.enums.Status;

/**
 * Mazoku. Will roam attack any non-friendly.
 * @author Kok Tim Ming
 */
public class ShutenDouji extends Enemy {
    private final Dialogue dialogue = new ShutenDoujiDialogue();
    /**
     * Constructor.
     */
    public ShutenDouji() {
        super("Shuten Douji", '鬼', 9999);
        this.addCapability(NPCBehavior.NEUTRAL);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.behaviours.add(new AttackBehaviour(ActorType.FRIENDLY));
        this.behaviours.add(new WanderBehaviour());
    }

    /**
     * Select and return an action to perform on the current turn.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor1
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return getBehavioralAction(map);
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);
        actions.add(new DialogueAction(dialogue, this));
        return actions;
    }

    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(99999,"mutilates", 100);
    }
}
