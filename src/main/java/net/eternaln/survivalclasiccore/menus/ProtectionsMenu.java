package net.eternaln.survivalclasiccore.menus;

import net.eternaln.survivalclasiccore.managers.menus.Menu;
import net.eternaln.survivalclasiccore.managers.menus.button.Button;
import net.eternaln.survivalclasiccore.managers.menus.type.FillType;
import net.eternaln.survivalclasiccore.utils.items.ItemCreator;
import net.eternaln.survivalclasiccore.utils.skull.SkullBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Set;

public class ProtectionsMenu extends Menu {

    public ProtectionsMenu(String title) {
        super(title, 4);

        this.setFillEnabled(true);

        this.setFillItemStack(new ItemCreator(Material.GRAY_STAINED_GLASS_PANE, 1, (short) 4)
                .setDisplayName("&8Eternal")
                .toItemStack());

        this.setFillType(FillType.ALL);
    }

    @Override
    public Set<Button> getButtons(Player player) {
        Set<Button> buttons = new HashSet<>();

        buttons.add(new ButtonItem(
                11,
                "ps get 16",
                SkullBuilder.newBuilder()
                        .setDisplayName("&b16x16")
                        .setLore("", "&fCompra una proteccion de 16x16","","&7Clic para comprar")
                        .setTexture("caecf8d5a21b3c9fc8ce5a566021f3a2b0d98652dbc5daee45f7c63647ed141a")
                        .toItemStack()
        ));

        buttons.add(new ButtonItem(
                13,
                "ps get 32",
                SkullBuilder.newBuilder()
                        .setDisplayName("&b32x32")
                        .setLore("", "&fCompra una proteccion de 32x32","","&7Clic para comprar")
                        .setTexture("3f2d38efae1e0a581f258aa09ca610749d3efddf5865304f2fa18b7a1a0c3bcd")
                        .toItemStack()
        ));

        buttons.add(new ButtonItem(
                15,
                "ps get 48",
                SkullBuilder.newBuilder()
                        .setDisplayName("&b48x48")
                        .setLore("", "&fCompra una proteccion de 48x48","","&7Clic para comprar")
                        .setTexture("11350e0a606965f2ef09491ba54ee137915de58a8a28ab130a53fa8f416b28d5")
                        .toItemStack()
        ));

        buttons.add(new ButtonItem(
                17,
                "ps get 64",
                SkullBuilder.newBuilder()
                        .setDisplayName("&b64x64")
                        .setLore("", "&fCompra una proteccion de 64x64","","&7Clic para comprar")
                        .setTexture("11350e0a606965f2ef09491ba54ee137915de58a8a28ab130a53fa8f416b28d5")
                        .toItemStack()
        ));

        buttons.add(new ButtonItem(
                32,
                "menu",
                SkullBuilder.newBuilder()
                        .setDisplayName("&cAtras")
                        .setTexture("dcec807dcc1436334fd4dc9ab349342f6c52c9e7b2bf346712db72a0d6d7a4")
                        .toItemStack()
        ));

        return buttons;
    }

    private static class ButtonItem extends Button {

        private final String command;
        private final ItemStack item;

        public ButtonItem(int slot, String command, ItemStack item) {
            super(slot);

            this.command = command;
            this.item = item;
        }

        @Override
        public void onClick(InventoryClickEvent event) {
            Player player = (Player) event.getWhoClicked();
            player.closeInventory();
            if (this.command == null) {
                return;
            }
            player.performCommand(this.command);
        }

        @Override
        public ItemStack getButtonItem() {
            return this.item;
        }
    }

}
