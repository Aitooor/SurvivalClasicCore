package net.eternaln.survivalclasiccore.commands.admin;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.data.configuration.Configuration;
import net.eternaln.survivalclasiccore.data.configuration.MessagesFile;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("core|basis|basic")
@CommandPermission("survivalclasic.core")
public class CoreCommand extends BaseCommand {

    MessagesFile messageFile = SurvivalClasicCore.getMessagesFile();

    @Default
    @CatchUnknown
    @HelpCommand("ayuda|help")
    public void help(Player sender, CommandHelp help) {
        help.showHelp();
    }

    @Subcommand("reload")
    @CommandPermission("survivalclasic.reload")
    public void reloadConfig(CommandSender sender) {
        SurvivalClasicCore.getConfiguration().load();
        SurvivalClasicCore.getMessagesFile().load();
        SurvivalClasicCore.getWarpsFile().reloadConfig();

        sender.sendMessage(messageFile.reload);
        Utils.log(messageFile.reload);
    }

    @Subcommand("setSpawn")
    @CommandPermission("survivalclasic.setspawn")
    public void setSpawn(Player sender) {
        SurvivalClasicCore.getConfiguration().load();
        SurvivalClasicCore.getConfiguration().setSpawnLocation(sender.getLocation());
        SurvivalClasicCore.getConfiguration().save();

        Utils.send(sender, messageFile.setSpawn);
        sender.playSound(sender.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
    }
}
