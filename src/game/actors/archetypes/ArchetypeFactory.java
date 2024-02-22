package game.actors.archetypes;


import utils.RandomNumberGenerator;

/**
 * This class returns an instance of the Archetype class based on an integer input.
 * @author Wee Jun Lin 😎<br>
 * Modified by: Kok Tim Ming
 */
public class ArchetypeFactory {
    /**
     * Creates an instance of a subclass of Archetype based on the given choice.
     *
     * @param choice an integer representing the user's choice
     * @return an instance of a subclass of Archetype
     * @throws IndexOutOfBoundsException if choice is not 1, 2, or 3 or 4
     */
    public static Archetype createArchetype(int choice){
        return switch (choice) {
            case 1 -> new Samurai();
            case 2 -> new Bandit();
            case 3 -> new Wretch();
            case 4 -> new Astrologer();
            default -> throw new IndexOutOfBoundsException("Invalid choice: " + choice);
        };
    }

    public static Archetype getRandArchetype(){
        return createArchetype(RandomNumberGenerator.getRandomInt(1,4));
    }
}
