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

@CommandAlias("warps|warp")
@CommandPermission("survivalclasiccore.warps")
public class WarpsCommand extends BaseCommand {

    private final Cooldown<UUID> cooldown = new Cooldown<>(SurvivalClasicCore.getConfiguration().getCmdCooldown());

    @Default
    @CatchUnknown
    @CommandCompletion("@warps")
    public void onWarp(Player sender, String name) {
        if (!sender.hasPermission("survivalclasiccore.cooldown.bypass") && !cooldown.isCooledDown(sender.getUniqueId())) {
            sender.sendMessage(Utils.ct("&cDebes esperar &b" + cooldown.getSecondsRemaining(sender.getUniqueId()) + " &csegundos"));
            return;
        }

        if (SurvivalClasicCore.getWarpsFile().getConfig().contains("warps." + name.toLowerCase())) {
            String warp = SurvivalClasicCore.getWarpsFile().getConfig().getString("warps." + name.toLowerCase());
            sender.teleport(LocationUtil.parseToLocation(warp));
            Utils.send(sender, "&fHas sido teletransportado a &a" + name);
        } else {
            sender.sendMessage(Utils.ct("&cEl warp no existe."));
        }
    }

    @Subcommand("set|establece|add|agregar")
    @CommandPermission("survivalclasiccore.warps.set")
    public void SetWarp(Player sender, String name) {
        if (!SurvivalClasicCore.getWarpsFile().getConfig().contains("warps." + name.toLowerCase())) {
            Location loc = sender.getLocation();
            SurvivalClasicCore.getWarpsFile().getConfig().set("warps." + name.toLowerCase(), LocationUtil.parseToString(loc));
            SurvivalClasicCore.getWarpsFile().saveConfig();
            Utils.send(sender, "&fEl warp &a" + name + " &fha sido establecido");
        } else {
            Utils.send(sender, "&cEl warp ya existe.");
        }
    }

    @Subcommand("remove|eliminar|delete|borrar")
    @CommandPermission("survivalclasiccore.warps.remove")
    public void RemoveWarp(Player sender, String name) {
        if (SurvivalClasicCore.getWarpsFile().getConfig().contains("warps." + name.toLowerCase())) {
            SurvivalClasicCore.getWarpsFile().getConfig().set("warps." + name.toLowerCase(), null);
            SurvivalClasicCore.getWarpsFile().saveConfig();
            Utils.send(sender, "&fWarp &c" + name + " &fborrado");
        } else {
            Utils.send(sender, "&cEl warp no existe.");
        }
    }
}
