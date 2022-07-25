package net.eternaln.survivalclasiccore.listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.managers.annotations.Register;
import net.eternaln.survivalclasiccore.commands.admin.GodCommand;
import net.eternaln.survivalclasiccore.commands.admin.SocialSpyCommand;
import net.eternaln.survivalclasiccore.data.configuration.MessagesFile;
import net.eternaln.survivalclasiccore.data.mongo.PlayerData;
import net.eternaln.survivalclasiccore.objects.staff.Staff;
import net.eternaln.survivalclasiccore.utils.CenteredMessage;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Register
public class PlayerListeners implements Listener {

    MessagesFile messagesFile = SurvivalClasicCore.getMessagesFile();

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        event.getFormat();

        String rank = "%vault_prefix%&r ";
        rank = PlaceholderAPI.setPlaceholders(event.getPlayer(), rank);

        if (rank.equals("")) {
            event.setFormat(Utils.ct(messagesFile.chatFormat.replace("%player%", this.playerName(player)).replace("%message%", event.getMessage())));
        } else {
            event.setFormat(Utils.ct(messagesFile.chatFormat.replace("%player%", rank + this.playerName(player)).replace("%message%", event.getMessage())));
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Staff staff = Staff.getStaff(player.getUniqueId());;

        PlayerData data = SurvivalClasicCore.getDataManager().handleDataCreation(player.getUniqueId());

        if (data.getNickName() != null) {
            player.setDisplayName(data.getNickName());
            player.setPlayerListName(Utils.ct(data.getNickName()));
        }

        if(staff.isStaffMode()) {
            player.setGameMode(GameMode.SURVIVAL);
        }
        if(!player.hasPlayedBefore()) {
            event.getPlayer().teleport(SurvivalClasicCore.getConfiguration().getSpawnLocation());
        }
        event.setJoinMessage(null);


        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if (onlinePlayer.hasMetadata("survivalclasic.vanish") || onlinePlayer.hasPermission("survivalclasic.vanish.see")) {
                    player.hidePlayer(SurvivalClasicCore.getInstance(), onlinePlayer);
            }
        }

        String name = player.getDisplayName();
        String rank = "%vault_prefix%&r ";
        rank = PlaceholderAPI.setPlaceholders(event.getPlayer(), rank);

        String messages = messagesFile.joinMessage;

        if(this.playerName(player) != null) {
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
                    CenteredMessage.Chat.sendCenteredMessage(player, message.replace("%player%", rank + name));
                }
            } else {
                for (String message : messages.split("\n")) {
                    CenteredMessage.Chat.sendCenteredMessage(player, message.replace("%player%", name));
                }
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);

        ArrayList<UUID> gods = new GodCommand().getGods();
        if(gods.contains(event.getPlayer().getUniqueId())) {
            gods.remove(event.getPlayer().getUniqueId());
        }

        List<UUID> socialSpy = SocialSpyCommand.getSocialSpyList();
        if(socialSpy.contains(event.getPlayer().getUniqueId())) {
            socialSpy.remove(event.getPlayer().getUniqueId());
        }

        if(event.getPlayer().hasMetadata("survivalclasic.vanish")) {
            event.getPlayer().removeMetadata("survivalclasic.vanish", SurvivalClasicCore.getInstance());
        }
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

    public String playerName(Player sender) {
        Player player = sender.getPlayer();
        PlayerData data = SurvivalClasicCore.getDataManager().getData(player.getUniqueId());
        if(data.getNickName() != null) {
            String playerName = data.getNickName();
            return playerName;
        } else {
            String playerName = player.getName();
            return playerName;
        }
    }
}
