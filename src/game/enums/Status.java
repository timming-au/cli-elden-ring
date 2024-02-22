package game.enums;

/**
 * Use this enum class to give `buff` or `debuff`.
 * @author Riordan D. Alfredo
 * Modified by: Kok Tim Ming
 */
public enum Status {
    /**
     * Given to anybody who wants the smoke.
     */
    HOSTILE_TO_ENEMY,
    /**
     * Indicator that the Actor reached <0 HP, but will execute custom death
     */
    DEATH_INBOUND,
    /**
     * Protects from de-spawning, ideally once
     */
    RESET_INBOUND,
    /**
     * Protects from de-spawning, ideally once
     */
    DESPAWN_PROTECTION,
    /**
     * Filters some functions of a resettable's reset when rest is called. Maybe?
     */
    REST_SPECIAL,
    /**
     * Blessed by Shuten Douji's equipment.
     */
    BLESSED
}
