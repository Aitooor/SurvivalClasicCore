package net.eternaln.survivalclasiccore.commands.admin;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("core|basis|basic")
public class CoreCommand extends BaseCommand {

    @Default
    @CatchUnknown
    @HelpCommand("help|ayuda")
    public void help(Player sender, CommandHelp help) {
        help.showHelp();
    }

    @Subcommand("reload")
    @CommandPermission("survivalclasiccore.reload")
    public void reloadConfig(CommandSender sender) {
        SurvivalClasicCore.getConfiguration().load();
        SurvivalClasicCore.getWarpsFile().reloadConfig();

        sender.sendMessage(SurvivalClasicCore.getConfiguration().getReload());
        Utils.log(SurvivalClasicCore.getConfiguration().getReload());
    }

    @Subcommand("setSpawn")
    @CommandPermission("survivalclasiccore.setspawn")
    public void setSpawn(Player sender) {
        SurvivalClasicCore.getConfiguration().load();
        SurvivalClasicCore.getConfiguration().setSpawnLocation(sender.getLocation());
        SurvivalClasicCore.getConfiguration().save();

        Utils.send(sender, SurvivalClasicCore.getConfiguration().getSetSpawn());
        sender.playSound(sender.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
    }
}
