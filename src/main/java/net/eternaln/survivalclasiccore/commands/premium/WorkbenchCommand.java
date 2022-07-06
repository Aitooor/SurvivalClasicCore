package net.eternaln.survivalclasiccore.commands.premium;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@CommandAlias("workbench|mesa|mesadetrabajo")
public class WorkbenchCommand extends BaseCommand {

    @Default
    @CatchUnknown
    @CommandPermission("survivalclasiccore.workbench")
    public void god(Player sender) {
        sender.openWorkbench(null, true);
    }

    @Subcommand("other|others|otros|otro")
    @CommandPermission("survivalclasiccore.workbench.other")
    public void other(Player sender, String target) {
        Player targetPlayer = Bukkit.getPlayer(target);
        targetPlayer.openWorkbench(null, true);
    }

}
