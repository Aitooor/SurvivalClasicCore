package net.eternaln.survivalclasiccore.commands.messages;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.commands.admin.SocialSpyCommand;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.Objects;

@CommandAlias("reply|r|responder|contestar")
@CommandPermission("survivalclasiccore.tpa")
public class ReplyCommand extends BaseCommand {

    @CatchUnknown
    @HelpCommand("help|ayuda")
    public void help(CommandHelp help) {
        help.showHelp();
    }

    @Default
    @CommandCompletion("@players")
    public void reply(Player sender, String message) {
        if (MessageCommand.getConversations().get(sender.getUniqueId()) == null) {
            Utils.send(sender, "Nobody messaged you!");
            return;
        }

        Player receiver = Bukkit.getPlayer(MessageCommand.getConversations().get(sender.getUniqueId()));

        if (receiver == null) {
            Utils.send(sender, "The player must be online");
            return;
        }

        Utils.send(sender,"&7Mensaje contestado a &b" + receiver.getDisplayName());
        Utils.send(receiver,"&7Mensaje recibido de &b" + sender.getDisplayName() + "\n&f" + message);
        receiver.playSound(receiver.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);

        SocialSpyCommand.getSocialSpyList().stream().map(Bukkit::getPlayer).filter(Objects::nonNull).forEach(p -> {
            Utils.send(p,"&8(REPLY) &7De &b" + sender.getDisplayName()  + " &7a &b" + receiver.getDisplayName() + " &7> &7" + message);
        });
    }
}
