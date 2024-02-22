package game.grounds.travelables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * TravelManager. Manages the travelling network between travelables.
 * @author Kok Tim Ming
 */
public class TravelManager {
    private final HashMap<String, Set<Travelable>> network = new HashMap<>();

    // contains all networks that the travelable is associated with
    private final HashMap<Travelable, Set<String>> travelableAssociation = new HashMap<>();

    private static TravelManager instance;

    /**
     * Returns TravelManager instance
     * @return TravelManager instance
     */
    public static TravelManager getInstance(){
        if(instance == null){
            instance = new TravelManager();
        }
        return instance;
    }

    private TravelManager(){}

    /**
     * Adds to a network
     * @param networkName Network name
     * @param travelable Travelable to add
     */
    public void register(String networkName, Travelable travelable){
        // add travelable's associations
        if(travelableAssociation.containsKey(travelable)){
            travelableAssociation.get(travelable).add(networkName);
        }else{
            Set<String> set = new HashSet<>();
            set.add(networkName);
            travelableAssociation.put(travelable,set);
        }

        // add travelable to network
        if(network.containsKey(networkName)){
            network.get(networkName).add(travelable);
        }else{
            Set<Travelable> set = new HashSet<>();
            set.add(travelable);
            network.put(networkName, set);
        }
    }

    /**
     * Registers a pair of travelables generates a unique networkName for both of them
     * @param a Travelable one
     * @param b Travelable two
     */
    public void registerPair(Travelable a, Travelable b){
        String networkName = UUID.randomUUID().toString();
        register(networkName, a);
        register(networkName, b);
    }

    /**
     * Get a list of associated travelables
     * @param travelable Travelable to find associations with
     * @return list of associated travelables
     */
    public ArrayList<Travelable> getAssociations(Travelable travelable){
        Set<Travelable> associatedTravelables = new HashSet<>();
        if(travelableAssociation.containsKey(travelable)){
            for (String networkName: travelableAssociation.get(travelable)){
                associatedTravelables.addAll(network.get(networkName));
            }
        }
        associatedTravelables.remove(travelable);
        return new ArrayList<>(associatedTravelables);
    }
}
