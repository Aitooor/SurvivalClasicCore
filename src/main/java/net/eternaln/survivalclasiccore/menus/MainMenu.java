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

public class MainMenu extends Menu {

    public MainMenu(String title) {
        super(title, 6);

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
                "warps",
                SkullBuilder.newBuilder()
                        .setDisplayName("&bWarps")
                        .setLore("", "&fLista de Warps", "","&7Clic para abrir")
                        .setTexture("6d821092ce5e7557451c723a0341e90b93e0564e2b01481de81eea271f04c5b6")
                        .toItemStack()
        ));

        buttons.add(new ButtonItem(
                14,
                "kits",
                SkullBuilder.newBuilder()
                        .setDisplayName("&bKits")
                        .setLore("", "&fLista de Kits", "&eKits del usuario","","&7Clic para abrir")
                        .setTexture("6979a0d1b565d7c1d2ebf47733b6f47b997d38e757e12992f4efb0603ebfc9b9")
                        .toItemStack()
        ));

        buttons.add(new ButtonItem(
                16,
                "ah",
                SkullBuilder.newBuilder()
                        .setDisplayName("&bSubastas")
                        .setLore("", "&fLista de Subastas", "&eSubastas de usuarios","","&7Clic para abrir")
                        .setTexture("f98bc63f05f6378bf29ef10e3d82acb3ceb73a720bf80f30bc576d0ad8c40cfb")
                        .toItemStack()
        ));

        buttons.add(new ButtonItem(
                32,
                "protecciones",
                SkullBuilder.newBuilder()
                        .setDisplayName("&bProtecciones")
                        .setLore("", "&fLista de protecciones que se pueden comprar", "","&7Clic para abrir")
                        .setTexture("4e21d2ca9227fdc220b32a9990843a34c7e975eec595d0f137e250c14cd97246")
                        .toItemStack()
        ));

        buttons.add(new ButtonItem(
                46,
                "social",
                SkullBuilder.newBuilder()
                        .setDisplayName("&bSocial")
                        .setLore("", "&fRedes Sociales", "","&7Clic para abrir")
                        .setTexture("6ce2643d5d82ae23c5ecf7818483c4f81c7953de2aebe5ce1f8fe72fcfbc2a0e")
                        .toItemStack()
        ));

        buttons.add(new ButtonItem(
                50,
                null,
                SkullBuilder.newBuilder()
                        .setDisplayName("&cCerrar")
                        .setTexture("6c51a49cf15b8cea465ee6f31e0c7f7064e9b8b84de2cf01d2c74b897a02dde7")
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
