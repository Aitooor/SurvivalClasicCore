package net.eternaln.survivalclasicbasis.utils;

import net.eternaln.survivalclasicbasis.SurvivalClasicBasis;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownManager {

    private final Map<UUID, Integer> cooldowns = new HashMap<>();

    static FileConfiguration config = SurvivalClasicBasis.getInstance().getConfig();

    public static final int DEFAULT_COOLDOWN = config.getInt("cmd-cooldown");

    public void setCooldown(UUID player, int time){
        if(time < 1) {
            cooldowns.remove(player);
        } else {
            cooldowns.put(player, time);
        }
    }

    public int getCooldown(UUID player){
        return cooldowns.getOrDefault(player, 0);
    }
}
