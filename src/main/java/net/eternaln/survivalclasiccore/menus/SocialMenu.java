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

public class SocialMenu extends Menu {

    public SocialMenu(String title) {
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
                "shop",
                SkullBuilder.newBuilder()
                        .setDisplayName("&bTienda")
                        .setLore("", "&fUrl de la Tienda", "","&7Clic para ver")
                        .setTexture("844498a0fe278956e3d04135ef4b1343d0548a7e208c61b1fb6f3b4dbc240da8")
                        .toItemStack()
        ));

        buttons.add(new ButtonItem(
                14,
                "social discord",
                SkullBuilder.newBuilder()
                        .setDisplayName("&bDiscord")
                        .setLore("", "&fUrl del Discord", "","&7Clic para ver")
                        .setTexture("3664e54e76287e3fe2bd397c098ee7a4bd0f9c88f939fde8bab78c3271a4618f")
                        .toItemStack()
        ));

        buttons.add(new ButtonItem(
                16,
                "twitter",
                SkullBuilder.newBuilder()
                        .setDisplayName("&bTwitter")
                        .setLore("", "&fUrl del Twitter", "&eTwitter del usuario","","&7Clic para ver")
                        .setTexture("dcb76166d1e1e449457b5c4436b3f48b7d768ac60f19e2c6b25ea42c4bad7c")
                        .toItemStack()
        ));

        buttons.add(new ButtonItem(
                22,
                "web",
                SkullBuilder.newBuilder()
                        .setDisplayName("&bPagina Web")
                        .setLore("", "&fUrl de la Pagina Web", "","&7Clic para ver")
                        .setTexture("3431ae7dcd1e2dd36c33a0c9a116b56e14acdaf0dafb2a04986d65aea0e35314")
                        .toItemStack()
        ));

        buttons.add(new ButtonItem(
                24,
                "tiktok",
                SkullBuilder.newBuilder()
                        .setDisplayName("&bTiktok")
                        .setLore("", "&fUrl del Tiktok", "&eTiktok del usuario","","&7Clic para ver")
                        .setTexture("bcf2105bb737638833033dd8244071e75870e2e11c2617e542e8924fb2b90180")
                        .toItemStack()
        ));

        buttons.add(new ButtonItem(
                32,
                "youtube",
                SkullBuilder.newBuilder()
                        .setDisplayName("&bYoutube")
                        .setLore("", "&fUrl del Youtube", "&eYoutube del usuario","","&7Clic para ver")
                        .setTexture("cecd041f628c005a690fc6b8237e7311bb7c3b3aac10539fefe396a4c7c783e7")
                        .toItemStack()
        ));

        buttons.add(new ButtonItem(
                50,
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
