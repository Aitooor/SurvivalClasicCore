package net.eternaln.survivalclasiccore.commands.premium;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.utils.Cooldown;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

@CommandAlias("workbench|mesa|mesadetrabajo")
@CommandPermission("survivalclasic.workbench")
public class WorkbenchCommand extends BaseCommand {

    private final Cooldown<UUID> cooldown = new Cooldown<>(SurvivalClasicCore.getConfiguration().getCmdCooldown());

    @Default
    public void god(Player sender) {
        if (!sender.hasPermission("survivalclasic.cooldown.bypass") && !cooldown.isCooledDown(sender.getUniqueId())) {
            long cooldownTime = cooldown.getSecondsRemaining(sender.getUniqueId());
            Utils.send(sender, SurvivalClasicCore.getMessagesFile().cooldown.replace("%time%", String.valueOf(cooldownTime)));
            return;
        }
        sender.openWorkbench(null, true);
        Utils.send(sender, "&aAbriendo tu mesa de trabajo");
    }

    @Subcommand("otros|others|other|otro")
    @CommandPermission("survivalclasic.workbench.other")
    public void other(Player sender, String target) {
        Player targetPlayer = Bukkit.getPlayer(target);
        targetPlayer.openWorkbench(null, true);
        Utils.send(sender, "&fAbriendo la mesa de trabajo de &b" + targetPlayer.getName());
        Utils.send(targetPlayer, sender.getDisplayName() + " &fha abierto tu mesa de trabajo");
    }

}
