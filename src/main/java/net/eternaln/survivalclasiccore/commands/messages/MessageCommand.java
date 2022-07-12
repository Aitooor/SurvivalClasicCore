package net.eternaln.survivalclasiccore.commands.messages;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import lombok.Getter;
import net.eternaln.survivalclasiccore.commands.admin.SocialSpyCommand;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.Bukkit;
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
            Utils.send(sender, "El jugador no esta online");
            return;
        }

        Utils.sendNoPrefix(sender, "&8(MSG) &7" + sender.getDisplayName() + " &7-> &b" + receiver.getDisplayName() + " &7> &7" + message);
        Utils.sendNoPrefix(receiver, "&8(MSG) &b" + sender.getDisplayName() + " &7> &7" + message);
        receiver.playSound(receiver.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);

        getConversations().put(sender.getUniqueId(), receiver.getUniqueId());
        getConversations().put(receiver.getUniqueId(), sender.getUniqueId());

        SocialSpyCommand.getSocialSpyList().stream().map(Bukkit::getPlayer).filter(Objects::nonNull).forEach(p -> {
            Utils.sendNoPrefix(p, "&6&lSP &8(MSG) &7De &b" + sender.getDisplayName() + " &7a &b" + receiver.getDisplayName() + " &7> &7" + message);
        });
    }
}
