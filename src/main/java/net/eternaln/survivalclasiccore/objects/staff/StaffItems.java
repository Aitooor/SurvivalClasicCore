package net.eternaln.survivalclasiccore.objects.staff;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.eternaln.survivalclasiccore.utils.Utils;
import net.eternaln.survivalclasiccore.utils.items.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@Getter
@AllArgsConstructor
public enum StaffItems {

    COMPASS("&bBrújula", new ItemStack(Material.COMPASS),0 ,1),
    INSPECTOR("&cMirar inventario", new ItemStack(Material.BOOK),0,8),
    WORLD_EDIT("&bWorld Edit", new ItemStack(Material.WOODEN_AXE),0,7),
    FREEZE("&cCongelar", new ItemStack(Material.PACKED_ICE), 0,4),
    RANDOM_TELEPORT("&bRPT a un User", new ItemStack(Material.CLOCK), 0,2),
    VANISH_OFF("&fVanish &c&lDESACTIVADO", new ItemStack(Material.INK_SAC), 8,0),
    VANISH_ON("&fVanish &a&lACTIVADO", new ItemStack(Material.GLOW_INK_SAC), 10,0);

    private final String displayName;
    private final ItemStack itemStack;
    private final int data;
    private final int slot;

    public ItemStack getItem() {
        return new ItemBuilder(this.itemStack)
                .name(Utils.ct(this.displayName))
                .data(this.data)
                .build();
    }

    public boolean canUse(ItemStack itemStack) {
        return itemStack.isSimilar(getItem());
    }

    public static void giveItems(Staff staff) {
        for (StaffItems item : StaffItems.values()) {
            staff.getPlayer().getInventory().setItem(item.getSlot(), item.getItem());
        }

        staff.getPlayer().getInventory().setArmorContents(staff.getArmorStaff());
        staff.getPlayer().updateInventory();
    }
}
