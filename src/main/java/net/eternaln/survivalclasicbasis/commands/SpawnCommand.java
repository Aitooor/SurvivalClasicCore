package net.eternaln.survivalclasicbasis.commands;

import net.eternaln.survivalclasicbasis.SurvivalClasicBasis;
import net.eternaln.survivalclasicbasis.utils.CooldownManager;
import net.eternaln.survivalclasicbasis.utils.LocationUtil;
import net.eternaln.survivalclasicbasis.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class SpawnCommand implements CommandExecutor {

    private final SurvivalClasicBasis plugin;

    private final CooldownManager cooldownManager = new CooldownManager();

    public SpawnCommand(SurvivalClasicBasis instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileConfiguration config = plugin.getConfig();

        if (!(sender instanceof Player)) {
            if (args.length == 0) {
                Utils.log("&cDebes ser un usuario para usar esto");
                return false;
            }
            return false;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            int timeLeft = cooldownManager.getCooldown(player.getUniqueId());

            if(config.getString("spawn-location") == null) {
                Utils.send(player, "&cAvisa a un staff");
                return true;
            }

            if (!player.hasPermission("survivalclasicbasis.cooldown.bypass")) {
                if(LocationUtil.parseToLocation(config.getString("spawn-location")) != null) {
                    if (timeLeft == 0) {
                        player.teleport(LocationUtil.parseToLocation(config.getString("spawn-location")));
                        Utils.send(player, config.getString("spawn"));
                        cooldownManager.setCooldown(player.getUniqueId(), CooldownManager.DEFAULT_COOLDOWN);
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                int timeLeft = cooldownManager.getCooldown(player.getUniqueId());
                                cooldownManager.setCooldown(player.getUniqueId(), --timeLeft);
                                if (timeLeft == 0) {
                                    this.cancel();
                                }
                            }
                        }.runTaskTimer(this.plugin, 20, 20);

                        return false;
                    } else {
                        player.sendMessage(Utils.ct("&cDebes esperar &b" + timeLeft + " &csegundos"));
                    }
                }
                return true;
            } else {
                player.teleport(LocationUtil.parseToLocation(config.getString("spawn-location")));
                Utils.send(player, config.getString("spawn"));
            }

            return false;
        }
        return false;
    }
}