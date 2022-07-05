package net.eternaln.survivalclasicbasis.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasicbasis.SurvivalClasicBasis;
import net.eternaln.survivalclasicbasis.utils.Utils;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("basis|basic|survival")
public class MainCommand extends BaseCommand {

    @Default
    @CatchUnknown
    @HelpCommand
    public void help(CommandSender sender, CommandHelp help) {
        help.showHelp();
    }

    @Subcommand("reload")
    @CommandPermission("survivalclasicbasis.reload")
    public void reloadConfig(CommandSender sender) {
        SurvivalClasicBasis.getConfiguration().load();
        SurvivalClasicBasis.getWarpsFile().reloadConfig();

        sender.sendMessage(SurvivalClasicBasis.getConfiguration().getReload());
        Utils.log(SurvivalClasicBasis.getConfiguration().getReload());
    }

    @Subcommand("setSpawn")
    @CommandPermission("survivalclasicbasis.setspawn")
    public void setSpawn(Player sender) {
        SurvivalClasicBasis.getConfiguration().load();
        SurvivalClasicBasis.getConfiguration().setSpawnLocation(sender.getLocation());
        SurvivalClasicBasis.getConfiguration().save();

        Utils.send(sender, SurvivalClasicBasis.getConfiguration().getSetSpawn());
        sender.playSound(sender.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
    }
}
