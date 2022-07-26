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

@CommandAlias("youtube|yt")
public class YoutubeCommand extends BaseCommand {

    private MessagesFile messagesFile = SurvivalClasicCore.getMessagesFile();
    private Configuration config = SurvivalClasicCore.getInstance().getConfiguration();
    private CooldownManager cooldowns = SurvivalClasicCore.getInstance().getCooldowns();
    private int cooldownConfig = config.cmdCooldown;

    @Default
    @CatchUnknown
    public void youtubeCommand(Player player) {
        if (cooldowns.getCooldown(player.getUniqueId()) == null) {
            Utils.sendNoPrefix(player, messagesFile.youtubeUrl);
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
        Utils.sendNoPrefix(player, messagesFile.youtubeUrl);
    }

    @Subcommand("otro|other|others|otro")
    @CommandPermission("survivalclasic.menu.other")
    @CommandCompletion("@players")
    public void otherCommand(Player sender, Player target) {
        if (cooldowns.getCooldown(sender.getUniqueId()) == null) {
            Utils.send(target, messagesFile.youtubeUrl);
            Utils.send(sender, "&fHas enviado el url de Youtube a &a" + target.getName());
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
        Utils.send(target, messagesFile.youtubeUrl);
        Utils.send(sender, "&fHas enviado el url de Youtube a &a" + target.getName());
    }

}
