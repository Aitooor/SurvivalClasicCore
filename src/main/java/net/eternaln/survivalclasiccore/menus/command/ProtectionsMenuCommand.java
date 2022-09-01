package net.eternaln.survivalclasiccore.menus.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.data.configuration.MenusFile;
import net.eternaln.survivalclasiccore.menus.ProtectionsMenu;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("protecciones|protects|protections")
@Private
public class ProtectionsMenuCommand extends BaseCommand {

    MenusFile menusFile = SurvivalClasicCore.getMenusFile();

    @Default
    public void protectionsMenuCommand(CommandSender sender) {
        new ProtectionsMenu(menusFile.protectionsMenuTitle).openMenu((Player) sender);
    }

    @Subcommand("otro|other|others|otro")
    @CommandPermission("survivalclasic.menu.other")
    @CommandCompletion("@players")
    public void otherCommand(CommandSender sender, Player target) {
        new ProtectionsMenu(menusFile.protectionsMenuTitle).openMenu(target);
    }
}