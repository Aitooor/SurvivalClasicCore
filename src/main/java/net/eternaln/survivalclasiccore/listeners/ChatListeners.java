package net.eternaln.survivalclasiccore.listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.data.configuration.Configuration;
import net.eternaln.survivalclasiccore.data.configuration.MessagesFile;
import net.eternaln.survivalclasiccore.data.mongo.PlayerData;
import net.eternaln.survivalclasiccore.managers.annotations.Register;
import net.eternaln.survivalclasiccore.objects.freeze.Freeze;
import net.eternaln.survivalclasiccore.objects.staff.Staff;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@Register
public class ChatListeners implements Listener {

    private Configuration config = SurvivalClasicCore.getInstance().getConfiguration();
    private MessagesFile messagesFile = SurvivalClasicCore.getMessagesFile();

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        Freeze freeze = Freeze.getFreeze(event.getPlayer().getUniqueId());

        event.getFormat();

        String rank = "%vault_prefix%";
        rank = PlaceholderAPI.setPlaceholders(event.getPlayer(), rank);

        String clan = "%clans_clan_tag%";
        clan = PlaceholderAPI.setPlaceholders(event.getPlayer(), clan);
        String clanTag = "&8[&b" + clan + "&8]";

        if (freeze != null && freeze.isFrozen()) {
            event.setCancelled(true);

            freeze.getPlayer().sendMessage(Utils.ct("&cCHAT-CONGELADO &7" + freeze.getName() + ": &f" + event.getMessage()));

            Staff staff = freeze.getStaff();

            if (staff != null) {
                staff.getPlayer().sendMessage(Utils.ct("&cCHAT-CONGELADO &7" + freeze.getName() + ": &f" + event.getMessage()));
            }
        }

        if (rank.equals("") && !clan.equals("")) {
            event.setFormat(Utils.ct(messagesFile.chatFormat
                    .replace("%player%", clanTag + " " + this.playerName(player))
                    .replace("%message%", event.getMessage())));
        }

        if (clan.equals("") && !rank.equals("")) {
            event.setFormat(Utils.ct(messagesFile.chatFormat
                    .replace("%player%", rank + " " + this.playerName(player))
                    .replace("%message%", event.getMessage())));
        }

        if (clan.equals("") && rank.equals("")) {
            event.setFormat(Utils.ct(messagesFile.chatFormat
                    .replace("%player%", this.playerName(player))
                    .replace("%message%", event.getMessage())));
        }

        if (!clan.equals("") && !rank.equals("")) {
            event.setFormat(Utils.ct(messagesFile.chatFormat
                    .replace("%player%", clanTag + " " + rank + " " + this.playerName(player))
                    .replace("%message%", event.getMessage())));
        }
    }

    public String playerName(Player sender) {
        PlayerData data = SurvivalClasicCore.getDataManager().getData(sender.getUniqueId());
        return data.getNickName() != null ? data.getNickName() : sender.getName();
    }

}
