package net.eternaln.survivalclasiccore.commands.messages;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import lombok.Getter;
import net.eternaln.survivalclasiccore.commands.admin.SocialSpyCommand;
import net.eternaln.survivalclasiccore.utils.Utils;
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

    @CatchUnknown
    @HelpCommand("ayuda|help")
    public void help(CommandHelp help) {
        help.showHelp();
    }

    @Default
    @CommandCompletion("@players @players")
    public void msg(Player sender, String target, String message) {

        Player receiver = Bukkit.getPlayer(target);

        if (receiver == null) {
            Utils.send(sender,"El jugador no esta online");
            return;
        }

        Utils.send(sender,"&7Mensaje enviado a &b" + receiver.getDisplayName());
        Utils.send(receiver,"&7Mensaje recibido de &b" + sender.getDisplayName() + " &7> &f" + message);
        receiver.playSound(receiver.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);

        getConversations().put(sender.getUniqueId(), receiver.getUniqueId());
        getConversations().put(receiver.getUniqueId(), sender.getUniqueId());

        SocialSpyCommand.getSocialSpyList().stream().map(Bukkit::getPlayer).filter(Objects::nonNull).forEach(p -> {
            Utils.send(p,"&8(MSG) &7De &b" + sender.getDisplayName()  + " &7a &b" + receiver.getDisplayName() + " &7> &7" + message);
        });
    }
}
