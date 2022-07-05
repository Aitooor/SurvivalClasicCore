package net.eternaln.survivalclasicbasis.listeners;

import net.eternaln.survivalclasicbasis.SurvivalClasicBasis;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;


public class SocialSpyListeners implements Listener {

    private final SurvivalClasicBasis plugin;

    public SocialSpyListeners(SurvivalClasicBasis instance) {
        plugin = instance;
    }
    
    FileConfiguration config = SurvivalClasicBasis.getInstance().getConfig();

    @EventHandler
    public void onCommandPrePr(PlayerCommandPreprocessEvent event){
        Player player = event.getPlayer();
        if (plugin.socialSpyToggle) {
            for (Player p : plugin.getServer().getOnlinePlayers()) {
                if (plugin.reply.containsKey(player.getUniqueId())) {
                    if (p.hasPermission("survivalclasicbasis.socialspy")) {
                        p.sendMessage(player.getName() + " said " + event.getMessage());
                    }
                }
            }
        }
    }

}
