package net.eternaln.survivalclasicbasis.listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import net.eternaln.survivalclasicbasis.SurvivalClasicBasis;
import net.eternaln.survivalclasicbasis.utils.CenteredMessage;
import net.eternaln.survivalclasicbasis.utils.LocationUtil;
import net.eternaln.survivalclasicbasis.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;


public class SocialSpyListeners implements Listener {

    private final SurvivalClasicBasis plugin;

    public SocialSpyListeners(SurvivalClasicBasis instance) {
        plugin = instance;
    }
    
    FileConfiguration config = SurvivalClasicBasis.getInstance().getConfig();

    @EventHandler
    public void onCommandPrePr(PlayerCommandPreprocessEvent e){
        Player pl = e.getPlayer();
        if (plugin.socialSpyToggle) {
            for (Player p : plugin.getServer().getOnlinePlayers()) {
                if (plugin.reply.containsKey(pl.getUniqueId())) {
                    if (p.hasPermission("survivalclasicbasis.socialspy")) {
                        p.sendMessage(pl.getName() + " said " + e.getMessage());
                    }
                }
            }
        }
    }

}
