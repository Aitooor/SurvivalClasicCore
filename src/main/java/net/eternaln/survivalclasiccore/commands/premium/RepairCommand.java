package net.eternaln.survivalclasiccore.commands.premium;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.Repairable;

@CommandAlias("reparar|repair|fix")
@CommandPermission("survivalclasiccore.repair")
public class RepairCommand extends BaseCommand {

    @CatchUnknown
    @HelpCommand("ayuda|help")
    public void help(CommandHelp help) {
        help.showHelp();
    }

    @Subcommand("yo|me")
    public class RepairMeCommand extends BaseCommand {
        @Default
        public void repair(Player sender) {
            ItemMeta im = sender.getInventory().getItemInMainHand().getItemMeta();
            if (im instanceof Damageable) {
                ((Damageable) im).setDamage(0);
                Utils.send(sender, "&aTu arma ha sido reparada");
            } else {
                Utils.send(sender, "&cTu arma no puede ser reparada");
            }

        }

        @Subcommand("todo|all|todos|all")
        @CommandPermission("survivalclasiccore.repair.all")
        @CommandCompletion("@players")
        public void all(Player sender) {
            for(ItemStack items : sender.getInventory().getContents()) {
                if(items instanceof Damageable) {
                    ((Damageable) items.getItemMeta()).setDamage(0);
                }

                for(ItemStack itemss : sender.getInventory().getArmorContents()) {
                    if(itemss instanceof Damageable) {
                        ((Damageable) itemss.getItemMeta()).setDamage(0);
                    }
                }
            }
        }
    }

    @Subcommand("otro|other|otros|others")
    @CommandPermission("survivalclasiccore.repair.other")
    public class RepairOthersCommand extends BaseCommand {
        @Default
        public void repair(Player sender, Player target) {
            ItemMeta im = target.getInventory().getItemInMainHand().getItemMeta();
            if (im instanceof Damageable) {
                ((Damageable) im).setDamage(0);
                target.getInventory().getItemInMainHand().setItemMeta(im);
                Utils.send(sender, "&aEl arma de " + target.getName() + " ha sido reparada");
                Utils.send(target, "&fReparado el objeto por &b" + target.getName());
            } else {
                Utils.send(target, "&cEl arma de &b" + target.getName() + " &cno puede ser reparada");
            }
        }

        @Subcommand("todo|all|todos|all")
        @CommandCompletion("@players")
        public void all(Player sender, Player target) {
            for(ItemStack items : target.getInventory().getContents()) {
                if(items instanceof Damageable) {
                    ((Damageable) items.getItemMeta()).setDamage(0);
                }

                for(ItemStack itemss : target.getInventory().getArmorContents()) {
                    if(itemss instanceof Damageable) {
                        ((Damageable) itemss.getItemMeta()).setDamage(0);
                    }
                }
            }
            Utils.send(sender, "&aReparado el inventario de &b" + target.getName());
            Utils.send(target, "&fReparado el inventario por &b" + target.getName());
        }
    }
}
