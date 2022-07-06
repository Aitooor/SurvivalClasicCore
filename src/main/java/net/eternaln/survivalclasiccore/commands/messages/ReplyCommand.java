package net.eternaln.survivalclasiccore.commands.messages;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.commands.admin.SocialSpyCommand;
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
            sender.sendMessage(ChatColor.RED + "Nobody messaged you!");
            return;
        }

        Player receiver = Bukkit.getPlayer(MessageCommand.getConversations().get(sender.getUniqueId()));

        if (receiver == null) {
            sender.sendMessage(ChatColor.RED + "The player must be online");
            return;
        }

        sender.sendMessage(ChatColor.YELLOW + "To " + receiver.getDisplayName()
                + ChatColor.YELLOW + ChatColor.BOLD + " >> " + ChatColor.GRAY + message);
        receiver.sendMessage(ChatColor.YELLOW + "From " + sender.getDisplayName() + ChatColor.YELLOW + ChatColor.BOLD
                + " >> " + ChatColor.GRAY + message);
        receiver.playSound(receiver.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);

        SocialSpyCommand.getSocialSpyList().stream().map(Bukkit::getPlayer).filter(Objects::nonNull).forEach(p -> {
            p.sendMessage(ChatColor.YELLOW + "From " + sender.getDisplayName() + ChatColor.YELLOW + " to " +
                    receiver.getDisplayName() + ChatColor.YELLOW + ChatColor.BOLD
                    + " >> " + ChatColor.GRAY + message);
        });
    }
}
