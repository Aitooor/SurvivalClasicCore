package net.eternaln.survivalclasiccore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.utils.Cooldown;
import net.eternaln.survivalclasiccore.utils.LocationUtil;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

@CommandAlias("warps|warp")
@CommandPermission("survivalclasiccore.warps")
public class WarpsCommand extends BaseCommand {

    private final Cooldown<UUID> cooldown = new Cooldown<>(SurvivalClasicCore.getConfiguration().getCmdCooldown());

    @HelpCommand("ayuda|help")
    @CatchUnknown
    public void onHelp(Player sender, CommandHelp help) {
        help.showHelp();
        Utils.sendNoPrefix(sender, SurvivalClasicCore.getConfiguration().getWarpHelp().toArray(String[]::new));
    }

    @Default
    public void onWarp(Player sender, String name) {
        if (!sender.hasPermission("survivalclasiccore.cooldown.bypass") && !cooldown.isCooledDown(sender.getUniqueId())) {
            sender.sendMessage(Utils.ct("&cDebes esperar &b" + cooldown.getSecondsRemaining(sender.getUniqueId()) + " &csegundos"));
            return;
        }

        if (SurvivalClasicCore.getWarpsFile().getConfig().getString(name.toLowerCase()) == null) {
            Utils.send(sender, "&cEl warp no existe");
            return;
        }

        String warp = SurvivalClasicCore.getWarpsFile().getConfig().getString(name.toLowerCase());
        sender.teleport(LocationUtil.parseToLocation(warp));
    }

    @Subcommand("lista|list")
    public void listWarps(Player sender) {
        Object warps = SurvivalClasicCore.getWarpsFile().getConfig();

        sender.sendMessage("&6&lETERNAL &fLista de Warps \n" + warps);
    }

    @Subcommand("set|establece|add|agregar")
    @CommandPermission("survivalclasiccore.warps.set")
    public void SetWarp(Player sender, String name) {
        Location loc = sender.getLocation();
        SurvivalClasicCore.getWarpsFile().getConfig().set(name.toLowerCase(), LocationUtil.parseToString(loc));
        SurvivalClasicCore.getWarpsFile().saveConfig();
        sender.sendMessage("Created warp: " + name + " at your location");

    }

    @Subcommand("remove|eliminar|delete|borrar")
    @CommandPermission("survivalclasiccore.warps.remove")
    public void RemoveWarp(Player sender, String name) {
        SurvivalClasicCore.getWarpsFile().getConfig().set(name.toLowerCase(), null);
        SurvivalClasicCore.getWarpsFile().saveConfig();
        sender.sendMessage("Created warp: " + name + " at your location");
    }
}
