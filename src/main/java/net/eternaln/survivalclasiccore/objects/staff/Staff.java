package net.eternaln.survivalclasiccore.objects.staff;

import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.utils.PlayerUtil;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.Map;
import java.util.UUID;

@Getter @Setter
public class Staff {

    @Getter
    private static Map<UUID, Staff> staffs = Maps.newHashMap();

    private UUID uuid;
    private boolean vanished;
    private boolean staffMode;
    private boolean flying;
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
        setStaffMode(true);
        enableVanish(true);

        Player player = getPlayer();
        player.setGameMode(GameMode.CREATIVE);

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
        disableVanish(true);

        Player player = getPlayer();
        player.setGameMode(GameMode.SURVIVAL);

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
                return;
            }
            else {
                player.hidePlayer(SurvivalClasicCore.getInstance(), online);
            }
        }

        if (message) {
            return;
        }
    }

    public void disableVanish(boolean message) {
        setVanished(false);

        Player player = getPlayer();

        for (Player online : Bukkit.getServer().getOnlinePlayers()) {
            player.showPlayer(SurvivalClasicCore.getInstance(), online);
        }

        if (message) {
            return;
        }
    }

    public void enableFly(boolean message) {
        Player player = getPlayer();
        if(!player.getAllowFlight()) {
            player.setAllowFlight(true);
            setFlying(true);
            if(message) {
                Utils.send(player, "&aFly Activado");
            }
        }
    }

    public void disableFly(boolean message) {
        Player player = getPlayer();
        if(player.getAllowFlight()) {
            player.setAllowFlight(false);
            setFlying(false);
            if(message) {
                Utils.send(player, "&cFly Desactivado");
            }
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