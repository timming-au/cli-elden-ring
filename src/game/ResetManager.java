package game;

import edu.monash.fit2099.engine.capabilities.Capable;
import game.enums.Status;

import java.util.ArrayList;
import java.util.List;

/**
 * A reset manager class that manages a list of resettables. Follows Singleton & Registry pattern.
 * @author Adrian Kristanto
 * Modified by: Kok Tim Ming
 */
public class ResetManager {
    private final List<Resettable> resettables;
    private final List<Capable> capableRest;
    private static ResetManager instance;

    private ResetManager() {
        this.resettables = new ArrayList<>();
        this.capableRest = new ArrayList<>();
    }

    /**
     * Returns the singleton ResetManager
     * @return the singleton ResetManager
     */
    public static ResetManager getInstance(){
        if(instance == null){
            instance = new ResetManager();
        }
        return instance;
    }

    /**
     * Execute reset for all registered resettables & adds REST_SPECIAL status to registered capables
     */
    public void run() {
        for(Capable capable: this.capableRest){
            capable.addCapability(Status.REST_SPECIAL);
        }
        for(Resettable resettable: this.resettables){
            resettable.reset();
        }
    }

    /**
     * Register a resettable
     * @param resettable Instance to register
     */
    public void register(Resettable resettable) {
        this.resettables.add(resettable);
    }

    /**
     * Register a Capable that should be resettable
     * @param capable Instance to register
     */
    public void registerRest(Capable capable) {
        this.capableRest.add(capable);
    }

    /**
     * Unregister an object
     * @param object object to unregister
     */
    public void unregister(Object object) {
        this.capableRest.remove(object);
        this.resettables.remove(object);
    }

}
