package game;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.World;
import game.actors.archetypes.Archetype;
import game.actors.archetypes.ArchetypeFactory;
import game.actors.friendlies.HuTao;
import game.actors.friendlies.Player;
import game.actors.enemies.ShutenDouji;
import game.grounds.travelables.SiteOfLostGrace;
import utils.FancyMessage;

import java.util.Scanner;

/**
 * The main class to start the game.
 * @author Adrian Kristanto
 * Modified by: Kok Tim Ming, Wee Jun Lin
 */
public class Application {

	public static void main(String[] args) {

		startMessage();

		World world = new World(new Display());

		// prompt user to choose Archetype
		int characterType = chooseCharacterType();
		Archetype selectedArchetype = ArchetypeFactory.createArchetype(characterType);
		GameMapBuilder gameMapBuilder = new GameMapBuilder();


		// --- ACTUAL MAP --- //

		gameMapBuilder.initialise();

		// add game maps
		// player map is the first gameMap in the list
		boolean isPlayerMap = true;
		for (GameMap map : gameMapBuilder.getMaps()) {
			world.addGameMap(map);
			if (isPlayerMap) {
				// player map is the first gameMap in the list
				Location playerSpawnLocation = map.at(38,11);
				Player player = new Player("Tarnished", '@', selectedArchetype.hitPoints(), selectedArchetype, playerSpawnLocation);
				world.addPlayer(player, playerSpawnLocation);
				isPlayerMap = false;
			}
		}
		gameMapBuilder.initialiseMaps();

 		// --- ACTUAL MAP --- //



		// --- TEST MAP --- //

//		gameMapBuilder.initialiseTest();
//
//		// add game maps
//		// player map is the first gameMap in the list
//		boolean isPlayerMap = true;
//		for (GameMap map : gameMapBuilder.getMaps()) {
//			world.addGameMap(map);
//			if (isPlayerMap) {
//				// player map is the first gameMap in the list
//				Location playerSpawnLocation = map.at(38,11);
//				Player player = new Player("Tarnished", '@', selectedArchetype.hitPoints(), selectedArchetype, playerSpawnLocation);
//				world.addPlayer(player, playerSpawnLocation);
//				map.at(39,12).addActor(new HuTao());
//				map.at(38,12).addActor(new ShutenDouji());
//				isPlayerMap = false;
//			}
//			map.at(36,18).setGround(new SiteOfLostGrace("C"));
//			map.at(38,18).setGround(new SiteOfLostGrace("A"));
//			map.at(40,18).setGround(new SiteOfLostGrace("B"));
//		}

		// --- TEST MAP --- //


		world.run();
	}

	/**
	 * Prompts the user to choose a character type by selecting a number between 1 and 3.
	 * The available character types are:
	 * (1) Samurai
	 * (2) Bandit
	 * (3) Wretch
	 * The method ensures that the user inputs a valid integer between 1 and 3.
	 *
	 * @return an integer representing the chosen character type, where 1 is Samurai, 2 is Bandit, and 3 is Wretch.
	 */
	public static int chooseCharacterType() {
		int characterType = 0;
		boolean isValidInput = false;
		Scanner scanner = new Scanner(System.in);

		do {
			System.out.println("Pick Your Class! (1) Samurai (2) Bandit (3) Wretch (4) Astrologer:");
			if (scanner.hasNextInt()) {
				characterType = scanner.nextInt();
				if (characterType >= 1 && characterType <= 4) {
					isValidInput = true;
				} else {
					System.out.println("Invalid input. Please enter a number between 1 and 4.");
				}
			} else {
				System.out.println("Invalid input. Please enter a number between 1 and 4.");
				scanner.next();
			}
		} while (!isValidInput);

		return characterType;
	}

	/**
	 * Prints elden ring start message
	 */
	public static void startMessage(){
		// BEHOLD, ELDEN RING
		for (String line : FancyMessage.ELDEN_RING.split("\n")) {
			new Display().println(line);
			try {
				Thread.sleep(50);
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}

		System.out.println("BEHOLD, ELDEN RING!");
	}
}
