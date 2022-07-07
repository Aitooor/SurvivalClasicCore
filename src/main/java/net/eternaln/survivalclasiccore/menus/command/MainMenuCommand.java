package net.eternaln.survivalclasiccore.menus.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.menus.MainMenu;
import org.bukkit.entity.Player;

@CommandAlias("menu|mainmenu|openmenu|abrirmenu")
public class MainMenuCommand extends BaseCommand {

    @Default
    public void mainMenuCommand(Player sender) {
        new MainMenu("Menu Principal").openMenu(sender);
    }

    @Subcommand("otro|other|others|otro")
    @CommandPermission("survivalclasiccore.menu.other")
    @CommandCompletion("@players")
    public void otherCommand(Player sender, Player target) {
        new MainMenu("Menu Principal").openMenu(target);
    }

}