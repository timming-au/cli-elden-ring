package game.enums;

/**
 * Enum class for NPC behaviors.
 * @author Kok Tim Ming
 */
public enum NPCBehavior {
    /**
     * Any hostile creature, that is territorial.
     */
    HOSTILE,
    /**
     * Any neutral creature, that could get provoked.
     */
    NEUTRAL,
    /**
     * Any passive creature, that will never strike back.
     */
    PASSIVE,
    /**
     * Any creature that is provoked.
     */
    PROVOKED,
    /**
     * Any creature that can provoke.
     */
    PROVOKER

}
