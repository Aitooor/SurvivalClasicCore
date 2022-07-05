package net.eternaln.survivalclasicbasis.data.items;

import de.exlll.configlib.annotation.ConfigurationElement;
import org.bukkit.inventory.ItemStack;

@ConfigurationElement
public class PlateItems extends AbstractItem {
    public PlateItems(ItemStack fragment, ItemStack coin) {
        super(fragment, coin);
    }
}
