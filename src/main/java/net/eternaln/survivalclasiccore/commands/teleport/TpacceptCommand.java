package net.eternaln.survivalclasiccore.commands.teleport;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CatchUnknown;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.HelpCommand;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.data.configuration.MessagesFile;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.entity.Player;

import java.util.HashMap;

@CommandAlias("tpaccept")
public class TpacceptCommand extends BaseCommand {

    MessagesFile messagesFile = SurvivalClasicCore.getMessagesFile();

    HashMap<Player, Player> requests = TpaCommand.getRequests();

    @CatchUnknown
    @HelpCommand("ayuda|help")
    public void help(CommandHelp help, Player sender) {
        help.showHelp();
    }

    @Default
    public void teleportAccept(Player sender) {
        if (requests.containsKey(sender)) {
            Utils.send(sender, messagesFile.tpAccept).replace("%player%", requests.get(sender).getName());
            requests.get(sender).sendMessage(Utils.ct(messagesFile.tpAcceptTarget).replace("%player%", sender.getName()));
            sender.teleport(requests.get(sender).getLocation());
        } else {
            Utils.send(sender, messagesFile.tpAcceptNoRequest);
        }
    }

}
