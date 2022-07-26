package net.eternaln.survivalclasiccore.utils;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * An easy to use Cooldown class
 */
public class Cooldown<T> {

    private final HashMap<T, Long> cooldowns = new HashMap<>();
    // Change time to how ever long you want
    private final long time;

    /**
     * Instantiates a new Cooldown. <br><br>
     *
     * For example if you need a 10 seconds cooldown you have to instantiate it with: <b>new Cooldown(10, TimeUnit.SECONDS)</b>
     *
     * @param time the time
     * @param sourceUnit The {@link TimeUnit TimeUnit} in which time is placed
     */
    public Cooldown(long time, TimeUnit sourceUnit) {
        this.time = TimeUnit.MILLISECONDS.convert(time, sourceUnit);
    }

    /**
     * Add the player to the cooldown
     *
     * @param player the player
     */
    public void addToCooldown(T player) {
        cooldowns.put(player, System.currentTimeMillis());
    }

    /**
     * Get the remaining time
     *
     * @param player the player
     * @return time remaining
     */
    public float getTimeRemaining(T player) {
        return time - ((System.currentTimeMillis() - cooldowns.get(player)));
    }

    /**
     * Get the remaining seconds
     *
     * @param player the player
     * @return time remaining
     */
    public float getSecondsRemaining(T player) {
        return getTimeRemaining(player) / 1000;
    }

    /**
     * Get the remaining time in a string
     * @param player
     * @return time remaining formatted
     */
    public String getSecondsRemainingString(T player) {
        return new DecimalFormat("#.#").format(getSecondsRemaining(player)) + "s";
    }

    /**
     * Gets if the player's cooldown is over
     *
     * @param player the player
     * @return true if it is over, otherwise false
     */
    public boolean isCooldownOver(T player) {
        if (!cooldowns.containsKey(player) || (((System.currentTimeMillis() - cooldowns.get(player))) >= time)) {
            cooldowns.remove(player);
            return true;
        } else {
            return false;
        }
    }
}