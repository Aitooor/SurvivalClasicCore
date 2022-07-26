package net.eternaln.survivalclasiccore.commands.social;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.data.configuration.Configuration;
import net.eternaln.survivalclasiccore.data.configuration.MessagesFile;
import net.eternaln.survivalclasiccore.managers.CooldownManager;
import net.eternaln.survivalclasiccore.utils.Cooldown;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.entity.Player;

import java.util.concurrent.TimeUnit;

@CommandAlias("web|website")
public class WebsiteCommand extends BaseCommand {

    private Configuration config = SurvivalClasicCore.getInstance().getConfiguration();
    private MessagesFile messagesFile = SurvivalClasicCore.getMessagesFile();
    private CooldownManager cooldowns = SurvivalClasicCore.getInstance().getCooldowns();
    private int cooldownConfig = config.cmdCooldown;

    @Default
    @CatchUnknown
    public void webCommand(Player player) {
        if (cooldowns.getCooldown(player.getUniqueId()) == null) {
            Utils.sendNoPrefix(player, messagesFile.websiteUrl);
            cooldowns.create(player.getUniqueId(), new Cooldown(TimeUnit.SECONDS.toMillis(cooldownConfig)));
            return;
        }
        Cooldown cooldown = cooldowns.getOrCreate(player.getUniqueId(), TimeUnit.SECONDS.toMillis(cooldownConfig));
        if (!cooldown.hasExpired()) {
            Utils.send(player, messagesFile.cooldown.replace("%time%", String.valueOf(TimeUnit.MILLISECONDS.toSeconds(cooldown.getRemaining()))));
            return;
        }
        cooldown.stop();
        cooldowns.create(player.getUniqueId(), new Cooldown(TimeUnit.SECONDS.toMillis(cooldownConfig)));
        Utils.sendNoPrefix(player, messagesFile.websiteUrl);
    }

    @Subcommand("otro|other|others|otro")
    @CommandPermission("survivalclasic.menu.other")
    @CommandCompletion("@players")
    public void otherCommand(Player sender, Player target) {
        if (cooldowns.getCooldown(sender.getUniqueId()) == null) {
            Utils.send(target, messagesFile.websiteUrl);
            Utils.send(sender, "&fHas enviado el url de web a &a" + target.getName());
            cooldowns.create(sender.getUniqueId(), new Cooldown(TimeUnit.SECONDS.toMillis(cooldownConfig)));
            return;
        }
        Cooldown cooldown = cooldowns.getOrCreate(sender.getUniqueId(), TimeUnit.SECONDS.toMillis(cooldownConfig));
        if (!cooldown.hasExpired()) {
            Utils.send(sender, messagesFile.cooldown.replace("%time%", String.valueOf(TimeUnit.MILLISECONDS.toSeconds(cooldown.getRemaining()))));
            return;
        }
        cooldown.stop();
        cooldowns.create(sender.getUniqueId(), new Cooldown(TimeUnit.SECONDS.toMillis(cooldownConfig)));
        Utils.send(target, messagesFile.websiteUrl);
        Utils.send(sender, "&fHas enviado el url de web a &a" + target.getName());
    }

}
