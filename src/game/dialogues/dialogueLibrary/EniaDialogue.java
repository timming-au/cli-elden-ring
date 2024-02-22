package game.dialogues.dialogueLibrary;

import game.dialogues.Choice;
import game.dialogues.Dialogue;
import game.dialogues.effects.ToggleChoice;
import game.dialogues.effects.GiveRune;

import java.util.Arrays;

/**
 * Enia's dialogue
 * @author Wee Jun Lin
 * Dialogues modified from: Elden Ring Wiki
 */
public class EniaDialogue extends Dialogue {

    /**
     * Constructor.
     */
    public EniaDialogue() {
        super("Are you the new Tarnished? You've done well. I am Enia, the Finger Reader. It is my duty to interpret the fates and guide those who seek answers. How may I assist you today?");
        initialise();
    }

    private void initialise(){

        // Dialogues
        Dialogue queenMarika = new Dialogue("Queen Marika is the vessel of the Elden Ring, carrier of its vision. A god, in truth. But after the Elden Ring's shattering, she was imprisoned in the Erdtree. A grim punishment for shattering the Order, despite her godhood. The Fingers speak... Marika's trespass demanded a heavy sentence. But even in shackles, she remains a god, and the vision's vessel. Confer Great Runes to become Elden Lord, and join Queen Marika as her consort. The Fingers have willed it so.");

        Dialogue greatRunes = new Dialogue("Ahh, Great Runes are the stuff of demigods: the children of the goddess, Queen Marika. She who is vessel of the Elden Ring. Tainted by the strength of their runes, her children warred, but none could become Elden Lord. And so grace was extended, to your kind, the Tarnished.\nWhat else would you like to know about?");

        Dialogue freeStuffOne = new Dialogue("Ah, I understand your desire for aid on your journey. However, as the Finger Reader, my purpose is to offer guidance and insight rather than material possessions. I can offer you knowledge, wisdom, and advice to help you overcome challenges and discover your true path. Is there a specific question or aspect of your journey you would like assistance with?");

        Dialogue freeStuffTwo = new Dialogue("I understand your persistence, but I must reiterate that my role as the Finger Reader is to provide guidance and wisdom rather than material possessions. Elden Ring is a realm where the journey and the challenges faced hold great significance.");

        Dialogue freeStuffThree = new Dialogue("Ah, I sense the unwavering determination within you. While I cannot provide physical goods directly, I have a special gift for you. Close your eyes and extend your hand.\n*Enia places her hand gently on yours, and you feel a surge of energy coursing through your fingertips. As you open your eyes, you find yourself holding a small pouch shimmering with mystical light.*\nInside this pouch, you shall find 400 enchanted runes. These runes possess latent power and can be utilized to enhance your abilities and aid you on your journey. Each rune is a symbol of strength and resilience.\nMay these runes guide you on your path and serve as a reminder of the resilience within your spirit. Safe travels, blessed one.");


        // Choices
        Choice cGreatRunes = new Choice("What can you tell me about the Great Runes?", greatRunes);
        Choice cQueenMarika = new Choice("What can you tell me about Queen Marika?", queenMarika);
        Choice cFreeStuffOne = new Choice("Can you give me free stuff?", freeStuffOne);
        Choice cFreeStuffTwo = new Choice("But I still want free goods!", freeStuffTwo);
        Choice cFreeStuffThree = new Choice("But I am lacking even the essential resources to accept your guidance...", Arrays.asList(new GiveRune(400), new ToggleChoice(cFreeStuffOne, false)), freeStuffThree);
        Choice cThankGoodbye = new Choice("Thank you and goodbye.", null);
        Choice cGoodbye = new Choice("Goodbye.", null);


        // link Dialogue and Choices
        this.addChoice(cGreatRunes);
        this.addChoice(cQueenMarika);
        this.addChoice(cFreeStuffOne);
        this.addChoice(cGoodbye);

        queenMarika.addChoice(cGreatRunes);
        queenMarika.addChoice(cGoodbye);

        greatRunes.addChoice(cQueenMarika);
        greatRunes.addChoice(cGoodbye);

        freeStuffOne.addChoice(cQueenMarika);
        freeStuffOne.addChoice(cGreatRunes);
        freeStuffOne.addChoice(cFreeStuffTwo);

        freeStuffTwo.addChoice(cQueenMarika);
        freeStuffTwo.addChoice(cGreatRunes);
        freeStuffTwo.addChoice(cFreeStuffThree);

        freeStuffThree.addChoice(cThankGoodbye);
    }
}
