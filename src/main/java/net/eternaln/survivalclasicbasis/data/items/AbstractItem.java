package net.eternaln.survivalclasicbasis.data.items;

import de.exlll.configlib.annotation.ConfigurationElement;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.inventory.ItemStack;

@Getter
@RequiredArgsConstructor
@ConfigurationElement
public abstract class AbstractItem {
    private final ItemStack fragment;
    private final ItemStack coin;
}
