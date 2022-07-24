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

@CommandAlias("heal|curar|health|salud")
@CommandPermission("survivalclasic.heal")
public class HealCommand extends BaseCommand {

    private final Cooldown<UUID> cooldown = new Cooldown<>(SurvivalClasicCore.getConfiguration().getCmdCooldown());

    @Default
    public void heal(Player sender) {
        if (!sender.hasPermission("survivalclasic.cooldown.bypass") && !cooldown.isCooledDown(sender.getUniqueId())) {
            long cooldownTime = cooldown.getSecondsRemaining(sender.getUniqueId());
            Utils.send(sender, SurvivalClasicCore.getMessagesFile().cooldown.replace("%time%", String.valueOf(cooldownTime)));
            return;
        }
        sender.setHealth(20);
        sender.setFoodLevel(20);
        Utils.send(sender, "&aTu salud ha sido restaurada");
    }

    @Subcommand("otros|others|other|otro")
    @CommandPermission("survivalclasic.heal.other")
    @CommandCompletion("@players")
    public void other(Player sender, String target) {
        Player targetPlayer = Bukkit.getPlayer(target);
        targetPlayer.setHealth(20);
        targetPlayer.setFoodLevel(20);
        Utils.send(targetPlayer, "&aTu salud ha sido restaurada por &b" + sender.getName());
    }

    @Subcommand("set|establecer")
    @CommandPermission("survivalclasic.heal.set")
    @CommandCompletion("@players")
    public void set(String target, int amount) {
        Player targetPlayer = Bukkit.getPlayer(target);
        targetPlayer.setHealth(amount);
        Utils.send(targetPlayer, "&aTu salud ha sido establecida a &e" + amount);
    }

}
