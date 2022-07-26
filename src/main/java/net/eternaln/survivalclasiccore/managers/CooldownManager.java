package net.eternaln.survivalclasiccore.managers;

import net.eternaln.survivalclasiccore.utils.CooldownOld;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownManager {

    private final Map<UUID, CooldownOld> cooldonws = new HashMap<>();
    private final Map<UUID, CooldownOld> cooldownsRepair = new HashMap<>();

    public void create(UUID uuid, CooldownOld cooldownOld) {
        this.cooldonws.put(uuid, cooldownOld);
    }

    public void createRepair(UUID uuid, CooldownOld cooldownOld) {
        this.cooldownsRepair.put(uuid, cooldownOld);
    }

    public void delete(UUID uuid) {
        this.cooldonws.remove(uuid);
    }

    public void deleteRepair(UUID uuid) {
        this.cooldownsRepair.remove(uuid);
    }

    public CooldownOld getOrCreate(UUID uuid, long duration) {
        return this.cooldonws.putIfAbsent(uuid, new CooldownOld(duration));
    }

    public CooldownOld getOrCreateRepair(UUID uuid, long duration) {
        return this.cooldownsRepair.putIfAbsent(uuid, new CooldownOld(duration));
    }

    public CooldownOld getCooldown(UUID uuid) {
        return this.cooldonws.get(uuid);
    }

    public CooldownOld getCooldownRepair(UUID uuid) {
        return this.cooldownsRepair.get(uuid);
    }

}