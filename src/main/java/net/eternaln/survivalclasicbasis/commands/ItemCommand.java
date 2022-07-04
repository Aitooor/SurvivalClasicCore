package net.eternaln.survivalclasicbasis.commands;

import net.eternaln.survivalclasicbasis.SurvivalClasicBasis;
import net.eternaln.survivalclasicbasis.utils.LocationUtil;
import net.eternaln.survivalclasicbasis.utils.Utils;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ItemCommand implements CommandExecutor {

    private final SurvivalClasicBasis plugin;

    public ItemCommand(SurvivalClasicBasis instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            if (args.length == 0) return false;
            if (args[0].equalsIgnoreCase("give")) {
                Utils.log("&cDebes ser un usuario para usar esto");
                return true;
            }
            return false;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("survivalclasicbasis.item.give")) {
            Utils.send(player, "&cNo puedes hacer eso.");
            return true;
        }

        if (args.length == 0) return false;

        if (args[0].equalsIgnoreCase("give")) {
            if(args[1].equalsIgnoreCase("gold") || args[1].equalsIgnoreCase("oro")) {
                if (args[2].equalsIgnoreCase("coin") || args[2].equalsIgnoreCase("moneda")) {
                    Utils.send(player, "&aObjeto entregado.");

                    ItemStack item = new ItemStack(Material.RAW_GOLD, 1);
                    ItemMeta itemmeta = item.getItemMeta();
                    itemmeta.setDisplayName(Utils.ct("&f&lMoneda de &6&lORO"));
                    ArrayList<String> lore = new ArrayList<String>();
                    lore.add("Test");
                    itemmeta.setLore(lore);
                    item.setItemMeta(itemmeta);

                    player.getInventory().addItem(new ItemStack(item));
                }
                else if (args[2].equalsIgnoreCase("fragment") || args[2].equalsIgnoreCase("fragmento")) {
                    Utils.send(player, "&aObjeto entregado.");

                    ItemStack item = new ItemStack(Material.GOLD_NUGGET, 1);
                    ItemMeta itemmeta = item.getItemMeta();
                    itemmeta.setDisplayName(Utils.ct("&f&lFragmento de &6&lORO"));
                    ArrayList<String> lore = new ArrayList<String>();
                    lore.add("Test");
                    itemmeta.setLore(lore);
                    item.setItemMeta(itemmeta);

                    player.getInventory().addItem(new ItemStack(item));
                }
            }
            else if(args[1].equalsIgnoreCase("plate") || args[1].equalsIgnoreCase("plata")) {
                if (args[2].equalsIgnoreCase("coin") || args[2].equalsIgnoreCase("moneda")) {
                    Utils.send(player, "&aObjeto entregado.");

                    ItemStack item = new ItemStack(Material.RAW_IRON, 1);
                    ItemMeta itemmeta = item.getItemMeta();
                    itemmeta.setDisplayName(Utils.ct("&f&lMoneda de &7&lPLATA"));
                    ArrayList<String> lore = new ArrayList<String>();
                    lore.add(Utils.ct("&f&lMoneda de &6&lORO"));
                    itemmeta.setLore(lore);
                    item.setItemMeta(itemmeta);

                    player.getInventory().addItem(new ItemStack(item));
                }
                else if (args[2].equalsIgnoreCase("fragment") || args[2].equalsIgnoreCase("fragmento")) {
                    Utils.send(player, "&aObjeto entregado.");

                    ItemStack item = new ItemStack(Material.IRON_NUGGET, 1);
                    ItemMeta itemmeta = item.getItemMeta();
                    itemmeta.setDisplayName(Utils.ct("&f&lFragmento de &7&lPLATA"));
                    ArrayList<String> lore = new ArrayList<String>();
                    lore.add("Test");
                    itemmeta.setLore(lore);
                    item.setItemMeta(itemmeta);

                    player.getInventory().addItem(new ItemStack(item));
                }
            }

            return true;
        }

        return false;
    }

}
