package net.eternaln.survivalclasiccore.commands.messages;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import lombok.Getter;
import net.eternaln.survivalclasiccore.commands.admin.SocialSpyCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

@CommandAlias("msg|w|whisper|tell|hablar|pm|md")
@CommandPermission("simple.message")
public class MessageCommand extends BaseCommand {

    @Getter
    private static final HashMap<UUID, UUID> conversations = new HashMap<>();

    @Default
    @CatchUnknown
    @CommandCompletion("@players @players")
    public void msg(Player sender, String target, String message) {

        Player receiver = Bukkit.getPlayer(target);

        if (receiver == null) {
            sender.sendMessage(ChatColor.RED + "The player must be online");
            return;
        }

        sender.sendMessage(ChatColor.YELLOW + "To " + receiver.getDisplayName() + ChatColor.YELLOW + ChatColor.BOLD
                + " >> " + ChatColor.GRAY + message);
        receiver.sendMessage(ChatColor.YELLOW + "From " + sender.getDisplayName() + ChatColor.YELLOW
                + ChatColor.BOLD + " >> " + ChatColor.GRAY + message);
        receiver.playSound(receiver.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);

        getConversations().put(sender.getUniqueId(), receiver.getUniqueId());
        getConversations().put(receiver.getUniqueId(), sender.getUniqueId());

        SocialSpyCommand.getSocialSpyList().stream().map(Bukkit::getPlayer).filter(Objects::nonNull).forEach(p -> {
            p.sendMessage(ChatColor.YELLOW + "From " + sender.getDisplayName() + ChatColor.YELLOW + " to " +
                    receiver.getDisplayName() + ChatColor.YELLOW + ChatColor.BOLD
                    + " >> " + ChatColor.GRAY + message);
        });
    }
}
