package net.eternaln.survivalclasiccore.listeners.staffmode;

import net.eternaln.survivalclasiccore.managers.annotations.Register;
import net.eternaln.survivalclasiccore.objects.freeze.Freeze;
import net.eternaln.survivalclasiccore.objects.staff.Staff;
import net.eternaln.survivalclasiccore.objects.staff.StaffItems;
import net.eternaln.survivalclasiccore.utils.PlayerUtil;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.ThreadLocalRandom;

@Register
public class StaffItemsListener implements Listener {

    @EventHandler
    private void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        Staff staff = Staff.getStaff(event.getPlayer().getUniqueId());

        if (staff != null && staff.isStaffMode()) {

            if (event.getRightClicked() instanceof Player) {
                Player target = (Player) event.getRightClicked();

                if (StaffItems.FREEZE.canUse(staff.getPlayer().getItemInHand())) {
                    Freeze freeze;

                    if (Freeze.getFreezes().get(target.getUniqueId()) == null) {
                        freeze = new Freeze(target.getUniqueId());
                    }
                    else {
                        freeze = Freeze.getFreezes().get(target.getUniqueId());
                    }

                    freeze.setStaff(staff);

                    if (freeze.isFrozen()) {
                        freeze.unFreezePlayer(true);
                    }
                    else {
                        freeze.freezePlayer(true);
                    }
                }
                else if (StaffItems.INSPECTOR.canUse(staff.getPlayer().getItemInHand())) {
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
                ItemStack item = staff.getPlayer().getInventory().getItemInHand();

                if (item == null || item.getType().equals(Material.AIR)) return;

                if (StaffItems.RANDOM_TELEPORT.canUse(item)) {
                    if (PlayerUtil.getOnlinePlayers().isEmpty()) {
                        staff.getPlayer().sendMessage(Utils.ct("&cNo han sido encontrados jugadores."));
                        return;
                    }

                    Player randomPlayer = PlayerUtil.getOnlinePlayers().get(ThreadLocalRandom.current().nextInt(PlayerUtil.getOnlinePlayers().size()));

                    if (randomPlayer != null) {
                        staff.getPlayer().teleport(randomPlayer);
                        staff.getPlayer().sendMessage(Utils.ct("&eTeletransoportado hacia &f" + randomPlayer.getName() + "&e."));
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
