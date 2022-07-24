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
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

@CommandAlias("warp|warps")
@CommandPermission("survivalclasic.warps")
public class WarpsCommand extends BaseCommand {

    MessagesFile messageFile = SurvivalClasicCore.getMessagesFile();
    MenusFile menusFile = SurvivalClasicCore.getMenusFile();

    private final Cooldown<UUID> cooldown = new Cooldown<>(SurvivalClasicCore.getConfiguration().getCmdCooldown());

    @Default
    @CatchUnknown
    @CommandCompletion("@warps")
    public void onWarp(CommandSender sender, @Optional String name) {
        Player player = (Player) sender;
        if (!sender.hasPermission("survivalclasic.cooldown.bypass") && !cooldown.isCooledDown(player.getUniqueId())) {
            long cooldownTime = cooldown.getSecondsRemaining(player.getUniqueId());
            Utils.send(sender, messageFile.cooldown.replace("%time%", String.valueOf(cooldownTime)));
            return;
        }

        if (name == null) {
            new WarpsMenu(menusFile.warpsMenuTitle).openMenu(player);
            return;
        }

        if (SurvivalClasicCore.getWarpsFile().getConfig().contains("warps." + name.toLowerCase())) {
            String warp = SurvivalClasicCore.getWarpsFile().getConfig().getString("warps." + name.toLowerCase());
            player.teleport(LocationUtil.parseToLocation(warp));
            Utils.send(sender, messageFile.tpWarp.replace("%warp%", name));
        } else {
            Utils.send(sender, messageFile.warpNotFound.replace("%warp%", name));
        }
    }

    @Subcommand("set|establece|add|agregar")
    @CommandPermission("survivalclasic.warps.set")
    public void SetWarp(Player sender, String name) {
        if (!sender.hasPermission("survivalclasic.cooldown.bypass") && !cooldown.isCooledDown(sender.getUniqueId())) {
            long cooldownTime = cooldown.getSecondsRemaining(sender.getUniqueId());
            Utils.send(sender, messageFile.cooldown.replace("%time%", String.valueOf(cooldownTime)));
            return;
        }
        if (!SurvivalClasicCore.getWarpsFile().getConfig().contains("warps." + name.toLowerCase())) {
            Location loc = sender.getLocation();
            SurvivalClasicCore.getWarpsFile().getConfig().set("warps." + name.toLowerCase(), LocationUtil.parseToString(loc));
            SurvivalClasicCore.getWarpsFile().saveConfig();
            Utils.send(sender, messageFile.warpSet.replace("%warp%", name));
        } else {
            Utils.send(sender, messageFile.alreadyExistWarp.replace("%warp%", name));
        }
    }

    @Subcommand("remove|eliminar|delete|borrar")
    @CommandPermission("survivalclasic.warps.remove")
    public void RemoveWarp(Player sender, String name) {
        if (!sender.hasPermission("survivalclasic.cooldown.bypass") && !cooldown.isCooledDown(sender.getUniqueId())) {
            long cooldownTime = cooldown.getSecondsRemaining(sender.getUniqueId());
            Utils.send(sender, messageFile.cooldown.replace("%time%", String.valueOf(cooldownTime)));
            return;
        }
        if (SurvivalClasicCore.getWarpsFile().getConfig().contains("warps." + name.toLowerCase())) {
            SurvivalClasicCore.getWarpsFile().getConfig().set("warps." + name.toLowerCase(), null);
            SurvivalClasicCore.getWarpsFile().saveConfig();
            Utils.send(sender, messageFile.warpRemoved.replace("%warp%", name));
        } else {
            Utils.send(sender, messageFile.warpNotFound.replace("%warp%", name));
        }
    }
}
