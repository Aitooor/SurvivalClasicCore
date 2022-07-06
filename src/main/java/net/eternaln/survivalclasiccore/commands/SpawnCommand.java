package net.eternaln.survivalclasiccore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.utils.Cooldown;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

@CommandAlias("spawn|spawnpoint")
public class SpawnCommand extends BaseCommand {

    private final Cooldown<UUID> cooldown = new Cooldown<>(SurvivalClasicCore.getConfiguration().getCmdCooldown());

    @Default
    public void spawn(Player sender) {
        if (!sender.hasPermission("survivalclasiccore.cooldown.bypass") && !cooldown.isCooledDown(sender.getUniqueId())) {
            sender.sendMessage(Utils.ct("&cDebes esperar &b" + cooldown.getSecondsRemaining(sender.getUniqueId()) + " &csegundos"));
            return;
        }

        sender.teleport(SurvivalClasicCore.getConfiguration().getSpawnLocation());
        cooldown.addToCooldown(sender.getUniqueId());
        Utils.send(sender, SurvivalClasicCore.getConfiguration().getTpSpawn());
    }

    @Subcommand("other|others|otros|otro")
    @CommandPermission("survivalclasiccore.spawn.other")
    @CommandCompletion("@players")
    public void spawnOther(Player sender, Player target) {
        if(!(target == null || target == null)) {
            if(!(target == sender)) {
                target.teleport(SurvivalClasicCore.getConfiguration().getSpawnLocation());
                Utils.send(target, SurvivalClasicCore.getConfiguration().getTpSpawn());
            } else {
                Utils.send(sender, "&cNo puedes teletransportarte a ti mismo");
            }
        } else {
            Utils.send(sender, "&cJugador no encontrado");
        }
    }

    @Subcommand("all|todos")
    public void spawnAll(Player sender) {
        for (Player online : Bukkit.getServer().getOnlinePlayers()) {
            if (online != sender) {
                online.teleport(SurvivalClasicCore.getConfiguration().getSpawnLocation());
                Utils.send(online, "&fHas sido teletransportado hacia &bSpawn");
            } else {
                Utils.send(sender, "&cNo puedes teletransportarte a ti mismo");
            }
        }
        Utils.send(sender, "&fHas teletransportado a todos hacia &bSpawn");
    }
}