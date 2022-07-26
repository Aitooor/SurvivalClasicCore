package net.eternaln.survivalclasiccore.commands;

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

@CommandAlias("ayuda|help")
public class CoreHelpCommand extends BaseCommand {

    private Configuration config = SurvivalClasicCore.getInstance().getConfiguration();
    private MessagesFile messagesFile = SurvivalClasicCore.getMessagesFile();
    private CooldownManager cooldowns = SurvivalClasicCore.getInstance().getCooldowns();
    private int cooldownConfig = config.cmdCooldown;

    @Default
    @CatchUnknown
    public void help(Player sender) {
        if (cooldowns.getCooldown(sender.getUniqueId()) == null) {
            Utils.sendNoPrefix(sender, messagesFile.helpCommand);
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
        Utils.sendNoPrefix(sender, messagesFile.helpCommand);
    }

}
