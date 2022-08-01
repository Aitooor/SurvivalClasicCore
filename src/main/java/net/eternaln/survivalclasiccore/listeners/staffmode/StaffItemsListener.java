package net.eternaln.survivalclasiccore.listeners.staffmode;

import net.eternaln.survivalclasiccore.managers.annotations.Register;
import net.eternaln.survivalclasiccore.objects.staff.Staff;
import net.eternaln.survivalclasiccore.objects.staff.StaffItems;
import net.eternaln.survivalclasiccore.utils.PlayerUtil;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

@Register
public class StaffItemsListener implements Listener {

    @EventHandler
    private void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        Staff staff = Staff.getStaff(event.getPlayer().getUniqueId());

        if (staff != null && staff.isStaffMode()) {

            if (event.getRightClicked() instanceof Player) {
                Player target = (Player) event.getRightClicked();

                if (StaffItems.FREEZE.canUse(staff.getPlayer().getInventory().getItemInMainHand())) {
                    staff.getPlayer().performCommand("freeze " + target.getName());
                }
                else if (StaffItems.INSPECTOR.canUse(staff.getPlayer().getInventory().getItemInMainHand())) {
                    staff.getPlayer().openInventory(PlayerUtil.customPlayerInventory(target));
                }
            }
        }
    }

    @EventHandler
    private void onPlayerInteract(PlayerInteractEvent event) {
        Staff staff = Staff.getStaff(event.getPlayer().getUniqueId());

        if (staff != null && staff.isStaffMode()) {

            if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                ItemStack item = staff.getPlayer().getInventory().getItemInMainHand();

                if (item == null || item.getType().equals(Material.AIR)) return;

                if (StaffItems.RANDOM_TELEPORT.canUse(item)) {
                    //TODO Need improvement
                    Player target = Bukkit.getOnlinePlayers().stream()
                            .filter(p -> !p.getUniqueId().equals(staff.getPlayer().getUniqueId()))
                            .filter(p -> !p.getUniqueId().equals(staff.getUuid()))
                            .findAny().orElse(null);

                    if (target != null) {
                        staff.getPlayer().teleport(target.getLocation());

                        Utils.send(staff.getPlayer(), "&fTeletransportado a &b" + target.getName());
                    } else {
                        Utils.send(staff.getPlayer(), "&cNo hay jugadores online");
                    }
                }
                else if (StaffItems.VANISH_ON.canUse(item)) {
                    staff.disableVanish(true);
                    staff.getPlayer().getInventory().setItem(StaffItems.VANISH_OFF.getSlot(), StaffItems.VANISH_OFF.getItem());
                }
                else if (StaffItems.VANISH_OFF.canUse(item)) {
                    staff.enableVanish(true);
                    staff.getPlayer().getInventory().setItem(StaffItems.VANISH_ON.getSlot(), StaffItems.VANISH_ON.getItem());
                }
                else if (StaffItems.FLY_ON.canUse(item)) {
                    staff.disableFly(true);
                    staff.getPlayer().getInventory().setItem(StaffItems.FLY_OFF.getSlot(), StaffItems.FLY_OFF.getItem());
                }
                else if (StaffItems.FLY_OFF.canUse(item)) {
                    staff.enableFly(true);
                    staff.getPlayer().getInventory().setItem(StaffItems.FLY_ON.getSlot(), StaffItems.FLY_ON.getItem());
                }
            }
        }
    }
}
