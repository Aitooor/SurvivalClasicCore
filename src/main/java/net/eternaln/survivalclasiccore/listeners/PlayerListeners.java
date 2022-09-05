package net.eternaln.survivalclasiccore.listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.data.configuration.Configuration;
import net.eternaln.survivalclasiccore.managers.annotations.Register;
import net.eternaln.survivalclasiccore.commands.admin.GodCommand;
import net.eternaln.survivalclasiccore.data.configuration.MessagesFile;
import net.eternaln.survivalclasiccore.data.mongo.PlayerData;
import net.eternaln.survivalclasiccore.objects.staff.Staff;
import net.eternaln.survivalclasiccore.utils.CenteredMessage;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;

import java.util.*;

@Register
public class PlayerListeners implements Listener {

    private Configuration config = SurvivalClasicCore.getInstance().getConfiguration();
    private MessagesFile messagesFile = SurvivalClasicCore.getMessagesFile();

    @EventHandler
    public void onPreLogin(AsyncPlayerPreLoginEvent event) {
        UUID player = event.getUniqueId();
        Player player1 = Bukkit.getPlayer(player);

        if (player1 != null) {
            Bukkit.getScheduler().runTaskLaterAsynchronously(SurvivalClasicCore.getInstance(), () -> {
                if (!player1.hasPlayedBefore()) {
                    player1.teleport(config.spawnLocation);
                }
            }, 4L);
        }
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        String messages = messagesFile.joinMessage;
        PlayerData data = SurvivalClasicCore.getDataManager().handleDataCreation(event.getPlayer().getUniqueId());
        Player player = event.getPlayer();

        Bukkit.getScheduler().runTaskLaterAsynchronously(SurvivalClasicCore.getInstance(), () -> {
            String rank = "%vault_prefix%&r ";
            rank = PlaceholderAPI.setPlaceholders(event.getPlayer(), rank);

            if (data.getNickName() != null) {
                player.setDisplayName(data.getNickName());
                player.setPlayerListName(Utils.ct(data.getNickName()));
            }

            if(data.getNickName() != null) {
                if (rank != null) {
                    for (String message : messages.split("\n")) {
                        CenteredMessage.Chat.sendCenteredMessage(player, message.replace("%player%", rank + this.playerName(player)));
                    }
                } else {
                    for (String message : messages.split("\n")) {
                        CenteredMessage.Chat.sendCenteredMessage(player, message.replace("%player%", this.playerName(player)));
                    }
                }
            } else {
                if(rank != null) {
                    for (String message : messages.split("\n")) {
                        CenteredMessage.Chat.sendCenteredMessage(player, message.replace("%player%", rank + event.getPlayer().getName()));
                    }
                } else {
                    for (String message : messages.split("\n")) {
                        CenteredMessage.Chat.sendCenteredMessage(player, message.replace("%player%", event.getPlayer().getName()));
                    }
                }
            }

        }, 2);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        for (Staff staff : Staff.getStaffs().values()) {
            Player pStaff = staff.getPlayer();
            if (staff.isVanished() && !player.hasPermission("survivalclasic.vanish") && !player.hasPermission("survivalclasic.staffmode")) {
                if (pStaff != null && pStaff.isOnline())
                    player.hidePlayer(SurvivalClasicCore.getInstance(), pStaff);
            } else {
                if (pStaff != null && pStaff.isOnline())
                    player.showPlayer(SurvivalClasicCore.getInstance(), pStaff);
            }
        }

        event.setJoinMessage(null);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);

        ArrayList<UUID> gods = new GodCommand().getGods();
        gods.remove(event.getPlayer().getUniqueId());

        if(event.getPlayer().hasMetadata("survivalclasic.vanish")) {
            event.getPlayer().removeMetadata("survivalclasic.vanish", SurvivalClasicCore.getInstance());
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Player killer = event.getEntity().getKiller();

        if (killer != null) {
            event.setDeathMessage(Utils.ct(Utils.getPrefixGame() + "&fEl jugador &b" + player.getDisplayName() + " &fha sido asesinado por &c" + killer.getDisplayName()));
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        event.getPlayer().teleport(SurvivalClasicCore.getConfiguration().getSpawnLocation());
    }

    public String playerName(Player sender) {
        PlayerData data = SurvivalClasicCore.getDataManager().getData(sender.getUniqueId());
        return data.getNickName() != null ? data.getNickName() : sender.getName();
    }
}
