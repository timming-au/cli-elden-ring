package game.dialogues;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import utils.FancyMessage;

import java.util.List;
import java.util.Scanner;

/**
 * Dialogue System class. Handles the operations of a full Dialogue.
 * @author Kok Tim Ming
 */
public class DialogueSystem {
    private Dialogue currentDialogue;
    private final Actor initiator;
    private final Actor other;  // should be used in the future

    /**
     * Constructor.
     * @param initiator Actor initiating the Dialogue
     * @param other Actor providing the Dialogue
     * @param startingDialogue Dialogue to start from
     */
    public DialogueSystem(Actor initiator, Actor other, Dialogue startingDialogue) {
        this.initiator = initiator;
        this.other = other;
        this.currentDialogue = startingDialogue;
    }

    /**
     * Starts the dialogue
     */
    public void start() {
        new Display().println(FancyMessage.DIALOGUE_START);
        while (currentDialogue != null) {
            displayMessage();
            displayChoices();
            int choiceIndex = getUserChoice();
            currentDialogue = currentDialogue.choose(initiator, choiceIndex);
        }
        new Display().println(System.lineSeparator() + FancyMessage.DIALOGUE_END);
    }

    private void displayMessage() {
        new Display().println(System.lineSeparator() + currentDialogue.getMessage());
    }

    private void displayChoices() {
        List<Choice> choices = currentDialogue.getAvailableChoices(initiator);
        for (int i = 0; i < choices.size(); i++) {
            new Display().println((i + 1) + ". " + choices.get(i).description());
        }
    }

    private int getUserChoice() {
        new Display().print("Enter your choice: ");
        return new Scanner(System.in).nextInt() - 1;
    }
}