package net.eternaln.survivalclasiccore.data;

import de.exlll.configlib.annotation.Comment;
import de.exlll.configlib.annotation.Convert;
import de.exlll.configlib.configs.yaml.BukkitYamlConfiguration;
import de.exlll.configlib.format.FieldNameFormatters;
import lombok.Getter;
import lombok.Setter;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.data.converters.ItemStackMapConverter;
import net.eternaln.survivalclasiccore.data.converters.LocationStringConverter;
import net.eternaln.survivalclasiccore.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@Getter
public class Configuration extends BukkitYamlConfiguration {

    public int cmdCooldown = 2;

    @Comment({"", "Messages"})
    public String prefix = "&6&lETERNAL &r";
    public String noPermission = "&cNo tienes permisos para hacer eso";
    public String reload = "&aConfig recargada";
    public String tpSpawn = "&aTeletransportado al spawn";
    public String setSpawn = "&aSpawn establecido";
    public String itemGived = "&aObjeto Entregado";
    public List<String> itemHelp = Arrays.asList(
            "&8&m-----------------------------------------",
            "&6&lETERNAL &7Ayuda Items",
            "&r",
            "&b/item give &7(gold/plate) (coin/fragment)",
            "&b/objeto dar &7(oro/plata) (moneda/fragmento)",
            "&8&m-----------------------------------------"
    );
    public List<String> warpHelp = Arrays.asList(
            "&8&m-----------------------------------------",
            "&6&lETERNAL &7Warps Help",
            "&r",
            "&b/warp &7(nombre del warp)",
            "&b/warp lista|list",
            "&8&m-----------------------------------------"
    );

    @Comment({"", "Items"})
    @Convert(ItemStackMapConverter.class)
    public ItemStack goldCoin = new ItemBuilder(Material.RAW_GOLD).name("&f&lMoneda de &6&lORO").lore("&f", "&7Reluciente como el sol", "&aIntercambiable con los aldeanos").build();
    @Convert(ItemStackMapConverter.class)
    public ItemStack goldFragment = new ItemBuilder(Material.GOLD_NUGGET).name("&f&lFragmento de &6&lORO").lore("&f", "&7Se tradea para obtener &f&lMoneda de &6&lORO", "&aIntercambiable con los aldeanos").build();
    @Convert(ItemStackMapConverter.class)
    public ItemStack plateCoin = new ItemBuilder(Material.RAW_IRON).name("&f&lMoneda de &7&lPLATA").lore("&f", "&7Frio como la luna", "&aIntercambiable con los aldeanos").build();
    @Convert(ItemStackMapConverter.class)
    public ItemStack plateFragment = new ItemBuilder(Material.IRON_NUGGET).name("&f&lFragmento de &7&lPLATA").lore("&f", "&f&lMoneda de &7&lPLATA", "&aIntercambiable con los aldeanos").build();

    @Convert(LocationStringConverter.class)
    @Comment({"", "DON'T MODIFY SPAWN LOCATION"})
    @Setter
    public Location spawnLocation = new Location(Bukkit.getWorld("world"), 0, 128, 0);

    public Configuration() {
        super(
                new File(SurvivalClasicCore.getInstance().getDataFolder(), "config.yml").toPath(),
                BukkitYamlProperties.builder().setFormatter(FieldNameFormatters.LOWER_UNDERSCORE).build()
        );
        this.loadAndSave();
    }
}