package net.eternaln.survivalclasiccore.commands.messages;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.commands.admin.SocialSpyCommand;
import net.eternaln.survivalclasiccore.utils.Cooldown;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.UUID;

@CommandAlias("reply|r|responder|contestar")
@CommandPermission("survivalclasic.tpa")
public class ReplyCommand extends BaseCommand {

    private final Cooldown<UUID> cooldown = new Cooldown<>(SurvivalClasicCore.getConfiguration().getCmdCooldown());

    @CatchUnknown
    @HelpCommand("ayuda|help")
    public void help(CommandHelp help) {
        help.showHelp();
    }

    @Default
    @CommandCompletion("@players")
    public void reply(Player sender, String message) {
        if (!sender.hasPermission("simple.message.bypass") && !cooldown.isCooledDown(sender.getUniqueId())) {
            long cooldownTime = cooldown.getSecondsRemaining(sender.getUniqueId());
            Utils.send(sender, SurvivalClasicCore.getMessagesFile().cooldown.replace("%time%", String.valueOf(cooldownTime)));
            return;
        }
        if (MessageCommand.getConversations().get(sender.getUniqueId()) == null) {
            Utils.send(sender, "&cNo hay ninguna conversación abierta");
            return;
        }

        Player receiver = Bukkit.getPlayer(MessageCommand.getConversations().get(sender.getUniqueId()));

        if (receiver == null) {
            Utils.send(sender, "&cEl jugador no esta online");
            return;
        }

        Utils.sendNoPrefix(sender, "&8(REPLY) &7" + sender.getDisplayName() + " &7-> &b" + receiver.getDisplayName() + " &7> &7" + message);
        Utils.sendNoPrefix(receiver, "&8(REPLY) &b" + sender.getDisplayName() + " &7> &7" + message);
        receiver.playSound(receiver.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);

        SocialSpyCommand.getSocialSpyList().stream().map(Bukkit::getPlayer).filter(Objects::nonNull).forEach(p -> {
            Utils.sendNoPrefix(p, "&6&lSP &8(REPLY) &7De &b" + sender.getDisplayName() + " &7a &b" + receiver.getDisplayName() + " &7> &7" + message);
        });
    }
}
