package net.eternaln.survivalclasiccore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.data.configuration.MessagesFile;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.entity.Player;

@CommandAlias("trabajo|jobs|job|trabajos")
public class JobsCommand extends BaseCommand {

    MessagesFile messageFile = SurvivalClasicCore.getMessagesFile();

    @Default
    public void jobsCommand(Player sender) {
        sender.performCommand("moneyhunters:job stats");
    }

    @HelpCommand
    @CatchUnknown
    public void help(Player sender) {
        Utils.sendNoPrefix(sender, messageFile.jobsHelp);
    }

    @Subcommand("lista|list|info|informacion")
    public void statsCommand(Player sender) {
        sender.performCommand("moneyhunters:job jobs");
    }
}
