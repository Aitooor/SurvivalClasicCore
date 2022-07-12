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
                19,
                "profile",
                SkullBuilder.newBuilder()
                        .setDisplayName("&aProfile")
                        .setLore("&7Click to open your profile", "&7and see your stats.", "&7and more!")
                        .setOwner(player.getName())
                        .toItemStack()
        ));

        buttons.add(new ButtonItem(
                50,
                null,
                SkullBuilder.newBuilder()
                        .setDisplayName("&c&lCerrar")
                        .setTexture("86e145e71295bcc0488e9bb7e6d6895b7f969a3b5bb7eb34a52e932bc84df5b")
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
