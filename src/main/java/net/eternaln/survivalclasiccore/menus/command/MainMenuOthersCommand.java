package net.eternaln.survivalclasiccore.menus.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.data.configuration.MenusFile;
import net.eternaln.survivalclasiccore.menus.MainMenu;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("menuothers")
@Private
@CommandPermission("survivalclasic.menu.other")
public class MainMenuOthersCommand extends BaseCommand {

    MenusFile menusFile = SurvivalClasicCore.getMenusFile();

    @Default
    @CommandCompletion("@players")
    public void otherCommand(CommandSender sender, String target) {
        Player targetPlayer = Bukkit.getPlayer(target);
        targetPlayer.performCommand("menu");
    }

}