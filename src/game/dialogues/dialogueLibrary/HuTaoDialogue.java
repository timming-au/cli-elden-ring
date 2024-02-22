package game.dialogues.dialogueLibrary;

import game.dialogues.Choice;
import game.dialogues.Dialogue;
import game.dialogues.effects.ToggleChoice;
import game.dialogues.effects.GiveHeal;
import java.util.Arrays;
/**
 * Hu Tao's dialogue 👻
 * @author Wee Jun Lin
 * Dialogues modified from: Genshin Impact Wiki
 */
public class HuTaoDialogue extends Dialogue {

    /**
     * Constructor
     */
    public HuTaoDialogue() {
        super("Where am I? Have I finally journeyed into the after-life?.");
        initialise();
    }
    private void initialise(){

        // Dialogues
        Dialogue singing = new Dialogue("♪Silly-churl,\nbilly-churl,\nsilly-billy hilichurl...\nWoooh~♪");

        Dialogue aboutOne = new Dialogue("Oh, you didn't know? I'm the 77th Director of the Wangsheng Funeral Parlor, Hu Tao.\nBut where am I? This sure doesn't look like anywhere within Teyvat.");
        Dialogue aboutTwo = new Dialogue("Oh, don't you worry. I'm no stranger to danger. In fact, I thrive in the face of it!\nBesides, I've brought my own fiery tricks and unyielding determination.");
        Dialogue gratitude = new Dialogue("Thank you warrior! Let me heal you as a token of gratitude.");

        // Choices
        Choice cSinging = new Choice("*Say nothing*", singing);
        Choice cAboutTwo = new Choice("You're in the Lands Between where powerful beings and ancient horrors roam these lands.",aboutTwo);
        Choice cGratitude = new Choice("Alright then... Be vigilant and may The Great Erdtree guide you.",gratitude);
        Choice cThank = new Choice("Thank you.", Arrays.asList(new GiveHeal(500), new ToggleChoice(cAboutTwo,false)), null);

        Choice cAboutOne = new Choice("Who are you and why are you here?", aboutOne);

        Choice cGoodbye = new Choice("Goodbye.",null);


        // link Dialogue and Choices
        this.addChoice(cSinging);
        this.addChoice(cAboutOne);
        this.addChoice(cGoodbye);

        aboutOne.addChoice(cAboutTwo);
        aboutOne.addChoice(cGoodbye);

        aboutTwo.addChoice(cGratitude);
        aboutTwo.addChoice(cGoodbye);

        gratitude.addChoice(cThank);
        gratitude.addChoice(cGoodbye);

        singing.addChoice(cAboutOne);
        singing.addChoice(cGoodbye);
    }
}
