package net.eternaln.survivalclasiccore.menus.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.data.configuration.MenusFile;
import net.eternaln.survivalclasiccore.data.configuration.MessagesFile;
import net.eternaln.survivalclasiccore.menus.SocialMenu;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("social|redes")
@Private
public class SocialMenuCommand extends BaseCommand {

    MenusFile menusFile = SurvivalClasicCore.getMenusFile();
    MessagesFile messagesFile = SurvivalClasicCore.getMessagesFile();

    @Default
    public void mainMenuCommand(CommandSender sender) {
        new SocialMenu(menusFile.socialMenuTitle).openMenu((Player) sender);
    }

    @Subcommand("otro|other|others|otro")
    @CommandPermission("survivalclasic.menu.other")
    @CommandCompletion("@players")
    public void otherCommand(CommandSender sender, Player target) {
        new SocialMenu(menusFile.socialMenuTitle).openMenu(target);
    }

    @Subcommand("discord")
    public void discordCommand(CommandSender sender) {
        Utils.sendNoPrefix(sender, messagesFile.discordUrl);
    }
}