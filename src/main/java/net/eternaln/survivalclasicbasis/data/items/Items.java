package net.eternaln.survivalclasicbasis.data.items;

import de.exlll.configlib.annotation.ConfigurationElement;
import lombok.Getter;
import net.eternaln.survivalclasicbasis.utils.ItemBuilder;
import org.bukkit.Material;

@Getter @ConfigurationElement
public class Items {

    private final GoldItems goldItems;
    private final PlateItems plateItems;

    public Items() {
        goldItems = new GoldItems(
                new ItemBuilder(Material.RAW_GOLD).name("&f&lMoneda de &6&lORO").lore("&f", "&7Reluciente como el sol", "&aIntercambiable con los aldeanos").build(),
                new ItemBuilder(Material.GOLD_NUGGET).name("&f&lFragmento de &6&lORO").lore("&f", "&7Se tradea para obtener &f&lMoneda de &6&lORO", "&aIntercambiable con los aldeanos").build()
        );

        plateItems = new PlateItems(
                new ItemBuilder(Material.RAW_IRON).name("&f&lMoneda de &7&lPLATA").lore("&f", "&7Frio como la luna", "&aIntercambiable con los aldeanos").build(),
                new ItemBuilder(Material.IRON_NUGGET).name("&f&lFragmento de &7&lPLATA").lore("&f", "&f&lMoneda de &7&lPLATA", "&aIntercambiable con los aldeanos").build()
        );
    }
}
