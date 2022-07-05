package net.eternaln.survivalclasicbasis.data;

import de.exlll.configlib.annotation.Comment;
import de.exlll.configlib.annotation.Convert;
import de.exlll.configlib.configs.yaml.BukkitYamlConfiguration;
import de.exlll.configlib.format.FieldNameFormatters;
import lombok.Getter;
import net.eternaln.survivalclasicbasis.SurvivalClasicBasis;
import net.eternaln.survivalclasicbasis.data.converters.LocationStringConverter;
import net.eternaln.survivalclasicbasis.data.items.Items;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@Getter
public class Configuration extends BukkitYamlConfiguration {

    private int cmdCooldown = 2;

    @Comment({"", "Messages"})
    private String prefix = "&6&lETERNAL &r";
    private String noPermission = "&cNo tienes permisos para hacer eso";
    private String reload = "&aConfig recargada";
    private String tpSpawn = "&aTeletransportado al spawn";
    private String setSpawn = "&aSpawn establecido";
    private String itemGived = "&aObjeto Entregado";
    private List<String> itemHelp = Arrays.asList(
            "&8&m-----------------------------------------",
            "&6&lETERNAL &7Ayuda Items",
            "&r",
            "&b/itemc give &7(gold/plate) (coin/fragment)",
            "&b/objetoc dar &7(oro/plata) (moneda/fragmento)",
            "&8&m-----------------------------------------"
    );
    private List<String> warpHelp = Arrays.asList(
            "&8&m-----------------------------------------",
            "&6&lETERNAL &7Warps Help",
            "&r",
            "&b/warp &7(nombre del warp)",
            "&b/warp lista|list",
            "&8&m-----------------------------------------"
    );

    @Comment({"", "Items"})
    private Items items = new Items();

    @Convert(LocationStringConverter.class)
    @Comment({"", "DON'T MODIFY SPAWN LOCATION"})
    private Location spawnLocation = new Location(Bukkit.getWorld("world"), 0, 128, 0);

    public Configuration() {
        super(
                new File(SurvivalClasicBasis.getInstance().getDataFolder(), "config.yml").toPath(),
                BukkitYamlProperties.builder().setFormatter(FieldNameFormatters.LOWER_UNDERSCORE).build()
        );
    }
}
