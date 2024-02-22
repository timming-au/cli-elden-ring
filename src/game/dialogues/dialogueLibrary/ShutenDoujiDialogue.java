package game.dialogues.dialogueLibrary;

import edu.monash.fit2099.engine.capabilities.CapabilitySet;
import game.dialogues.Choice;
import game.dialogues.Dialogue;
import game.dialogues.effects.GiveWeapon;
import game.dialogues.effects.InflictDamage;
import game.dialogues.effects.ToggleChoice;
import game.enums.Status;
import game.weapons.Chisui;

import java.util.Arrays;
/**
 * Shuten Douji's dialogue
 * @author Kok Tim Ming
 */
public class ShutenDoujiDialogue extends Dialogue {
    /**
     * Constructor.
     */
    public ShutenDoujiDialogue() {
        super("Ah, another pitiful mortal who recognizes the darkness that courses through my veins. Your fear is well-placed, for I am the embodiment of terror and chaos. What brings you before me, trembling and full of curiosity?");
        initialise();
    }

    private void initialise(){

        // Dialogues
        Dialogue comprehendOne = new Dialogue("(Eyes gleaming with malice) You wish to comprehend the true extent of my malevolence? Know this: I revel in chaos and revelry, thriving on the misery and suffering of others. My powers are derived from the darkest corners of the underworld, fueled by the anguish and fear of those unfortunate enough to cross my path.");

        Dialogue comprehendTwo = new Dialogue("Ah, the trembles of a mortal soul, embracing the darkness yet still clinging to a flicker of defiance. Ponder all you want, for in the end, the lure of evil is irresistible. Your soul shall make a delectable addition to my collection.");

        Dialogue pleadOne = new Dialogue("A just cause, you say? Mortals often hide their true intentions behind such noble words. How can I trust that you will not wield this weapon for nefarious purposes?");

        Dialogue pleadTwo = new Dialogue("Very well. The sword shall be placed in your hands, but remember, mortal, you carry a great burden. Do not disappoint me.");

        Dialogue disrespected = new Dialogue("(Eyes narrowing with anger) Foolish mortal, your insolence knows no bounds. I have witnessed the demise of countless arrogant souls, and your disrespect shall not go unpunished.\n(In an instant, shadows converge around you, binding you tightly, rendering you immobile) *Your heart races with fear as Shuten Doji approaches, emanating an aura of pure malevolence.*\n...\n*Your vision grows darker, and your consciousness slowly slips away...*");

        Dialogue aboutChisui = new Dialogue("Ah, the vampiric weapon that rests in your hands, a relic steeped in ancient bloodlust and shadowed origins. Its tale is one of intricate craftsmanship and the intertwining of fates.\nLong ago, when darkness cast its veil upon the realm, a clandestine order of artisans and sorcerers emerged from the shadows. They were known as the Kiseijuu, masters of forbidden arts and creators of weapons that fed upon the very essence of life. The Nightweavers sought to turn the powers of the vampiric creatures against themselves, to harness their dark energy and use it as a weapon of vengeance.\nNow, as you hold this vampiric weapon, it is both a boon and a burden. It carries the weight of the Nightweavers' struggle against the darkness, as well as the echoes of countless lives whose essence has been siphoned by its blade.");

        // Choices
        Choice cGoodbye = new Choice("Goodbye.", disrespected);

        Choice cFarewell = new Choice("Our paths have crossed, and though our encounters have been fraught with darkness and trepidation, I now depart from your presence.",null);

        Choice cDisrespectOne = new Choice("I stand before you with a proposition that may pique your interest. I seek the Remembrance of the Grafted, an artifact said to hold immense power and knowledge.", disrespected);

        Choice cDisrespectTwo = new Choice("You claim to be an embodiment of darkness, but all I see is a pathetic creature playing at being powerful.", disrespected);

        Choice cDisrespectThree = new Choice("I understand your skepticism, but it seems that behind all that bluster, you're nothing more than a pitiful monster.", disrespected);

        Choice cDisrespectFour = new Choice("Spare me your warnings. I care not for your expectations or burdens, for I now wield this sword as I please.", disrespected);

        Choice cDisrespectFive = new Choice("Your dark might is truly awe-inspiring, How did you come to possess such sinister abilities, and what advice would you give to those who dare to dabble in darkness?", disrespected);

        Choice cAcceptFate = new Choice("Accept your fate", new InflictDamage(99999),null);

        Choice cComprehendOne = new Choice("I am both fascinated and terrified by your legendary infamy. I seek to understand the depths of your wickedness and the secrets of your malevolent power.", comprehendOne);

        Choice cComprehendTwo = new Choice("Your words chill me to the bone. I shall ponder the depths of darkness you've unveiled, even if it means risking my own soul. Your legacy of evil shall not be forgotten.", comprehendTwo);

        Choice cPleadOne = new Choice("I have heard tales of a legendary sword said to be under your possession. I implore you to spare me the mercy of wielding that sword.", pleadOne);

        Choice cPleadTwo = new Choice("I understand your skepticism, but I offer you my word and unwavering loyalty.", pleadTwo);

        Choice cAcceptSword = new Choice("I accept the challenge and vow to honor the power of the sword. My actions shall reflect my unwavering determination to wield it with righteousness and protect those in need.", Arrays.asList(new GiveWeapon(new Chisui()), new ToggleChoice(cPleadTwo,false)), null);

        Choice cAboutChisui = new Choice("Tell me more about Chisui.", aboutChisui, new CapabilitySet(){{this.addCapability(Status.BLESSED);}});
        Choice cAboutChisuiFinish = new Choice("Your words resonate with both fascination and apprehension. To hold a weapon forged from the essence of vampires, to wield the power that once belonged to those who thrived on darkness, is an intriguing proposition.", null);

        // link Dialogue and Choices
        this.addChoice(cDisrespectOne);
        this.addChoice(cComprehendOne);
        this.addChoice(cGoodbye);

        comprehendOne.addChoice(cDisrespectFive);
        comprehendOne.addChoice(cComprehendTwo);

        comprehendTwo.addChoice(cPleadOne);
        comprehendTwo.addChoice(cDisrespectTwo);
        comprehendTwo.addChoice(cFarewell);
        comprehendTwo.addChoice(cAboutChisui);

        pleadOne.addChoice(cPleadTwo);
        pleadOne.addChoice(cDisrespectThree);

        pleadTwo.addChoice(cAcceptSword);
        pleadTwo.addChoice(cDisrespectFour);

        disrespected.addChoice(cAcceptFate);

        aboutChisui.addChoice(cAboutChisuiFinish);
    }
}
