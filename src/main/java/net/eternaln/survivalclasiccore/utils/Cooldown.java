package net.eternaln.survivalclasiccore.utils;

import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.UUID;

/**
 * An easy to use Cooldown class
 */
public class Cooldown<T> {

    private final HashMap<T, Long> cooldowns = new HashMap<>();
    // Change time to how ever long you want
    private final long time;

    /**
     * Instantiates a new Cooldown.
     *
     * @param time the time in milliseconds
     */
    public Cooldown(long time) {
        this.time = time;
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
    public long getTimeRemaining(T player) {
        return time - ((System.currentTimeMillis() - cooldowns.get(player)));
    }

    /**
     * Get the remaining seconds
     *
     * @param player the player
     * @return time remaining
     */
    public long getSecondsRemaining(T player) {
        return time - ((System.currentTimeMillis() - cooldowns.get(player)) / 1000);
    }

    /**
     * Gets if the player's cooldown is over
     *
     * @param player the player
     * @return boolean
     */
    public boolean isCooledDown(T player) {
        if (!cooldowns.containsKey(player) || (((System.currentTimeMillis() - cooldowns.get(player))) >= time)) {
            cooldowns.remove(player);
            Utils.send(Bukkit.getPlayer((UUID) player), "&cNo tienes cooldown");
            Utils.log("&cCOOLDOWN FUNCIONA &7(No tiene)");
            return true;
        } else {
            Utils.log("&cCOOLDOWN FUNCIONA &7(Tiene)");
            return false;
        }
    }

}