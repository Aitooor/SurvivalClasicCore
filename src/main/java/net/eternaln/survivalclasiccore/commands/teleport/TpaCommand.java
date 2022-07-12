package net.eternaln.survivalclasiccore.commands.teleport;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CatchUnknown;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.HelpCommand;
import lombok.Getter;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.utils.Cooldown;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

@CommandAlias("tpa")
public class TpaCommand extends BaseCommand {
    @Getter
    private static final HashMap<Player, Player> requests = new HashMap();

    private final Cooldown<UUID> cooldown = new Cooldown<>(SurvivalClasicCore.getConfiguration().getCmdCooldown());

    @CatchUnknown
    @HelpCommand("ayuda|help")
    public void help(CommandHelp help) {
        help.showHelp();
    }

    @Default
    public void teleport(Player sender, String target) {
        if (!sender.hasPermission("survivalclasiccore.cooldown.bypass") && !cooldown.isCooledDown(sender.getUniqueId())) {
            sender.sendMessage(Utils.ct("&cDebes esperar &b" + cooldown.getSecondsRemaining(sender.getUniqueId()) + " &csegundos"));
            return;
        }
        Player targetPlayer = Bukkit.getPlayer(target);
        if (targetPlayer != null) {
            if (!target.equals(sender.getName())) {
                Utils.send(sender, "&fHas enviado solicitud a &b" + targetPlayer.getName());
                Utils.send(targetPlayer, "&fEl jugador &b" + sender.getName() + " &fQuiere teletrasportarse a ti\n&aPuedes aceptar usando &l/tp accept|confirm|aceptar|confirmar\n" +
                        "&cO rechazarlo usando &l/tp rechazar|deny");
                requests.put(targetPlayer, sender);
            } else {
                Utils.send(sender, "&cNo puedes teletransportarte a ti mismo");
            }
        } else {
            Utils.send(sender, "&cEste jugador no esta online");
        }
    }
}
