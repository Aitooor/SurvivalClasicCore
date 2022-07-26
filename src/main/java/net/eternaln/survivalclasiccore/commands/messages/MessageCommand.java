package net.eternaln.survivalclasiccore.commands.messages;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import lombok.Getter;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.commands.admin.SocialSpyCommand;
import net.eternaln.survivalclasiccore.data.configuration.Configuration;
import net.eternaln.survivalclasiccore.data.configuration.MessagesFile;
import net.eternaln.survivalclasiccore.managers.CooldownManager;
import net.eternaln.survivalclasiccore.utils.Cooldown;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.checkerframework.checker.units.qual.C;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@CommandAlias("msg|w|whisper|tell|hablar|pm|md")
@CommandPermission("simple.message")
public class MessageCommand extends BaseCommand {

    private Configuration config = SurvivalClasicCore.getInstance().getConfiguration();
    private MessagesFile messageFile = SurvivalClasicCore.getMessagesFile();
    private CooldownManager cooldowns = SurvivalClasicCore.getInstance().getCooldowns();
    private int cooldownConfig = config.cmdCooldown;

    @Getter
    private static final HashMap<UUID, UUID> conversations = new HashMap<>();

    @CatchUnknown
    @HelpCommand("ayuda|help")
    public void help(Player sender, CommandHelp help) {
        if (cooldowns.getCooldown(sender.getUniqueId()) == null) {
            help.showHelp();
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
        help.showHelp();
    }

    @Default
    @CommandCompletion("@players @players")
    public void msg(Player sender, Player target, String message) {
        if (cooldowns.getCooldown(sender.getUniqueId()) == null) {
            onMsg(sender, target, message);
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
        onMsg(sender, target, message);
    }

    private void onMsg(Player sender, Player target, String message) {
        if (target == null) {
            Utils.send(sender, "&cEl jugador no esta online");
            return;
        }
        if(target.equals(sender.getName())) {
            Utils.send(sender, "&cNo puedes enviar mensajes a ti mismo");
            return;
        }

        Utils.sendNoPrefix(sender, "&8(MSG) &7" + sender.getDisplayName() + " &7-> &b" + target.getDisplayName() + " &7> &7" + message);
        Utils.sendNoPrefix(target, "&8(MSG) &b" + sender.getDisplayName() + " &7> &7" + message);
        target.playSound(target.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);

        getConversations().put(sender.getUniqueId(), target.getUniqueId());
        getConversations().put(target.getUniqueId(), sender.getUniqueId());

        SocialSpyCommand.getSocialSpyList().stream().map(Bukkit::getPlayer).filter(Objects::nonNull).forEach(p -> {
            Utils.sendNoPrefix(p, "&6&lSP &8(MSG) &7De &b" + sender.getDisplayName() + " &7a &b" + target.getDisplayName() + " &7> &7" + message);
        });
    }
}
