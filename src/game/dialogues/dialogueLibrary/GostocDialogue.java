package game.dialogues.dialogueLibrary;

import game.dialogues.Choice;
import game.dialogues.Dialogue;
import game.dialogues.effects.ToggleChoice;
import game.dialogues.effects.GiveItem;
import game.items.FlaskOfCrimsonTears;

import java.util.Arrays;

/**
 * Kale's dialogue
 * @author Wee Jun Lin
 * Dialogues modified from: Elden Ring Wiki
 */
public class GostocDialogue extends Dialogue {
    public GostocDialogue() {
        super("You're Tarnished, aren't you?\n" +
                "I would advise against taking the main gate into the castle.It's tightly guarded by hardened old hands. Try the opening right here. The guards don't know about it. You'll breach the castle undetected.");
        initialise();
    }
    private void initialise(){

        // Dialogues
        Dialogue freeStuff1 = new Dialogue("Yes, that's the spirit...You're just the kind of Tarnished that I like. I pray for your success.");
        Dialogue freeStuff2 = new Dialogue("I've come into some fine goods. Only, it turns out I can't use a one of them. Perhaps you'd like to take them off my hands?");
        Dialogue freeStuff3 = new Dialogue("Here you go. A gift of rejuvenation.");
        Dialogue freeStuff4 = new Dialogue("You're welcome, Tarnished.");

        Dialogue tellMeAboutCastle = new Dialogue("Stormveil Castle, it perches atop a mighty cliff, gazing down upon Limgrave. Once ruled by an old king, back in the days when the true storm raged. Nowadays, it's the stronghold of the Demigod Godrick the Grafted, a fearsome presence indeed.\nThe castle stands fortified, its gates guarded by weathered veterans who have seen it all. But fret not, dear Tarnished, for I've got a trick up my sleeve.\nThere's an opening, a secret passage, right here. The guards know nothing of it. Through there, you'll penetrate the castle's heart, undetected.");

        // Choices
        Choice cFreeStuff1 = new Choice("Very well.",  freeStuff1);
        Choice cFreeStuff2 = new Choice("Do you have anything you can give me?",freeStuff2);
        Choice cFreeStuff3 = new Choice("Sure thing.",freeStuff3);
        Choice cFreeStuff4 = new Choice("Thank you", Arrays.asList(new GiveItem(new FlaskOfCrimsonTears()), new ToggleChoice(cFreeStuff1, false)), freeStuff4);
        Choice cTellMeAboutCastle = new Choice("What can you tell me about the Stormveil Castle?",  tellMeAboutCastle);
        Choice cGoodbye = new Choice("Goodbye.",  null);


        // link Dialogue and Choices
        this.addChoice(cFreeStuff1);
        this.addChoice(cTellMeAboutCastle);
        this.addChoice(cGoodbye);

        tellMeAboutCastle.addChoice(cFreeStuff1);
        tellMeAboutCastle.addChoice(cGoodbye);

        freeStuff1.addChoice(cFreeStuff2);
        freeStuff2.addChoice(cFreeStuff3);

        freeStuff3.addChoice(cFreeStuff4);
        freeStuff4.addChoice(cGoodbye);
    }
}
