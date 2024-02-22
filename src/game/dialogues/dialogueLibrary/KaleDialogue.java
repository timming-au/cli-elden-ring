package game.dialogues.dialogueLibrary;

import game.dialogues.Choice;
import game.dialogues.Dialogue;

/**
 * Kale's dialogue
 * @author Wee Jun Lin
 * Dialogues modified from: Elden Ring Wiki
 */
public class KaleDialogue extends Dialogue {

    /**
     * Constructor.
     */
    public KaleDialogue() {
        super("You are a Tarnished, I can see it. And I can also see... That you're not after my throat.\n " +
                "Then why not purchase a little something? I am Kale, Purveyor of fine goods. What do you buy?");
        initialise();
    }

    private void initialise(){

        // Dialogues
        Dialogue recommendPurchase = new Dialogue("I would recommend purchasing the Uchigatana or Great Knife. Both are formidable weapons.");

        Dialogue tellMeAboutYourself = new Dialogue("I am of a nomadic people. Selling wares as I travel. The land has been tainted by madness since the shattering of the Elden Ring.\nIt's only Tarnished like yourself who keep things from drying up entirely. Let's say you're a very welcome customer.\nWhat else would you like to know about?");

        // Choices
        Choice cRecommendPurchase = new Choice("What do you recommend me to purchase from your store?", recommendPurchase);
        Choice cTellMeAboutYourself = new Choice("Tell me about yourself.", tellMeAboutYourself);

        Choice cGoodbye = new Choice("Goodbye.", null);


        // link Dialogue and Choices
        this.addChoice(cRecommendPurchase);
        this.addChoice(cTellMeAboutYourself);
        this.addChoice(cGoodbye);

        recommendPurchase.addChoice(cTellMeAboutYourself);
        recommendPurchase.addChoice(cGoodbye);

        tellMeAboutYourself.addChoice(cRecommendPurchase);
        tellMeAboutYourself.addChoice(cGoodbye);

    }
}


