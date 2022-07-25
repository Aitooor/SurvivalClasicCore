package net.eternaln.survivalclasiccore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.data.configuration.MessagesFile;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.entity.Player;

@CommandAlias("ayuda|help")
public class CoreHelpCommand extends BaseCommand {

    MessagesFile messageFile = SurvivalClasicCore.getMessagesFile();

    @Default
    @CatchUnknown
    public void help(Player sender) {Utils.sendNoPrefix(sender, messageFile.helpCommand);
    }

}
