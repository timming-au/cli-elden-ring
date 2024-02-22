package game.actors.friendlies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.dialogues.Dialogue;
import game.dialogues.DialogueAction;
import game.dialogues.dialogueLibrary.HuTaoDialogue;
import game.enums.ActorType;

/**
 * Hu Tao 👻
 * @author Wee Jun Lin
 */
public class HuTao extends Actor {
    private final Dialogue dialogue = new HuTaoDialogue();
    public HuTao() {
        super("Hu Tao", '胡', 9999);
        this.addCapability(ActorType.FRIENDLY);
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);
        actions.add(new DialogueAction(dialogue, this));
        return actions;
    }

    /**
     * Select and return an action to perform on the current turn.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }
}
