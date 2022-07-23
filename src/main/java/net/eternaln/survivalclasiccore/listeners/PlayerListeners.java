package net.eternaln.survivalclasiccore.listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.annotations.Register;
import net.eternaln.survivalclasiccore.commands.admin.GodCommand;
import net.eternaln.survivalclasiccore.commands.admin.SocialSpyCommand;
import net.eternaln.survivalclasiccore.data.configuration.MessagesFile;
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

import java.util.ArrayList;

@Register
public class PlayerListeners implements Listener {

    MessagesFile messagesFile = SurvivalClasicCore.getMessagesFile();

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        event.getFormat();

        Player player = event.getPlayer();

        String rank = "%vault_prefix%&r ";
        rank = PlaceholderAPI.setPlaceholders(event.getPlayer(), rank);

        if (rank.equals("")) {
            event.setFormat(Utils.ct(player.getDisplayName() + "&7: &r" + event.getMessage()));
        } else {
            event.setFormat(Utils.ct(rank + player.getDisplayName() + "&7: &r" + event.getMessage()));
        }
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
        if(!player.hasPlayedBefore()) {
            event.getPlayer().teleport(SurvivalClasicCore.getConfiguration().getSpawnLocation());
        }
        event.setJoinMessage(null);


        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if (onlinePlayer.hasMetadata("survivalclasiccore.vanish") || onlinePlayer.hasPermission("survivalclasiccore.vanish.see")) {
                    player.hidePlayer(SurvivalClasicCore.getInstance(), onlinePlayer);
            }
        }

        String name = player.getDisplayName();
        String rank = "%vault_prefix%&r ";
        rank = PlaceholderAPI.setPlaceholders(event.getPlayer(), rank);

        String messages = messagesFile.joinMessage;

        if (rank != null) {
            for (String message : messages.split("\n")) {
                CenteredMessage.Chat.sendCenteredMessage(player, message.replace("%player%", rank + name));
            }
        } else {
            for (String message : messages.split("\n")) {
                CenteredMessage.Chat.sendCenteredMessage(player, message.replace("%player%", name));
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);

        ArrayList<String> gods = new GodCommand().getGods();
        if(gods.contains(event.getPlayer().getUniqueId())) {
            gods.remove(event.getPlayer().getUniqueId());
        }
        if(SocialSpyCommand.getSocialSpyList().contains(event.getPlayer().getUniqueId())) {
            new SocialSpyCommand().toggleSocialSpy(event.getPlayer());
        }
        if(event.getPlayer().hasMetadata("survivalclasiccore.vanish")) {
            event.getPlayer().removeMetadata("survivalclasiccore.vanish", SurvivalClasicCore.getInstance());
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
}
