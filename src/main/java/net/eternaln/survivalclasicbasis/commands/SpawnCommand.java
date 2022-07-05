package net.eternaln.survivalclasicbasis.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.Default;
import net.eternaln.survivalclasicbasis.SurvivalClasicBasis;
import net.eternaln.survivalclasicbasis.utils.Cooldown;
import net.eternaln.survivalclasicbasis.utils.Utils;
import org.bukkit.entity.Player;

import java.util.UUID;

public class SpawnCommand extends BaseCommand {

    private final Cooldown<UUID> cooldown = new Cooldown<>(SurvivalClasicBasis.getConfiguration().getCmdCooldown());

    @Default
    public void spawn(Player sender) {
        if (!sender.hasPermission("survivalclasicbasis.cooldown.bypass") && !cooldown.isCooledDown(sender.getUniqueId())) {
            sender.sendMessage(Utils.ct("&cDebes esperar &b" + cooldown.getSecondsRemaining(sender.getUniqueId()) + " &csegundos"));
            return;
        }

        sender.teleport(SurvivalClasicBasis.getConfiguration().getSpawnLocation());
        cooldown.addToCooldown(sender.getUniqueId());
        Utils.send(sender, SurvivalClasicBasis.getConfiguration().getTpSpawn());
    }
}