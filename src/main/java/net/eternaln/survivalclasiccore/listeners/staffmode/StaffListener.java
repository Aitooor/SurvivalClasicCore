package net.eternaln.survivalclasiccore.listeners.staffmode;

import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.managers.annotations.Register;
import net.eternaln.survivalclasiccore.objects.staff.Staff;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;

@Register
public class StaffListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    private void onStaffJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        Bukkit.getScheduler().runTaskLater(SurvivalClasicCore.getInstance(), () -> {
            Staff staff = new Staff(player.getUniqueId());

            if(player == null) {
                return;
            }

            if (!staff.isStaffMode() && player.hasPermission("survivalclasiccore.staff.staffmode")) {
                staff.enableStaffMode(true);
            }
            if (staff.isStaffMode() && !player.hasPermission("survivalclasiccore.staff.staffmode")) {
                staff.getPlayer().setGameMode(GameMode.SURVIVAL);
            }
        }, 20L);

        for (Staff staff : Staff.getStaffs().values()) {
            player.hidePlayer(SurvivalClasicCore.getInstance(), staff.getPlayer());
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    private void onStaffLeave(PlayerQuitEvent event) {
        Staff staff = Staff.getStaff(event.getPlayer().getUniqueId());

        if (staff != null) {
            staff.disableStaffMode(true);
            Staff.getStaffs().remove(event.getPlayer().getUniqueId());
        }
    }

    @EventHandler
    private void onStaffDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Staff staff = Staff.getStaff(event.getEntity().getUniqueId());

            if (staff != null) {
                if (staff.isStaffMode() || staff.isVanished()) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onStaffHit(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
            Staff staff = Staff.getStaff(event.getDamager().getUniqueId());

            if (staff != null && staff.isStaffMode()) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    private void onStaffBreak(BlockBreakEvent event) {
        Staff staff = Staff.getStaff(event.getPlayer().getUniqueId());

        if (staff != null && staff.isStaffMode()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    private void onStaffPlace(BlockPlaceEvent event) {
        Staff staff = Staff.getStaff(event.getPlayer().getUniqueId());

        if (staff != null && staff.isStaffMode()) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    private void onStaffInventory(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Staff staff = Staff.getStaff(player.getUniqueId());

        if (staff != null) {
            if (staff.isStaffMode()) {
                event.setCancelled(true);
                player.updateInventory();
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    private void onPlayerSwapHandItemsEvent(PlayerSwapHandItemsEvent event) {
        Player player = event.getPlayer();
        Staff staff = Staff.getStaff(player.getUniqueId());

        if (staff != null) {
            if (staff.isStaffMode()) {
                event.setCancelled(true);
                player.updateInventory();
            }
        }
    }

    @EventHandler
    private void onStaffPickupItem(PlayerPickupItemEvent event) {
        Staff staff = Staff.getStaff(event.getPlayer().getUniqueId());

        if (staff != null) {
            if (staff.isStaffMode() || staff.isVanished()) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    private void onStaffDropItem(PlayerDropItemEvent event) {
        Staff staff = Staff.getStaff(event.getPlayer().getUniqueId());

        if (staff != null && staff.isStaffMode()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    private void onStaffChangeWorld(PlayerChangedWorldEvent event) {
        Staff staff = Staff.getStaff(event.getPlayer().getUniqueId());

        if (staff != null && staff.isVanished()) {
            staff.enableVanish(false);
        }
    }
}
