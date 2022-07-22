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

public class WarpsMenu extends Menu {

    public WarpsMenu(String title) {
        super(title, 5);

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
                12,
                "warp comida",
                SkullBuilder.newBuilder()
                        .setDisplayName("&bComida")
                        .setLore("", "&fLista de Warps", "","&7Clic para abrir")
                        .setTexture("89fc9c5b8736f36f3405cbe1363d434c141cd23aac04186476af0a0f7877aef4")
                        .toItemStack()
        ));

        buttons.add(new ButtonItem(
                14,
                "warp nether",
                SkullBuilder.newBuilder()
                        .setDisplayName("&bNether")
                        .setLore("", "&fLista de Kits", "&eKits del usuario","","&7Clic para abrir")
                        .setTexture("26e6696b63738bbcc5fd973709ea90997215b0cfeece20b2659cb7b35f1bf0")
                        .toItemStack()
        ));

        buttons.add(new ButtonItem(
                16,
                "warp end",
                SkullBuilder.newBuilder()
                        .setDisplayName("&bEnd")
                        .setLore("", "&fLista de Subastas", "&eSubastas de usuarios","","&7Clic para abrir")
                        .setTexture("f4684e3e7890caf7d13762ea19eb14c5940b88fd7f077d81e6effb4f6df16c26")
                        .toItemStack()
        ));

        buttons.add(new ButtonItem(
                22,
                "warp cajas",
                SkullBuilder.newBuilder()
                        .setDisplayName("&bCajas")
                        .setLore("", "&fLista de Subastas", "&eSubastas de usuarios","","&7Clic para abrir")
                        .setTexture("47ec41e0df8e170d97f9b9af1d65edad4979c78c89b01b180f389ee08a61af82")
                        .toItemStack()
        ));

        buttons.add(new ButtonItem(
                24,
                "warp tienda",
                SkullBuilder.newBuilder()
                        .setDisplayName("&bTienda")
                        .setLore("", "&fLista de Subastas", "&eSubastas de usuarios","","&7Clic para abrir")
                        .setTexture("f2784307b892f52b92f74fa9db4984c4f0f02eb81c6752e5eba69ad67858427e")
                        .toItemStack()
        ));

        buttons.add(new ButtonItem(
                41,
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
