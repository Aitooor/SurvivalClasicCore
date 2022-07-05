package net.eternaln.survivalclasicbasis.data.items;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.inventory.ItemStack;

@Getter
@RequiredArgsConstructor
public abstract class AbstractItem {
    private final ItemStack fragment;
    private final ItemStack coin;
}
