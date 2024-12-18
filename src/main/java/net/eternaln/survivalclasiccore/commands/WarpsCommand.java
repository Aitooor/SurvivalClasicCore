package net.eternaln.survivalclasiccore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.data.configuration.MenusFile;
import net.eternaln.survivalclasiccore.data.configuration.MessagesFile;
import net.eternaln.survivalclasiccore.menus.WarpsMenu;
import net.eternaln.survivalclasiccore.utils.Cooldown;
import net.eternaln.survivalclasiccore.utils.LocationUtil;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@CommandAlias("warp|warps")
public class WarpsCommand extends BaseCommand {

    private MessagesFile messageFile = SurvivalClasicCore.getMessagesFile();
    private MenusFile menusFile = SurvivalClasicCore.getMenusFile();
    private Cooldown<UUID> cooldown = new Cooldown<>(SurvivalClasicCore.getConfiguration().getCmdCooldown(), TimeUnit.SECONDS);


    @Default
    @CatchUnknown
    @CommandCompletion("@warps")
    public void onWarp(Player sender, @Optional String name) {
        if (!cooldown.isCooldownOver(sender.getUniqueId()) && !sender.hasPermission("survivalclasic.cooldown.bypass")) {
            String cooldownTime = cooldown.getFormattedRemainingString(sender.getUniqueId());
            Utils.send(sender, messageFile.cooldown.replace("%time%", cooldownTime));
            return;
        }
        cooldown.addToCooldown(sender.getUniqueId());
        if (name != null) {
            onWarpCommand(sender, name.toUpperCase());
        } else {
            new WarpsMenu(menusFile.warpsMenuTitle).openMenu(sender);
        }
    }

    @Subcommand("set|establece|add|agregar")
    @CommandPermission("survivalclasic.warps.set")
    @CommandCompletion("@warps")
    public void SetWarp(Player sender, String name) {
        Location warpLocation = new Location(Bukkit.getWorld(sender.getWorld().getName()), sender.getLocation().getX(), sender.getLocation().getY(), sender.getLocation().getZ(), sender.getLocation().getYaw(), sender.getLocation().getPitch());

        if (!SurvivalClasicCore.getWarpsFile().getConfig().contains("warps." + name.toUpperCase())) {
            SurvivalClasicCore.getWarpsFile().getConfig().set("warps." + name.toUpperCase(), LocationUtil.parseToString(warpLocation));
            SurvivalClasicCore.getWarpsFile().saveConfig();
            Utils.send(sender, messageFile.warpSet.replace("%warp%", name.toUpperCase()));
        } else {
            Utils.send(sender, messageFile.alreadyExistWarp.replace("%warp%", name.toUpperCase()));
        }
    }

    @Subcommand("remove|eliminar|delete|borrar")
    @CommandPermission("survivalclasic.warps.remove")
    @CommandCompletion("@warps")
    public void RemoveWarp(Player sender, String name) {
        if (SurvivalClasicCore.getWarpsFile().getConfig().contains("warps." + name.toUpperCase())) {
            SurvivalClasicCore.getWarpsFile().getConfig().set("warps." + name.toUpperCase(), null);
            SurvivalClasicCore.getWarpsFile().saveConfig();
            Utils.send(sender, messageFile.warpRemoved.replace("%warp%", name.toUpperCase()));
        } else {
            Utils.send(sender, messageFile.warpNotFound.replace("%warp%", name.toUpperCase()));
        }
    }

    public void onWarpCommand(Player sender, String warp) {
        if (warp == null) {
            new WarpsMenu(menusFile.warpsMenuTitle).openMenu(sender);
            return;
        }

        if (SurvivalClasicCore.getWarpsFile().getConfig().contains("warps." + warp)) {
            String warps = SurvivalClasicCore.getWarpsFile().getConfig().getString("warps." + warp);
            sender.teleport(LocationUtil.parseToLocation(warps));
            Utils.send(sender, messageFile.tpWarp.replace("%warp%", warp));
        } else {
            Utils.send(sender, messageFile.warpNotFound.replace("%warp%", warp));
        }
    }
}
