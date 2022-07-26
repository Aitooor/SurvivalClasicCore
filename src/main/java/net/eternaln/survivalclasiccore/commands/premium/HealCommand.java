package net.eternaln.survivalclasiccore.commands.premium;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.data.configuration.Configuration;
import net.eternaln.survivalclasiccore.data.configuration.MessagesFile;
import net.eternaln.survivalclasiccore.managers.CooldownManager;
import net.eternaln.survivalclasiccore.utils.CooldownOld;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.concurrent.TimeUnit;

@CommandAlias("heal|curar|health|salud")
@CommandPermission("survivalclasic.heal")
public class HealCommand extends BaseCommand {

    private Configuration config = SurvivalClasicCore.getInstance().getConfiguration();
    private MessagesFile messageFile = SurvivalClasicCore.getMessagesFile();
    private CooldownManager cooldowns = SurvivalClasicCore.getInstance().getCooldowns();
    private int cooldownConfig = config.cmdCooldown;

    @Default
    public void heal(Player sender) {
        if (cooldowns.getCooldown(sender.getUniqueId()) == null) {
            sender.setHealth(20);
            sender.setFoodLevel(20);
            Utils.send(sender, "&aTu salud ha sido restaurada");
            cooldowns.create(sender.getUniqueId(), new CooldownOld(TimeUnit.SECONDS.toMillis(cooldownConfig)));
            return;
        }
        CooldownOld cooldownOld = cooldowns.getOrCreate(sender.getUniqueId(), TimeUnit.SECONDS.toMillis(cooldownConfig));
        if (!cooldownOld.hasExpired()) {
            Utils.send(sender, messageFile.cooldown.replace("%time%", String.valueOf(TimeUnit.MILLISECONDS.toSeconds(cooldownOld.getRemaining()))));
            return;
        }
        cooldownOld.stop();
        cooldowns.create(sender.getUniqueId(), new CooldownOld(TimeUnit.SECONDS.toMillis(cooldownConfig)));
        sender.setHealth(20);
        sender.setFoodLevel(20);
        Utils.send(sender, "&aTu salud ha sido restaurada");
    }

    @Subcommand("otros|others|other|otro")
    @CommandPermission("survivalclasic.heal.other")
    @CommandCompletion("@players")
    public void other(Player sender, Player target) {
        if (cooldowns.getCooldown(sender.getUniqueId()) == null) {
            target.setHealth(20);
            target.setFoodLevel(20);
            Utils.send(target, "&aTu salud ha sido restaurada por &b" + sender.getName());
            cooldowns.create(sender.getUniqueId(), new CooldownOld(TimeUnit.SECONDS.toMillis(cooldownConfig)));
            return;
        }
        CooldownOld cooldownOld = cooldowns.getOrCreate(sender.getUniqueId(), TimeUnit.SECONDS.toMillis(cooldownConfig));
        if (!cooldownOld.hasExpired()) {
            Utils.send(sender, messageFile.cooldown.replace("%time%", String.valueOf(TimeUnit.MILLISECONDS.toSeconds(cooldownOld.getRemaining()))));
            return;
        }
        cooldownOld.stop();
        cooldowns.create(sender.getUniqueId(), new CooldownOld(TimeUnit.SECONDS.toMillis(cooldownConfig)));
        target.setHealth(20);
        target.setFoodLevel(20);
        Utils.send(target, "&aTu salud ha sido restaurada por &b" + sender.getName());
    }

    @Subcommand("set|establecer")
    @CommandPermission("survivalclasic.heal.set")
    @CommandCompletion("@players")
    public void set(String target, int amount) {
        Player targetPlayer = Bukkit.getPlayer(target);
        if (cooldowns.getCooldown(targetPlayer.getUniqueId()) == null) {
            targetPlayer.setHealth(amount);
            Utils.send(targetPlayer, "&aTu salud ha sido establecida a &e" + amount);
            cooldowns.create(targetPlayer.getUniqueId(), new CooldownOld(TimeUnit.SECONDS.toMillis(cooldownConfig)));
            return;
        }
        CooldownOld cooldownOld = cooldowns.getOrCreate(targetPlayer.getUniqueId(), TimeUnit.SECONDS.toMillis(cooldownConfig));
        if (!cooldownOld.hasExpired()) {
            Utils.send(targetPlayer, messageFile.cooldown.replace("%time%", String.valueOf(TimeUnit.MILLISECONDS.toSeconds(cooldownOld.getRemaining()))));
            return;
        }
        cooldownOld.stop();
        cooldowns.create(targetPlayer.getUniqueId(), new CooldownOld(TimeUnit.SECONDS.toMillis(cooldownConfig)));
        targetPlayer.setHealth(amount);
        Utils.send(targetPlayer, "&aTu salud ha sido establecida a &e" + amount);
    }

}
