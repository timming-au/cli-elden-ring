package game.dialogues;

import edu.monash.fit2099.engine.actors.Actor;

import java.util.ArrayList;
import java.util.List;

/**
 * Dialogue class. Enables choice-based dialogues
 * @author Kok Tim Ming
 */
public class Dialogue {
    private final String message;
    private final List<Choice> choices;

    /**
     * Constructor
     * @param message Dialogue message to display
     */
    public Dialogue(String message) {
        this.message = message;
        choices = new ArrayList<>();
    }

    /**
     * Returns the dialogue message
     * @return dialogue message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Adds a choice to the Dialogue.
     * @param choice Choice to add to Dialogue
     */
    public void addChoice(Choice choice) {
        choices.add(choice);
    }

    /**
     * Returns the list of choices attached to the Dialogue.
     * @return list of choices attached to the Dialogue
     */
    public List<Choice> getChoices() {
        return choices;
    }

    /**
     * Returns the list of available choices attached to the Dialogue.
     * @return list of available choices attached to the Dialogue
     */
    public List<Choice> getAvailableChoices(Actor actor){
        List<Choice> availableChoices = new ArrayList<>();
        for(Choice choice: choices){
            if(choice.available()){
                boolean available = true;
                for(Enum<?> capability: choice.conditions()){
                    if(!actor.hasCapability(capability)){
                        available = false;
                        break;
                    }
                }
                if(available) availableChoices.add(choice);
            }
        }
        return availableChoices;
    }

    /**
     * Choose a choice and execute, returning the next Dialogue
     * @param initiator Actor executing the choice
     * @param choiceIndex Choice number selected
     * @return The next Dialogue
     */
    public Dialogue choose(Actor initiator, int choiceIndex) {
        List<Choice> availableChoices = getAvailableChoices(initiator);
        if (choiceIndex >= 0 && choiceIndex < availableChoices.size()) {
            return availableChoices.get(choiceIndex).execute(initiator);
        } else {
            return null;
        }
    }
}