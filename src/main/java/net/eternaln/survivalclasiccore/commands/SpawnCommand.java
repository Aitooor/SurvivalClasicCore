package net.eternaln.survivalclasiccore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.data.configuration.Configuration;
import net.eternaln.survivalclasiccore.data.configuration.MessagesFile;
import net.eternaln.survivalclasiccore.utils.Cooldown;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@CommandAlias("spawn|spawnpoint")
public class SpawnCommand extends BaseCommand {

    Configuration config = SurvivalClasicCore.getConfiguration();
    private MessagesFile messageFile = SurvivalClasicCore.getMessagesFile();
    private Cooldown<UUID> cooldown = new Cooldown<>(SurvivalClasicCore.getConfiguration().getCmdCooldown(), TimeUnit.SECONDS);

    @Default
    public void spawn(Player sender) {
        if (!cooldown.isCooldownOver(sender.getUniqueId())) {
            String cooldownTime = cooldown.getSecondsRemainingString(sender.getUniqueId());
            Utils.send(sender, messageFile.cooldown.replace("%time%", cooldownTime));
            return;
        }
        cooldown.addToCooldown(sender.getUniqueId());
        sender.teleport(config.spawnLocation);
        Utils.send(sender, messageFile.tpSpawn);
    }

    @Subcommand("otros|others|other|otro")
    @CommandPermission("survivalclasic.spawn.other")
    @CommandCompletion("@players")
    public void spawnOther(Player sender, Player target) {
        if (!(target == null || target == null)) {
            if (!(target == sender)) {
                target.teleport(SurvivalClasicCore.getConfiguration().getSpawnLocation());
                Utils.send(target, messageFile.tpSpawn);
            } else {
                Utils.send(sender, messageFile.tpSelf);
            }
        } else {
            Utils.send(sender, messageFile.playerNotFound);
        }
    }

    @Subcommand("all|todos")
    @CommandPermission("survivalclasic.spawn.all")
    public void spawnAll(Player sender) {
        for (Player online : Bukkit.getServer().getOnlinePlayers()) {
            if (online != sender) {
                online.teleport(SurvivalClasicCore.getConfiguration().getSpawnLocation());
                Utils.send(online, messageFile.tpSpawnOther);
            }
        }
        Utils.send(sender, messageFile.tpSpawnAll);
    }
}