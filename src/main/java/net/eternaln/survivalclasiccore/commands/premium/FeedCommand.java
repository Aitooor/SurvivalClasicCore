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

@CommandAlias("comida|feed|food|alimento")
@CommandPermission("survivalclasic.feed")
public class FeedCommand extends BaseCommand {

    private Configuration config = SurvivalClasicCore.getInstance().getConfiguration();
    private MessagesFile messageFile = SurvivalClasicCore.getMessagesFile();
    private CooldownManager cooldowns = SurvivalClasicCore.getInstance().getCooldowns();
    private int cooldownConfig = config.cmdCooldown;

    @Default
    public void heal(Player sender) {
        if (cooldowns.getCooldown(sender.getUniqueId()) == null) {
            sender.setFoodLevel(20);
            Utils.send(sender, "&aTu comida ha sido restaurada");
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
        sender.setFoodLevel(20);
        Utils.send(sender, "&aTu comida ha sido restaurada");
    }

    @Subcommand("otros|others|other|otro")
    @CommandPermission("survivalclasic.feed.other")
    @CommandCompletion("@players")
    public void other(Player sender, String target) {
        Player targetPlayer = Bukkit.getPlayer(target);
        targetPlayer.setFoodLevel(20);
        Utils.send(targetPlayer, "&aTu comida ha sido restaurada por &b" + sender.getName());
    }

    @Subcommand("set|establecer")
    @CommandPermission("survivalclasic.feed.set")
    @CommandCompletion("@players")
    public void set(String target, int amount) {
        Player targetPlayer = Bukkit.getPlayer(target);
        targetPlayer.setFoodLevel(amount);
        Utils.send(targetPlayer, "&aTu comida ha sido establecida a &e" + amount);
    }

}
