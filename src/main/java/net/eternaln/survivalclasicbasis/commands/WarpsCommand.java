package net.eternaln.survivalclasicbasis.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasicbasis.SurvivalClasicBasis;
import net.eternaln.survivalclasicbasis.utils.LocationUtil;
import net.eternaln.survivalclasicbasis.utils.Utils;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("warps|warp")
@CommandPermission("survivalclasicbasis.warps")
public class WarpsCommand extends BaseCommand {

    @HelpCommand
    public void onHelp(CommandSender sender) {
        Utils.sendNoPrefix(sender, SurvivalClasicBasis.getConfiguration().getWarpHelp().toArray(String[]::new));
    }

    @Default
    @CatchUnknown
    public void onWarp(Player sender, String name) {
        if (SurvivalClasicBasis.getWarpsFile().getConfig().getString(name.toLowerCase()) == null) {
            Utils.send(sender, "&cEl warp no existe");
            return;
        }

        String warp = SurvivalClasicBasis.getWarpsFile().getConfig().getString(name.toLowerCase());
        sender.teleport(LocationUtil.parseToLocation(warp));
    }

    @Subcommand("set|establece|add|agregar")
    @CommandPermission("survivalclasicbasis.warps.set")
    public void SetWarp(Player sender, String name) {
        Location loc = sender.getLocation();
        SurvivalClasicBasis.getWarpsFile().getConfig().set(name.toLowerCase(), LocationUtil.parseToString(loc));
        SurvivalClasicBasis.getWarpsFile().saveConfig();
        sender.sendMessage("Created warp: " + name + " at your location");

    }

    @Subcommand("remove|eliminar|delete|borrar")
    @CommandPermission("survivalclasicbasis.warps.remove")
    public void RemoveWarp(Player sender, String name) {
        SurvivalClasicBasis.getWarpsFile().getConfig().set(name.toLowerCase(), null);
        SurvivalClasicBasis.getWarpsFile().saveConfig();
        sender.sendMessage("Created warp: " + name + " at your location");
    }
}
