package net.eternaln.survivalclasicbasis.data.items;

import de.exlll.configlib.annotation.ConfigurationElement;
import org.bukkit.inventory.ItemStack;

@ConfigurationElement
public class GoldItems extends AbstractItem {
    public GoldItems(ItemStack fragment, ItemStack coin) {
        super(fragment, coin);
    }
}
