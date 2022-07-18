package net.eternaln.survivalclasiccore.commands.teleport;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CatchUnknown;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.HelpCommand;
import lombok.Getter;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.data.configuration.MessagesFile;
import net.eternaln.survivalclasiccore.utils.Cooldown;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

@CommandAlias("tpa")
public class TpaCommand extends BaseCommand {
    @Getter
    private static final HashMap<Player, Player> requests = new HashMap();

    MessagesFile messagesFile = SurvivalClasicCore.getMessagesFile();

    private final Cooldown<UUID> cooldown = new Cooldown<>(SurvivalClasicCore.getConfiguration().getCmdCooldown());

    @CatchUnknown
    @HelpCommand("ayuda|help")
    public void help(CommandHelp help) {
        help.showHelp();
    }

    @Default
    public void teleport(Player sender, String target) {
        if (!sender.hasPermission("survivalclasiccore.cooldown.bypass") && !cooldown.isCooledDown(sender.getUniqueId())) {
            long cooldownTime = cooldown.getSecondsRemaining(sender.getUniqueId());
            Utils.send(sender, SurvivalClasicCore.getMessagesFile().cooldown.replace("%time%", String.valueOf(cooldownTime)));
            return;
        }
        Player targetPlayer = Bukkit.getPlayer(target);
        if (targetPlayer != null) {
            if (!target.equals(sender.getName())) {
                Utils.send(sender, messagesFile.tpaSender.replace("%player%", targetPlayer.getName()));
                Utils.send(targetPlayer, messagesFile.tpaTarget.replace("%player%", sender.getName()));
                requests.put(targetPlayer, sender);
            } else {
                Utils.send(sender, messagesFile.tpSelf);
            }
        } else {
            Utils.send(sender, messagesFile.noOnlinePlayer.replace("%player%", target));
        }
    }
}
