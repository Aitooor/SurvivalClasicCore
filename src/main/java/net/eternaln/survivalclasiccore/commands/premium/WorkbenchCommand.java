package net.eternaln.survivalclasiccore.commands.premium;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.data.configuration.Configuration;
import net.eternaln.survivalclasiccore.data.configuration.MessagesFile;
import net.eternaln.survivalclasiccore.managers.CooldownManager;
import net.eternaln.survivalclasiccore.utils.Cooldown;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.concurrent.TimeUnit;

@CommandAlias("workbench|mesa|mesadetrabajo")
@CommandPermission("survivalclasic.workbench")
public class WorkbenchCommand extends BaseCommand {

    private Configuration config = SurvivalClasicCore.getInstance().getConfiguration();
    private MessagesFile messageFile = SurvivalClasicCore.getMessagesFile();
    private CooldownManager cooldowns = SurvivalClasicCore.getInstance().getCooldowns();
    private int cooldownConfig = config.cmdCooldown;

    @Default
    public void god(Player sender) {
        if (cooldowns.getCooldown(sender.getUniqueId()) == null) {
            sender.openWorkbench(null, true);
            Utils.send(sender, "&aAbriendo tu mesa de trabajo");
            cooldowns.create(sender.getUniqueId(), new Cooldown(TimeUnit.SECONDS.toMillis(cooldownConfig)));
            return;
        }
        Cooldown cooldown = cooldowns.getOrCreate(sender.getUniqueId(), TimeUnit.SECONDS.toMillis(cooldownConfig));
        if (!cooldown.hasExpired()) {
            Utils.send(sender, messageFile.cooldown.replace("%time%", String.valueOf(TimeUnit.MILLISECONDS.toSeconds(cooldown.getRemaining()))));
            return;
        }
        cooldown.stop();
        cooldowns.create(sender.getUniqueId(), new Cooldown(TimeUnit.SECONDS.toMillis(cooldownConfig)));
        sender.openWorkbench(null, true);
        Utils.send(sender, "&aAbriendo tu mesa de trabajo");
    }

    @Subcommand("otros|others|other|otro")
    @CommandPermission("survivalclasic.workbench.other")
    public void other(Player sender, Player target) {
        if (cooldowns.getCooldown(sender.getUniqueId()) == null) {
            target.openWorkbench(null, true);
            Utils.send(sender, "&fAbriendo la mesa de trabajo de &b" + target.getName());
            Utils.send(target, sender.getDisplayName() + " &fha abierto tu mesa de trabajo");
            cooldowns.create(sender.getUniqueId(), new Cooldown(TimeUnit.SECONDS.toMillis(cooldownConfig)));
            return;
        }
        Cooldown cooldown = cooldowns.getOrCreate(sender.getUniqueId(), TimeUnit.SECONDS.toMillis(cooldownConfig));
        if (!cooldown.hasExpired()) {
            Utils.send(sender, messageFile.cooldown.replace("%time%", String.valueOf(TimeUnit.MILLISECONDS.toSeconds(cooldown.getRemaining()))));
            return;
        }
        cooldown.stop();
        cooldowns.create(sender.getUniqueId(), new Cooldown(TimeUnit.SECONDS.toMillis(cooldownConfig)));
        target.openWorkbench(null, true);
        Utils.send(sender, "&fAbriendo la mesa de trabajo de &b" + target.getName());
        Utils.send(target, sender.getDisplayName() + " &fha abierto tu mesa de trabajo");
    }

}
