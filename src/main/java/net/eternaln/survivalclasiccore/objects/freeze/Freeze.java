package net.eternaln.survivalclasiccore.objects.freeze;

import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;
import net.eternaln.survivalclasiccore.objects.staff.Staff;
import net.eternaln.survivalclasiccore.objects.staff.StaffHandler;
import net.eternaln.survivalclasiccore.utils.PlayerUtil;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.UUID;

@Getter @Setter
public class Freeze {

    @Getter
    private static Map<UUID, Freeze> freezes = Maps.newHashMap();

    private UUID uuid;
    private Staff staff;
    private boolean frozen;
    private ItemStack frozenItem = new ItemStack(Material.PACKED_ICE);

    private ItemStack[] armorContents;

    public Freeze(UUID uuid) {
        this.uuid = uuid;

        freezes.put(uuid, this);
    }

    public void freezePlayer(boolean message) {
        setFrozen(true);

        Player player = getPlayer();

        setArmorContents(player.getInventory().getArmorContents());

        PlayerUtil.clear(player, true, false);
        PlayerUtil.denyMovement(player);

        player.getInventory().setHelmet(getFrozenItem());
        player.updateInventory();

        if (message) {
            StaffHandler.sendMessageAllStaff("&c" + getName() + " &7ha sido congelado por &c" + staff.getName(), false);
            player.sendMessage(Utils.ct("&cHas sido congelado por &c" + staff.getName()));
        }
    }

    public void unFreezePlayer(boolean message) {
        setFrozen(false);

        Player player = getPlayer();

        PlayerUtil.allowMovement(player);
        PlayerUtil.clear(player, true, false);

        player.getInventory().setArmorContents(getArmorContents());
        player.updateInventory();

        if (message) {
            StaffHandler.sendMessageAllStaff("&c" + getName() + " &7ha sido descongelado por &c" + staff.getName(), false);
            player.sendMessage(Utils.ct("&cHas sido descongelado por &c" + staff.getName()));
        }

        freezes.remove(uuid);
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }

    public String getName() {
        return getPlayer().getName();
    }

    public static Freeze getFreeze(UUID uuid) {
        return freezes.get(uuid);
    }
}
