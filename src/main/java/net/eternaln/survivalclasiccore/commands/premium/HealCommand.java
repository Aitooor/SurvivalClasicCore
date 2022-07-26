package net.eternaln.survivalclasiccore.commands.premium;

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

@CommandAlias("heal|curar|health|salud")
@CommandPermission("survivalclasic.heal")
public class HealCommand extends BaseCommand {

    private MessagesFile messageFile = SurvivalClasicCore.getMessagesFile();
    private Cooldown<UUID> cooldown = new Cooldown<>(SurvivalClasicCore.getConfiguration().getCmdCooldown(), TimeUnit.SECONDS);

    @Default
    public void heal(Player sender) {
        if (!cooldown.isCooldownOver(sender.getUniqueId())) {
            String cooldownTime = cooldown.getSecondsRemainingString(sender.getUniqueId());
            Utils.send(sender, messageFile.cooldown.replace("%time%", cooldownTime));
            return;
        }
        cooldown.addToCooldown(sender.getUniqueId());

        sender.setHealth(20);
        sender.setFoodLevel(20);
        Utils.send(sender, "&aTu salud ha sido restaurada");
    }

    @Subcommand("otros|others|other|otro")
    @CommandPermission("survivalclasic.heal.other")
    @CommandCompletion("@players")
    public void other(Player sender, Player target) {
        if (!cooldown.isCooldownOver(sender.getUniqueId())) {
            String cooldownTime = cooldown.getSecondsRemainingString(sender.getUniqueId());
            Utils.send(sender, messageFile.cooldown.replace("%time%", cooldownTime));
            return;
        }
        cooldown.addToCooldown(sender.getUniqueId());

        target.setHealth(20);
        target.setFoodLevel(20);
        Utils.send(target, "&aTu salud ha sido restaurada por &b" + sender.getName());
    }

    @Subcommand("set|establecer")
    @CommandPermission("survivalclasic.heal.set")
    @CommandCompletion("@players")
    public void set(Player sender, Player target, int amount) {
        if (!cooldown.isCooldownOver(sender.getUniqueId())) {
            String cooldownTime = cooldown.getSecondsRemainingString(sender.getUniqueId());
            Utils.send(sender, messageFile.cooldown.replace("%time%", cooldownTime));
            return;
        }
        cooldown.addToCooldown(sender.getUniqueId());

        target.setHealth(amount);
        Utils.send(target, "&aTu salud ha sido establecida a &e" + amount);
    }

}
