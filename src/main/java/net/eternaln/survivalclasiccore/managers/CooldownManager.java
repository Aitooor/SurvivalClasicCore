package net.eternaln.survivalclasiccore.managers;

import net.eternaln.survivalclasiccore.utils.Cooldown;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownManager {

    private final Map<UUID, Cooldown> cooldonws = new HashMap<>();
    private final Map<UUID, Cooldown> cooldownsRepair = new HashMap<>();

    public void create(UUID uuid, Cooldown cooldown) {
        this.cooldonws.put(uuid, cooldown);
    }

    public void createRepair(UUID uuid, Cooldown cooldown) {
        this.cooldownsRepair.put(uuid, cooldown);
    }

    public void delete(UUID uuid) {
        this.cooldonws.remove(uuid);
    }

    public void deleteRepair(UUID uuid) {
        this.cooldownsRepair.remove(uuid);
    }

    public Cooldown getOrCreate(UUID uuid, long duration) {
        return this.cooldonws.putIfAbsent(uuid, new Cooldown(duration));
    }

    public Cooldown getOrCreateRepair(UUID uuid, long duration) {
        return this.cooldownsRepair.putIfAbsent(uuid, new Cooldown(duration));
    }

    public Cooldown getCooldown(UUID uuid) {
        return this.cooldonws.get(uuid);
    }

    public Cooldown getCooldownRepair(UUID uuid) {
        return this.cooldownsRepair.get(uuid);
    }

}