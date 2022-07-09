package net.eternaln.survivalclasiccore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.utils.Cooldown;
import net.eternaln.survivalclasiccore.utils.LocationUtil;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

@CommandAlias("casa|home|casas|homes")
public class HomesCommand extends BaseCommand {

    private final Cooldown<UUID> cooldown = new Cooldown<>(SurvivalClasicCore.getConfiguration().getCmdCooldown());

    @Default
    @CatchUnknown
    public void onHome(Player sender, String name) {
        if (!sender.hasPermission("survivalclasiccore.cooldown.bypass") && !cooldown.isCooledDown(sender.getUniqueId())) {
            sender.sendMessage(Utils.ct("&cDebes esperar &b" + cooldown.getSecondsRemaining(sender.getUniqueId()) + " &csegundos"));
            return;
        }

        if (!(SurvivalClasicCore.getHomesFile().getConfig().getString(sender.getName() + "." + name.toLowerCase()) == null)) {
            String home = SurvivalClasicCore.getHomesFile().getConfig().getString(sender.getName() + "." + name.toLowerCase());
            sender.teleport(LocationUtil.parseToLocation(home));
            Utils.send(sender, "&fHas sido teletransportado a &a" + name);
        } else {
            sender.sendMessage(Utils.ct("&cLa casa no existe"));
        }
    }

    @Subcommand("establece|set|add|agregar")
    public void setHome(Player sender, String name) {
        if (!sender.hasPermission("survivalclasiccore.cooldown.bypass") && !cooldown.isCooledDown(sender.getUniqueId())) {
            sender.sendMessage(Utils.ct("&cDebes esperar &b" + cooldown.getSecondsRemaining(sender.getUniqueId()) + " &csegundos"));
            return;
        }

        if (SurvivalClasicCore.getHomesFile().getConfig().getString(sender.getName() + "." + name.toLowerCase()) == null) {
            SurvivalClasicCore.getHomesFile().getConfig().set(sender.getName() + "." + name.toLowerCase(), LocationUtil.parseToString(sender.getLocation()));
            SurvivalClasicCore.getHomesFile().saveConfig();
            sender.sendMessage(Utils.ct("&fHas establecido la casa &a" + name));
        } else {
            sender.sendMessage(Utils.ct("&cLa casa ya existe"));
        }
    }

    @Subcommand("eliminar|remove|delete|borrar")
    public void removeHome(Player sender, String name) {
        if (!sender.hasPermission("survivalclasiccore.cooldown.bypass") && !cooldown.isCooledDown(sender.getUniqueId())) {
            sender.sendMessage(Utils.ct("&cDebes esperar &b" + cooldown.getSecondsRemaining(sender.getUniqueId()) + " &csegundos"));
            return;
        }

        if (SurvivalClasicCore.getHomesFile().getConfig().getString(sender.getName() + "." + name.toLowerCase()) != null) {
            SurvivalClasicCore.getHomesFile().getConfig().set(sender.getName() + "." + name.toLowerCase(), null);
            SurvivalClasicCore.getHomesFile().saveConfig();
            sender.sendMessage(Utils.ct("&fHas eliminado la casa &a" + name));
        } else {
            sender.sendMessage(Utils.ct("&cLa casa no existe"));
        }
    }
}
