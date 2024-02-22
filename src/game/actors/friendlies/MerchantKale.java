package game.actors.friendlies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.dialogues.Dialogue;
import game.dialogues.DialogueAction;
import game.dialogues.dialogueLibrary.KaleDialogue;
import game.enums.ActorType;
import game.enums.Capability;
import game.actions.transActions.PurchaseAction;
import game.weapons.Club;
import game.weapons.GreatKnife;
import game.weapons.Uchigatana;

/**
 * Merchant Kale.
 * @author Kok Tim Ming
 * Modified by: Wee Jun Lin
 */
public class MerchantKale extends Actor {
    /**
     * Constructor.
     */
    private final Dialogue dialogue = new KaleDialogue();
    public MerchantKale() {
        super("Merchant Kale", 'k', 9999);
        this.addCapability(Capability.TRANSACTABLE);
        this.addCapability(ActorType.FRIENDLY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        actions.add(new PurchaseAction(this, new Uchigatana()));
        actions.add(new PurchaseAction(this, new Club()));
        actions.add(new PurchaseAction(this, new GreatKnife()));
        actions.add(new DialogueAction(dialogue, this));
        return actions;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }
}
