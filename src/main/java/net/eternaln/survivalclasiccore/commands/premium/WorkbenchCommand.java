package net.eternaln.survivalclasiccore.commands.premium;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@CommandAlias("workbench|mesa|mesadetrabajo")
public class WorkbenchCommand extends BaseCommand {

    @CatchUnknown
    @HelpCommand("ayuda|help")
    public void help(CommandHelp help) {
        help.showHelp();
    }

    @Default
    @CommandPermission("survivalclasiccore.workbench")
    public void god(Player sender) {
        sender.openWorkbench(null, true);
        Utils.send(sender, "&aAbriendo tu mesa de trabajo");
    }

    @Subcommand("otros|others|other|otro")
    @CommandPermission("survivalclasiccore.workbench.other")
    public void other(Player sender, String target) {
        Player targetPlayer = Bukkit.getPlayer(target);
        targetPlayer.openWorkbench(null, true);
        Utils.send(sender, "&fAbriendo la mesa de trabajo de &b" + targetPlayer.getName());
        Utils.send(targetPlayer, sender.getDisplayName() + " &fha abierto tu mesa de trabajo");
    }

}
