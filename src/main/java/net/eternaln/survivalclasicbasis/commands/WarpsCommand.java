package net.eternaln.survivalclasicbasis.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasicbasis.SurvivalClasicBasis;
import net.eternaln.survivalclasicbasis.utils.Utils;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

@CommandAlias("warps|warp")
@CommandPermission("survivalclasicbasis.warps")
public class WarpsCommand extends BaseCommand {

    @HelpCommand @Default @CatchUnknown
    public void onHelp(CommandSender sender) {
        Utils.sendNoPrefix(sender, SurvivalClasicBasis.getConfiguration().getWarpHelp().toArray(String[]::new));
    }

    @Subcommand("set|establece|add|agregar")
    @CommandPermission("survivalclasicbasis.warps.set")
    public void SetWarp (Player sender, String name) {
        Player player = sender;

        Location loc = player.getLocation();
        SurvivalClasicBasis.getInstance().getWarpsConfig().createSection(name.toLowerCase());
        // Create the section in the config for the warp (I prefer lowercase, doesn't have to be)
        ConfigurationSection cs = SurvivalClasicBasis.getInstance().getWarpsConfig().getConfigurationSection(name.toLowerCase());
        cs.set("X", loc.getX());
        cs.set("Y", loc.getY());
        cs.set("Z", loc.getZ());
        cs.set("world", loc.getWorld().getName());
        SurvivalClasicBasis.getInstance().getWarpsConfig().getString(name.toLowerCase());
        // Save all the necessary components of the location in this section, and save the config
        player.sendMessage("Created warp: " + name + " at your location");

    }

    @Subcommand("remove|eliminar|delete|borrar")
    @CommandPermission("survivalclasicbasis.warps.remove")
    public void RemoveWarp (Player sender, String name) {
        Player player = sender;

        Location loc = player.getLocation();
        SurvivalClasicBasis.getInstance().getWarpsConfig().createSection(name.toLowerCase());
        // Create the section in the config for the warp (I prefer lowercase, doesn't have to be)
        ConfigurationSection cs = SurvivalClasicBasis.getInstance().getWarpsConfig().getConfigurationSection(name.toLowerCase());
        cs.set("X", loc.getX());
        cs.set("Y", loc.getY());
        cs.set("Z", loc.getZ());
        cs.set("world", loc.getWorld().getName());
        SurvivalClasicBasis.getInstance().getWarpsConfig().set(name.toLowerCase(), null);
        // Save all the necessary components of the location in this section, and save the config
        player.sendMessage("Created warp: " + name + " at your location");
    }
}
