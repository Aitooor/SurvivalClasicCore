package net.eternaln.survivalclasiccore.commands.teleport;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.utils.Cooldown;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

@CommandAlias("tpaccept")
public class TpacceptCommand extends BaseCommand {

    private final Cooldown<UUID> cooldown = new Cooldown<>(SurvivalClasicCore.getConfiguration().getCmdCooldown());

    HashMap<Player, Player> requests = TpaCommand.getRequests();

    @CatchUnknown
    @HelpCommand("ayuda|help")
    public void help(CommandHelp help) {
        help.showHelp();
    }

    @Default
    public void teleportAccept(Player sender) {
        if (requests.containsKey(sender)) {
            Utils.send(sender,"&fHas &a&lACEPTADO &fla petición de &b" + requests.get(sender).getPlayer().getDisplayName());
            requests.get(sender).sendMessage(Utils.ct("&fEl jugador &b" + sender.getName() + " &fha &a&lACEPTADO &ftu petición"));
            sender.teleport(requests.get(sender).getLocation());
        } else {
            Utils.send(sender,"&cNo tienes ninguna peticiones de teletransporte");
        }
    }

}
