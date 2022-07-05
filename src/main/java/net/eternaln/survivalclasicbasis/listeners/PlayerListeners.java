package net.eternaln.survivalclasicbasis.listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import net.eternaln.survivalclasicbasis.SurvivalClasicBasis;
import net.eternaln.survivalclasicbasis.utils.CenteredMessage;
import net.eternaln.survivalclasicbasis.utils.LocationUtil;
import net.eternaln.survivalclasicbasis.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;


public class PlayerListeners implements Listener {
    
    private final SurvivalClasicBasis plugin;
    
    public PlayerListeners(SurvivalClasicBasis instance) {
        plugin = instance;
    }
    
    FileConfiguration config = SurvivalClasicBasis.getInstance().getConfig();
    
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        event.getFormat();
        
        Player player = event.getPlayer();
        
        String rank = "%vault_prefix% ";
        rank = PlaceholderAPI.setPlaceholders(event.getPlayer(), rank);
        
        
        event.setFormat(Utils.ct(rank + player.getDisplayName() + "&7: &r" + event.getMessage()));
    }
    
    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            Player player = event.getPlayer();
            if(player == null) {
                return;
            }
            String name = "&f%player_name% ";
            name = PlaceholderAPI.setPlaceholders(event.getPlayer(), name);
            String rank = "%vault_prefix% ";
            rank = PlaceholderAPI.setPlaceholders(event.getPlayer(), rank);
            
            CenteredMessage.Chat.sendCenteredMessage(player, "TEST");
            CenteredMessage.Chat.sendCenteredMessage(player, "TEST");
        }, 2);
    }
    
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        
        player.setGameMode(GameMode.SURVIVAL);
        event.getPlayer().teleport(SurvivalClasicBasis.getConfiguration().getSpawnLocation());
        event.setJoinMessage(null);
    }
    
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);
    }
    
    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        
        if(player.getLocation().getBlockY() < 0) {
            event.getPlayer().teleport(SurvivalClasicBasis.getConfiguration().getSpawnLocation());
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Player killer = event.getEntity().getKiller();
        event.setDeathMessage(Utils.ct(Utils.getPrefixGame() + "&fEl jugador &b" + player.getDisplayName() + " &fha sido asesinado por &c" + killer.getDisplayName()));
    }
    
    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        event.getPlayer().teleport(SurvivalClasicBasis.getConfiguration().getSpawnLocation());
    }
}
