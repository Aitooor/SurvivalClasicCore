package net.eternaln.survivalclasiccore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.data.configuration.MessagesFile;
import net.eternaln.survivalclasiccore.utils.Cooldown;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

@CommandAlias("spawn|spawnpoint")
public class SpawnCommand extends BaseCommand {

    MessagesFile messageFile = SurvivalClasicCore.getMessagesFile();

    private final Cooldown<UUID> cooldown = new Cooldown<>(SurvivalClasicCore.getConfiguration().getCmdCooldown());

    @Default
    public void spawn(Player sender) {
        if (!sender.hasPermission("survivalclasic.cooldown.bypass") && !cooldown.isCooledDown(sender.getUniqueId())) {
            long cooldownTime = cooldown.getSecondsRemaining(sender.getUniqueId());
            Utils.send(sender, messageFile.cooldown.replace("%time%", String.valueOf(cooldownTime)));
            return;
        }

        sender.teleport(SurvivalClasicCore.getConfiguration().getSpawnLocation());
        cooldown.addToCooldown(sender.getUniqueId());
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