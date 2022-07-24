package net.eternaln.survivalclasiccore.listeners.staffmode;

import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.objects.freeze.Freeze;
import net.eternaln.survivalclasiccore.objects.staff.Staff;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class FreezeChatListener implements Listener {

    public FreezeChatListener(SurvivalClasicCore plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    private void onFrozenChat(AsyncPlayerChatEvent event) {
        Freeze freeze = Freeze.getFreeze(event.getPlayer().getUniqueId());

        if (freeze != null && freeze.isFrozen()) {
            event.setCancelled(true);

            freeze.getPlayer().sendMessage(Utils.ct("&cCHAT-CONGELADO &7" + freeze.getName() + ": &f" + event.getMessage()));

            Staff staff = freeze.getStaff();

            if (staff != null) {
                staff.getPlayer().sendMessage(Utils.ct("&cCHAT-CONGELADO &7" + freeze.getName() + ": &f" + event.getMessage()));
            }
        }
    }
}
