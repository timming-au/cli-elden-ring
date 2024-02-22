package game.dialogues;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.capabilities.CapabilitySet;
import edu.monash.fit2099.engine.displays.Display;
import game.dialogues.effects.DialogueEffect;

import java.util.ArrayList;
import java.util.List;

/**
 * Choice class. Provides an option to branch into a different (or same) Dialogue, with side effects.
 * @author Kok Tim Ming
 */
public class Choice {
    private final String description;
    private List<DialogueEffect> effects = new ArrayList<>();
    private final Dialogue toDialogue;
    private CapabilitySet conditions = new CapabilitySet();

    private boolean available = true;

    /**
     * Constructor.
     * @param description Description of the Choice
     * @param effect effect that will be triggered when Choice is selected
     * @param toDialogue Dialogue to go to when Choice is selected
     */
    public Choice(String description, DialogueEffect effect, Dialogue toDialogue) {
        this.description = description;
        if(effect != null){
            this.effects.add(effect);
        }
        this.toDialogue = toDialogue;
    }

    /**
     * Constructor.
     * @param description Description of the Choice
     * @param effect effect that will be triggered when Choice is selected
     * @param toDialogue Dialogue to go to when Choice is selected
     * @param conditions CapabilitySet of conditions the initiator must have for this choice to be available
     */
    public Choice(String description, DialogueEffect effect, Dialogue toDialogue, CapabilitySet conditions) {
        this.description = description;
        if(effect != null){
            this.effects.add(effect);
        }
        for(Enum<?> condition: conditions.capabilitiesList()){
            this.conditions.addCapability(condition);
        }
        this.toDialogue = toDialogue;
    }

    /**
     * Constructor.
     * @param description Description of the Choice
     * @param effects List of effects that will be triggered when Choice is selected
     * @param toDialogue Dialogue to go to when Choice is selected
     */
    public Choice(String description, List<DialogueEffect> effects, Dialogue toDialogue) {
        this.description = description;
        this.effects = effects;
        this.toDialogue = toDialogue;
    }

    /**
     * Constructor.
     * @param description Description of the Choice
     * @param effects List of effects that will be triggered when Choice is selected
     * @param toDialogue Dialogue to go to when Choice is selected
     * @param conditions CapabilitySet of conditions the initiator must have for this choice to be available
     */
    public Choice(String description, List<DialogueEffect> effects, Dialogue toDialogue, CapabilitySet conditions) {
        this.description = description;
        this.effects = effects;
        this.toDialogue = toDialogue;
        for(Enum<?> condition: conditions.capabilitiesList()){
            this.conditions.addCapability(condition);
        }
    }

    /**
     * Constructor.
     * @param description Description of the Choice
     * @param toDialogue Dialogue to go to when Choice is selected
     */
    public Choice(String description, Dialogue toDialogue) {
        this.description = description;
        this.toDialogue = toDialogue;
    }

    /**
     * Constructor.
     * @param description Description of the Choice
     * @param toDialogue Dialogue to go to when Choice is selected
     * @param conditions CapabilitySet of conditions the initiator must have for this choice to be available
     */
    public Choice(String description, Dialogue toDialogue, CapabilitySet conditions) {
        this.description = description;
        this.toDialogue = toDialogue;
        for(Enum<?> condition: conditions.capabilitiesList()){
            this.conditions.addCapability(condition);
        }
    }

    /**
     * Sets the availability of the choice.
     * @param available availability of the choice
     */
    public void setAvailable(boolean available){
        this.available = available;
    }

    /**
     * Gets the availability of the choice.
     * @return availability of the choice
     */
    public boolean available(){
        return this.available;
    }

    /**
     * Returns the description of the choice
     * @return the description of the choice
     */
    public String description() {
        return description;
    }

    public List<Enum<?>> conditions(){
        return conditions.capabilitiesList();
    }

    /**
     * Execute the choice
     * @param actor Actor executing the choice
     * @return the next Dialogue
     */
    public Dialogue execute(Actor actor) {
        if(effects != null){
            for (DialogueEffect effect: effects){
                if(effect.description(actor) != null){
                    new Display().println(effect.description(actor));
                }
                effect.trigger(actor);
            }
        }
        return toDialogue;
    }
}
