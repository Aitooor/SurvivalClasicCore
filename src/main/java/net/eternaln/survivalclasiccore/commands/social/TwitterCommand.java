package net.eternaln.survivalclasiccore.commands.social;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.data.configuration.Configuration;
import net.eternaln.survivalclasiccore.data.configuration.MessagesFile;
import net.eternaln.survivalclasiccore.managers.CooldownManager;
import net.eternaln.survivalclasiccore.utils.CooldownOld;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.entity.Player;

import java.util.concurrent.TimeUnit;

@CommandAlias("twitter")
public class TwitterCommand extends BaseCommand {

    private Configuration config = SurvivalClasicCore.getInstance().getConfiguration();
    private MessagesFile messagesFile = SurvivalClasicCore.getMessagesFile();
    private CooldownManager cooldowns = SurvivalClasicCore.getInstance().getCooldowns();
    private int cooldownConfig = SurvivalClasicCore.getInstance().getConfiguration().cmdCooldown;
    

    @Default
    @CatchUnknown
    public void twitterCommand(Player player) {
        if (cooldowns.getCooldown(player.getUniqueId()) == null) {
            Utils.sendNoPrefix(player, messagesFile.helpCommand);
            cooldowns.create(player.getUniqueId(), new CooldownOld(TimeUnit.SECONDS.toMillis(cooldownConfig)));
            return;
        }
        CooldownOld cooldownOld = cooldowns.getOrCreate(player.getUniqueId(), TimeUnit.SECONDS.toMillis(cooldownConfig));
        if (!cooldownOld.hasExpired()) {
            Utils.send(player, messagesFile.cooldown.replace("%time%", String.valueOf(TimeUnit.MILLISECONDS.toSeconds(cooldownOld.getRemaining()))));
            return;
        }
        cooldownOld.stop();
        cooldowns.create(player.getUniqueId(), new CooldownOld(TimeUnit.SECONDS.toMillis(cooldownConfig)));
        Utils.sendNoPrefix(player, messagesFile.helpCommand);
    }

    @Subcommand("otro|other|others|otro")
    @CommandPermission("survivalclasic.menu.other")
    @CommandCompletion("@players")
    public void otherCommand(Player sender, Player target) {
        if (cooldowns.getCooldown(sender.getUniqueId()) == null) {
            Utils.send(target, messagesFile.twitterUrl);
            Utils.send(sender, "&fHas enviado el url de Twitter a &a" + target.getName());
            cooldowns.create(sender.getUniqueId(), new CooldownOld(TimeUnit.SECONDS.toMillis(cooldownConfig)));
            return;
        }
        CooldownOld cooldownOld = cooldowns.getOrCreate(sender.getUniqueId(), TimeUnit.SECONDS.toMillis(cooldownConfig));
        if (!cooldownOld.hasExpired()) {
            Utils.send(sender, messagesFile.cooldown.replace("%time%", String.valueOf(TimeUnit.MILLISECONDS.toSeconds(cooldownOld.getRemaining()))));
            return;
        }
        cooldownOld.stop();
        cooldowns.create(sender.getUniqueId(), new CooldownOld(TimeUnit.SECONDS.toMillis(cooldownConfig)));
        Utils.send(target, messagesFile.twitterUrl);
        Utils.send(sender, "&fHas enviado el url de Twitter a &a" + target.getName());
    }

}
