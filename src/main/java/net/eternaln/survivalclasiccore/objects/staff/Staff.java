package net.eternaln.survivalclasiccore.objects.staff;

import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;
import net.eternaln.survivalclasiccore.commands.admin.GodCommand;
import net.eternaln.survivalclasiccore.utils.PlayerUtil;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

@Getter @Setter
public class Staff {

    @Getter
    private static Map<UUID, Staff> staffs = Maps.newHashMap();

    private UUID uuid;
    private boolean vanished;
    private boolean staffMode;
    private ItemStack[] armorContents;
    private ItemStack[] contents;

    private ItemStack[] armorStaff = new ItemStack[] {
            new ItemStack(Material.LEATHER_BOOTS, 1),
            new ItemStack(Material.LEATHER_LEGGINGS, 1),
            new ItemStack(Material.LEATHER_CHESTPLATE, 1),
            new ItemStack(Material.LEATHER_HELMET, 1)
    };

    public Staff(UUID uuid) {
        this.uuid = uuid;
        staffs.put(uuid, this);
    }

    public void enableStaffMode(boolean message) {
        Player player = getPlayer();
        Staff staff = new Staff(player.getUniqueId());

        setStaffMode(true);
        enableVanish(false);

        if(!player.getAllowFlight()) {
            player.setAllowFlight(true);
        }
        ArrayList<UUID> gods = new GodCommand().getGods();
        if(!gods.contains(player.getUniqueId())) {
            gods.add(player.getUniqueId());
        }

        setArmorContents(player.getInventory().getArmorContents());
        setContents(player.getInventory().getContents());

        PlayerUtil.clear(player, true, true);
        StaffItems.giveItems(this);

        if (message) {
            Utils.send(player,"&aStaffMode Activado");
        }
    }

    public void disableStaffMode(boolean message) {
        setStaffMode(false);
        disableVanish(false);

        Player player = getPlayer();
        player.setGameMode(GameMode.SURVIVAL);
        if(player.getAllowFlight()) {
            player.setAllowFlight(false);
        }
        ArrayList<UUID> gods = new GodCommand().getGods();
        if(gods.contains(player.getUniqueId())) {
            gods.remove(player.getUniqueId());
        }

        PlayerUtil.clear(player, true, true);

        player.getInventory().setArmorContents(getArmorContents());
        player.getInventory().setContents(getContents());

        if (message) {
            Utils.send(player,"&cStaffMode Desactivado");
        }
    }

    public void enableVanish(boolean message) {
        setVanished(true);

        Player player = getPlayer();

        for (Player online : Bukkit.getServer().getOnlinePlayers()) {
            if (Staff.getStaff(online.getUniqueId()) != null) {
                online.showPlayer(player);
            }
            else {
                online.hidePlayer(player);
            }
        }

        if (message) {
            Utils.send(player,"&aVanish Activado");
        }
    }

    public void disableVanish(boolean message) {
        setVanished(false);

        Player player = getPlayer();

        for (Player online : Bukkit.getServer().getOnlinePlayers()) {
            online.showPlayer(player);
        }

        if (message) {
            Utils.send(player, "&cVanish Desactivado");
        }
    }

    public ItemStack[] getArmorStaff() {
        ItemStack[] armor = this.armorStaff.clone();

        for (ItemStack pieces : armorStaff) {
            LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) pieces.getItemMeta();
            leatherArmorMeta.setDisplayName(Utils.ct("&8StaffMode"));
            leatherArmorMeta.setColor(Color.ORANGE);
            pieces.setItemMeta(leatherArmorMeta);
        }

        return armor;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }

    public String getName() {
        return getPlayer().getName();
    }

    public static Staff getStaff(UUID uuid) {
        return staffs.get(uuid);
    }
}