package game;

import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.GroundFactory;
import game.actors.DummyActor;
import game.actors.enemies.ShutenDouji;
import game.actors.friendlies.FingerReaderEnia;
import game.actors.friendlies.GatekeeperGostoc;
import game.actors.friendlies.HuTao;
import game.actors.friendlies.MerchantKale;
import game.economy.runes.GoldenRune;
import game.grounds.*;
import game.grounds.environments.*;
import game.grounds.travelables.GoldenFogDoor;
import game.grounds.travelables.SiteOfLostGrace;
import game.grounds.travelables.TravelManager;
import utils.Maps;
import utils.RandomNumberGenerator;

import java.util.*;

/**
 * Utility class for map building in Application.
 * This is required to statically map fixed travelling networks (Golden Fog Doors), and perhaps
 * to organise map building
 * @author Kok Tim Ming
 */
public class GameMapBuilder {
    private final GroundFactory groundFactory = new FancyGroundFactory(
            new Dirt(), new Wall(), new Floor(), new Graveyard(), new GustOfWind(),
            new PuddleOfWater(), new Cliff(), new SummonSign(),
            new Cage(), new Barrack(), new SummonSign());
    private HashMap<String, GameMap> hashMaps;

    /**
     * To initialise Test Map
     */
    public void initialiseTest(){
        this.hashMaps = new HashMap<>(){{
            put("EMPTY",new GameMap(groundFactory, Maps.EMPTY));
        }};
    }
    /**
     * To initialise Actual Map
     */
    public void initialise(){
        this.hashMaps = new HashMap<>(){{
            put("LIMGRAVE",new GameMap(groundFactory, Maps.LIMGRAVE));
            put("STORMVEIL_CASTLE",new GameMap(groundFactory, Maps.STORMVEIL_CASTLE));
            put("ROUNDTABLE_HOLD",new GameMap(groundFactory, Maps.ROUNDTABLE_HOLD));
            put("BOSS_ROOM",new GameMap(groundFactory, Maps.BOSS_ROOM));
        }};
    }

    /**
     * Scatters Golden Runes with probability
     * @param map Map to scatter Golden Runes on
     * @param spawnChance Spawning chance for each ground
     */
    public void scatterGoldenRunes(GameMap map, int spawnChance){
        for (int x: map.getXRange()){
            for (int y: map.getYRange()){
                if(spawnChance >= RandomNumberGenerator.getRandomInt(100)
                        && map.at(x,y).getGround().canActorEnter(new DummyActor())){
                    map.at(x,y).addItem(new GoldenRune());
                }
            }
        }
    }

    /**
     * Returns the list of maps
     * @return
     */
    public ArrayList<GameMap> getMaps(){
        return new ArrayList<>(hashMaps.values());
    }

    /**
     * Initialise game objects on actual maps
     */
    public void initialiseMaps() {
        if(hashMaps == null){
            return;
        }

        // limgrave
        GoldenFogDoor limgraveDoorA = new GoldenFogDoor("Limgrave");
        GoldenFogDoor limgraveDoorB = new GoldenFogDoor("Limgrave");
        GameMap limGrave = hashMaps.get("LIMGRAVE");
        limGrave.at(5,10).setGround(limgraveDoorA);
        limGrave.at(35,10).setGround(limgraveDoorB);
        limGrave.at(38,15).setGround(new SiteOfLostGrace("The First Step"));
        limGrave.at(39,12).addActor(new MerchantKale());
        limGrave.at(39,17).addActor(new FingerReaderEnia());
        scatterGoldenRunes(limGrave,1);

        // stormveil castle
        GoldenFogDoor stormveilDoorA = new GoldenFogDoor("Stormveil Castle");
        GoldenFogDoor stormveilDoorB = new GoldenFogDoor("Stormveil Castle");
        GameMap svCastle = hashMaps.get("STORMVEIL_CASTLE");
        svCastle.at(38,21).setGround(stormveilDoorA);
        svCastle.at(1,1).setGround(stormveilDoorB);
        svCastle.at(36,18).setGround(new SiteOfLostGrace("Stormveil Main Gate"));
        svCastle.at(38,16).addActor(new ShutenDouji());
        svCastle.at(39,21).addActor(new GatekeeperGostoc());
        scatterGoldenRunes(svCastle,1);

        // roundtable hold
        GoldenFogDoor roundtableDoorA = new GoldenFogDoor("Roundtable Hold");
        GameMap roundtable = hashMaps.get("ROUNDTABLE_HOLD");
        roundtable.at(4,6).setGround(new SiteOfLostGrace("Table of Lost Grace"));
        roundtable.at(9,5).addActor(new HuTao());
        roundtable.at(9,9).setGround(roundtableDoorA);

        // boss room
        GoldenFogDoor bossRoomDoorA = new GoldenFogDoor("Boss Room");
        hashMaps.get("BOSS_ROOM").at(23,7).setGround(bossRoomDoorA);

        TravelManager tgm = TravelManager.getInstance();
        tgm.registerPair(limgraveDoorA, stormveilDoorA);
        tgm.registerPair(limgraveDoorB, roundtableDoorA);
        tgm.registerPair(stormveilDoorB, bossRoomDoorA);
    }
}
