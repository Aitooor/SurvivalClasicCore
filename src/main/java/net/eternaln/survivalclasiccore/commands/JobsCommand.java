package net.eternaln.survivalclasiccore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.data.configuration.MessagesFile;
import net.eternaln.survivalclasiccore.utils.Cooldown;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@CommandAlias("trabajo|jobs|job|trabajos")
public class JobsCommand extends BaseCommand {

    private MessagesFile messagesFile = SurvivalClasicCore.getMessagesFile();
    private Cooldown<UUID> cooldown = new Cooldown<>(SurvivalClasicCore.getConfiguration().getCmdCooldown(), TimeUnit.SECONDS);

    @Default
    public void jobsCommand(Player sender) {
        if (!cooldown.isCooldownOver(sender.getUniqueId())) {
            String cooldownTime = cooldown.getSecondsRemainingString(sender.getUniqueId());
            Utils.send(sender, messagesFile.cooldown.replace("%time%", cooldownTime));
            return;
        }
        cooldown.addToCooldown(sender.getUniqueId());
        sender.performCommand("moneyhunters:job stats");
    }

    @HelpCommand
    @CatchUnknown
    public void help(Player sender) {
        if (!cooldown.isCooldownOver(sender.getUniqueId())) {
            String cooldownTime = cooldown.getSecondsRemainingString(sender.getUniqueId());
            Utils.send(sender, messagesFile.cooldown.replace("%time%", cooldownTime));
            return;
        }
        cooldown.addToCooldown(sender.getUniqueId());
        Utils.sendNoPrefix(sender, messagesFile.jobsHelp);
    }

    @Subcommand("lista|list|info|informacion")
    public void statsCommand(Player sender) {
        if (!cooldown.isCooldownOver(sender.getUniqueId())) {
            String cooldownTime = cooldown.getSecondsRemainingString(sender.getUniqueId());
            Utils.send(sender, messagesFile.cooldown.replace("%time%", cooldownTime));
            return;
        }
        cooldown.addToCooldown(sender.getUniqueId());
        sender.performCommand("moneyhunters:job jobs");
    }
}
