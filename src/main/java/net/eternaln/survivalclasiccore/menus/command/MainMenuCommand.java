package net.eternaln.survivalclasiccore.menus.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.data.configuration.MenusFile;
import net.eternaln.survivalclasiccore.menus.MainMenu;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("menu|mainmenu|openmenu|abrirmenu")
public class MainMenuCommand extends BaseCommand {

    MenusFile menusFile = SurvivalClasicCore.getMenusFile();

    @Default
    public void mainMenuCommand(CommandSender sender) {
        new MainMenu(menusFile.mainMenuTitle).openMenu((Player) sender);
    }

    @Subcommand("otro|other|others|otro")
    @CommandPermission("survivalclasic.menu.other")
    @CommandCompletion("@players")
    public void otherCommand(CommandSender sender, Player target) {
        new MainMenu(menusFile.mainMenuTitle).openMenu(target);
    }

}