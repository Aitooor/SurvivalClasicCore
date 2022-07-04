package net.eternaln.survivalclasicbasis.commands;

import net.eternaln.survivalclasicbasis.SurvivalClasicBasis;
import net.eternaln.survivalclasicbasis.utils.Utils;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemCommand implements CommandExecutor {

    private final SurvivalClasicBasis plugin;
    public ItemCommand(SurvivalClasicBasis instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileConfiguration config = plugin.getConfig();

        if (!(sender instanceof Player)) {
            if (args.length == 0) {
                Utils.log("&cDebes ser un usuario para usar esto");
                return false;
            }
            if (args[0].equalsIgnoreCase("give")) {
                Utils.log("&cDebes ser un usuario para usar esto");
                return true;
            }
            return false;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("survivalclasicbasis.item.give")) {
            Utils.send(player, config.getString("no-permissions"));
            return true;
        }

        if (args.length == 0) {
            Utils.sendNoPrefix(player, config.getString("item.help"));
            return false;
        }

        if (args[0].equalsIgnoreCase("give") || args[0].equalsIgnoreCase("dar")) {
            if(args[1].equalsIgnoreCase("gold") || args[1].equalsIgnoreCase("oro")) {
                if (args[2].equalsIgnoreCase("coin") || args[2].equalsIgnoreCase("moneda")) {
                    Utils.send(player, config.getString("item.gived"));

                    ItemStack item = new ItemStack(Material.RAW_GOLD, 1);
                    ItemMeta itemmeta = item.getItemMeta();
                    itemmeta.setDisplayName(Utils.ct(config.getString("item.gold.coin.name")));
                    itemmeta.setLore(Utils.formatList(config.getStringList("item.gold.coin.lore")));
                    item.setItemMeta(itemmeta);

                    player.getInventory().addItem(new ItemStack(item));
                }
                else if (args[2].equalsIgnoreCase("fragment") || args[2].equalsIgnoreCase("fragmento")) {
                    Utils.send(player, config.getString("item.gived"));

                    ItemStack item = new ItemStack(Material.GOLD_NUGGET, 1);
                    ItemMeta itemmeta = item.getItemMeta();
                    itemmeta.setDisplayName(Utils.ct(config.getString("item.gold.fragment.name")));
                    itemmeta.setLore(Utils.formatList(config.getStringList("item.gold.fragment.lore")));
                    item.setItemMeta(itemmeta);

                    player.getInventory().addItem(new ItemStack(item));
                }
            }
            else if(args[1].equalsIgnoreCase("plate") || args[1].equalsIgnoreCase("plata")) {
                if (args[2].equalsIgnoreCase("coin") || args[2].equalsIgnoreCase("moneda")) {
                    Utils.send(player, config.getString("item.gived"));

                    ItemStack item = new ItemStack(Material.RAW_IRON, 1);
                    ItemMeta itemmeta = item.getItemMeta();
                    itemmeta.setDisplayName(Utils.ct(config.getString("item.plate.coin.name")));
                    itemmeta.setLore(Utils.formatList(config.getStringList("item.plate.coin.lore")));
                    item.setItemMeta(itemmeta);

                    player.getInventory().addItem(new ItemStack(item));
                }
                else if (args[2].equalsIgnoreCase("fragment") || args[2].equalsIgnoreCase("fragmento")) {
                    Utils.send(player, config.getString("item.gived"));

                    ItemStack item = new ItemStack(Material.IRON_NUGGET, 1);
                    ItemMeta itemmeta = item.getItemMeta();
                    itemmeta.setDisplayName(Utils.ct(config.getString("item.plate.coin.name")));
                    itemmeta.setLore(Utils.formatList(config.getStringList("item.plate.fragment.lore")));
                    item.setItemMeta(itemmeta);

                    player.getInventory().addItem(new ItemStack(item));
                }
            }

            return true;
        }

        return false;
    }

}
