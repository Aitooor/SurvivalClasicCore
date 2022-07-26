package net.eternaln.survivalclasiccore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.data.configuration.Configuration;
import net.eternaln.survivalclasiccore.data.configuration.MenusFile;
import net.eternaln.survivalclasiccore.data.configuration.MessagesFile;
import net.eternaln.survivalclasiccore.managers.CooldownManager;
import net.eternaln.survivalclasiccore.menus.WarpsMenu;
import net.eternaln.survivalclasiccore.utils.CooldownOld;
import net.eternaln.survivalclasiccore.utils.LocationUtil;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.concurrent.TimeUnit;

@CommandAlias("warp|warps")
public class WarpsCommand extends BaseCommand {

    private Configuration config = SurvivalClasicCore.getInstance().getConfiguration();
    private MessagesFile messageFile = SurvivalClasicCore.getMessagesFile();
    private MenusFile menusFile = SurvivalClasicCore.getMenusFile();
    private CooldownManager cooldowns = SurvivalClasicCore.getInstance().getCooldowns();
    private int cooldownConfig = config.cmdCooldown;


    @Default
    @CatchUnknown
    @CommandCompletion("@warps")
    public void onWarp(Player sender, @Optional String name) {
        if (cooldowns.getCooldown(sender.getUniqueId()) == null) {
            onWarpCommand(sender, name);
            cooldowns.create(sender.getUniqueId(), new CooldownOld(TimeUnit.SECONDS.toMillis(cooldownConfig)));
            return;
        }
        CooldownOld cooldownOld = cooldowns.getOrCreate(sender.getUniqueId(), TimeUnit.SECONDS.toMillis(cooldownConfig));
        if (!cooldownOld.hasExpired()) {
            Utils.send(sender, messageFile.cooldown.replace("%time%", String.valueOf(TimeUnit.MILLISECONDS.toSeconds(cooldownOld.getRemaining()))));
            return;
        }
        cooldownOld.stop();
        cooldowns.create(sender.getUniqueId(), new CooldownOld(TimeUnit.SECONDS.toMillis(cooldownConfig)));
        onWarpCommand(sender, name);
    }

    @Subcommand("set|establece|add|agregar")
    @CommandPermission("survivalclasic.warps.set")
    @CommandCompletion("@warps")
    public void SetWarp(Player sender, String name) {
        //TODO Player tp to correct location
        Location warpLocation = new Location(Bukkit.getWorld(sender.getWorld().getName()), sender.getLocation().getX(), sender.getLocation().getY(), sender.getLocation().getZ(), sender.getLocation().getYaw(), sender.getLocation().getPitch());

        if (!SurvivalClasicCore.getWarpsFile().getConfig().contains("warps." + name)) {

            SurvivalClasicCore.getWarpsFile().getConfig().set("warps." + name, LocationUtil.parseToString(warpLocation));
            SurvivalClasicCore.getWarpsFile().saveConfig();
            Utils.send(sender, messageFile.warpSet.replace("%warp%", name));
        } else {
            Utils.send(sender, messageFile.alreadyExistWarp.replace("%warp%", name));
        }
    }

    @Subcommand("remove|eliminar|delete|borrar")
    @CommandPermission("survivalclasic.warps.remove")
    @CommandCompletion("@warps")
    public void RemoveWarp(Player sender, String name) {
        if (SurvivalClasicCore.getWarpsFile().getConfig().contains("warps." + name)) {
            SurvivalClasicCore.getWarpsFile().getConfig().set("warps." + name, null);
            SurvivalClasicCore.getWarpsFile().saveConfig();
            Utils.send(sender, messageFile.warpRemoved.replace("%warp%", name));
        } else {
            Utils.send(sender, messageFile.warpNotFound.replace("%warp%", name));
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
