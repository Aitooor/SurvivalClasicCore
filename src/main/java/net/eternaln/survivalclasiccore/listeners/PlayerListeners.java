package net.eternaln.survivalclasiccore.listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.annotations.Register;
import net.eternaln.survivalclasiccore.data.mongo.PlayerData;
import net.eternaln.survivalclasiccore.utils.CenteredMessage;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;

@Register
public class PlayerListeners implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        event.getFormat();

        Player player = event.getPlayer();

        String rank = "%vault_prefix%&r ";
        rank = PlaceholderAPI.setPlaceholders(event.getPlayer(), rank);

        if(rank.equals("")) {
            event.setFormat(Utils.ct(player.getDisplayName() + "&7: &r" + event.getMessage()));
        } else {
            event.setFormat(Utils.ct(rank + player.getDisplayName() + "&7: &r" + event.getMessage()));
        }
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        Bukkit.getScheduler().runTaskLater(SurvivalClasicCore.getInstance(), () -> {
            Player player = event.getPlayer();
            if (player == null) {
                return;
            }
            String name = "&f%player_name%&r ";
            name = PlaceholderAPI.setPlaceholders(event.getPlayer(), name);
            String rank = "%vault_prefix%&r ";
            rank = PlaceholderAPI.setPlaceholders(event.getPlayer(), rank);
            if(rank != null) {
                CenteredMessage.Chat.sendCenteredMessage(player, "");
                CenteredMessage.Chat.sendCenteredMessage(player, "&6&lETERNAL &8| &bSúrvival Clásico &f1.19");
                CenteredMessage.Chat.sendCenteredMessage(player, "");
                CenteredMessage.Chat.sendCenteredMessage(player, "&aIP &7eternaln.net");
                CenteredMessage.Chat.sendCenteredMessage(player, "&aTIENDA &7https://tienda.eternaln.net");
                CenteredMessage.Chat.sendCenteredMessage(player, "&aDISCORD &7https://discord.com/invite/bMarGsQYfb");
                CenteredMessage.Chat.sendCenteredMessage(player, "");
                CenteredMessage.Chat.sendCenteredMessage(player, rank + name + "&aesperamos que disfrutes");
                CenteredMessage.Chat.sendCenteredMessage(player, "&b¡Empecemos la aventura!");
                CenteredMessage.Chat.sendCenteredMessage(player, "");
            } else {
                CenteredMessage.Chat.sendCenteredMessage(player, "");
                CenteredMessage.Chat.sendCenteredMessage(player, "&6&lETERNAL &8| &bSúrvival Clásico &f1.19");
                CenteredMessage.Chat.sendCenteredMessage(player, "");
                CenteredMessage.Chat.sendCenteredMessage(player, "&aIP &7eternaln.net");
                CenteredMessage.Chat.sendCenteredMessage(player, "&aTIENDA &7https://tienda.eternaln.net");
                CenteredMessage.Chat.sendCenteredMessage(player, "&aDISCORD &7https://discord.com/invite/bMarGsQYfb");
                CenteredMessage.Chat.sendCenteredMessage(player, "");
                CenteredMessage.Chat.sendCenteredMessage(player, name + "&aesperamos que disfrutes");
                CenteredMessage.Chat.sendCenteredMessage(player, "&b¡Empecemos la aventura!");
                CenteredMessage.Chat.sendCenteredMessage(player, "");
            }
        }, 2);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        PlayerData data = SurvivalClasicCore.getDataManager().handleDataCreation(player.getUniqueId());

        if (data.getNickName() != null) {
            player.setDisplayName(data.getNickName());
            player.setPlayerListName(Utils.ct(data.getNickName()));
        }

        player.setGameMode(GameMode.SURVIVAL);
        event.getPlayer().teleport(SurvivalClasicCore.getConfiguration().getSpawnLocation());
        event.setJoinMessage(null);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Player killer = event.getEntity().getKiller();

        if (killer != null)
            event.setDeathMessage(Utils.ct(Utils.getPrefixGame() + "&fEl jugador &b" + player.getDisplayName() + " &fha sido asesinado por &c" + killer.getDisplayName()));
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        event.getPlayer().teleport(SurvivalClasicCore.getConfiguration().getSpawnLocation());
    }
}
