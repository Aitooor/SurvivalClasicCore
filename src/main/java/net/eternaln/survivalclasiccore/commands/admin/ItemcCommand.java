package net.eternaln.survivalclasiccore.commands.admin;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.data.configuration.MessagesFile;
import net.eternaln.survivalclasiccore.utils.PlayerUtil;
import net.eternaln.survivalclasiccore.utils.Utils;
import net.eternaln.survivalclasiccore.utils.items.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@CommandAlias("itemc|objeto|objetos|objetosc|itemclassic")
@CommandPermission("survivalclasic.itemc.give")
public class ItemcCommand extends BaseCommand {
    MessagesFile messagesFile = SurvivalClasicCore.getMessagesFile();
    @Default
    @HelpCommand("ayuda|help")
    @CatchUnknown
    public void onHelp(Player sender) {
        Utils.sendNoPrefix(sender, String.valueOf(messagesFile.itemcHelp));
    }

    @Subcommand("give|dar")
    public class GiveItemSubCommand extends BaseCommand {

        @Subcommand("gold|oro")
        public class GiveGoldItemSubCommand extends BaseCommand {

            @Subcommand("coin|moneda")
            @CommandCompletion("@range:1-2304")
            public void giveGoldCoin(Player sender, int amount) {
                if (amount > 0 || amount > 2304) {
                    if (sender.getInventory().addItem(new ItemBuilder(SurvivalClasicCore.getConfiguration().getGoldCoin()).amount(amount).build()).isEmpty()) {
                        sender.getInventory().addItem(new ItemBuilder(SurvivalClasicCore.getConfiguration().getGoldCoin()).amount(amount).build());
                        Utils.send(sender, "&fHas recibido &b" + amount + " &fde &a" + SurvivalClasicCore.getConfiguration().getGoldCoin().getItemMeta().getDisplayName());
                    } else {
                        if (sender.getInventory().firstEmpty() == -1) {
                            sender.getWorld().dropItem(sender.getLocation(), new ItemBuilder(SurvivalClasicCore.getConfiguration().getGoldCoin()).amount(amount).build());
                            Utils.send(sender, "&fHas recibido &b" + amount + " &fde &a" + SurvivalClasicCore.getConfiguration().getGoldCoin().getItemMeta().getDisplayName());
                            Utils.send(sender, "&7Como tu inventario esta lleno, los items se han soltado en el suelo");
                        }
                    }
                } else {
                    Utils.send(sender, "&cIngresa un numero del 1-2304");
                }
            }

            @Subcommand("fragment|fragmento")
            @CommandCompletion("@range:1-2304")
            public void giveGoldFragment(Player sender, int amount) {
                if (amount > 0 || amount > 2304) {
                    if (sender.getInventory().addItem(new ItemBuilder(SurvivalClasicCore.getConfiguration().getGoldFragment()).amount(amount).build()).isEmpty()) {
                        sender.getInventory().addItem(new ItemBuilder(SurvivalClasicCore.getConfiguration().getGoldFragment()).amount(amount).build());
                        Utils.send(sender, "&fHas recibido &b" + amount + " &fde &a" + SurvivalClasicCore.getConfiguration().getGoldFragment().getItemMeta().getDisplayName());
                    } else {
                        if (sender.getInventory().firstEmpty() == -1) {
                            sender.getWorld().dropItem(sender.getLocation(), new ItemBuilder(SurvivalClasicCore.getConfiguration().getGoldFragment()).amount(amount).build());
                            Utils.send(sender, "&fHas recibido &b" + amount + " &fde &a" + SurvivalClasicCore.getConfiguration().getGoldFragment().getItemMeta().getDisplayName());
                            Utils.send(sender, "&7Como tu inventario esta lleno, los items se han soltado en el suelo");
                        }
                    }
                } else {
                    Utils.send(sender, "&cIngresa un numero del 1-2304");
                }
            }
        }

        @Subcommand("plate|plata")
        public class GivePlateItemSubCommand extends BaseCommand {

            @Subcommand("coin|moneda")
            @CommandCompletion("@range:1-2304")
            public void givePlateCoin(Player sender, int amount) {
                if (amount > 0 || amount > 2304) {
                    if (sender.getInventory().addItem(new ItemBuilder(SurvivalClasicCore.getConfiguration().getPlateCoin()).amount(amount).build()).isEmpty()) {
                        sender.getInventory().addItem(new ItemBuilder(SurvivalClasicCore.getConfiguration().getPlateCoin()).amount(amount).build());
                        Utils.send(sender, "&fHas recibido &b" + amount + " &fde &a" + SurvivalClasicCore.getConfiguration().getPlateCoin().getItemMeta().getDisplayName());
                    } else {
                        if (sender.getInventory().firstEmpty() == -1) {
                            sender.getWorld().dropItem(sender.getLocation(), new ItemBuilder(SurvivalClasicCore.getConfiguration().getPlateCoin()).amount(amount).build());
                            Utils.send(sender, "&fHas recibido &b" + amount + " &fde &a" + SurvivalClasicCore.getConfiguration().getPlateCoin().getItemMeta().getDisplayName());
                            Utils.send(sender, "&7Como tu inventario esta lleno, los items se han soltado en el suelo");
                        }
                    }
                } else {
                    Utils.send(sender, "&cIngresa un numero del 1-2304");
                }
            }

            @Subcommand("fragment|fragmento")
            @CommandCompletion("@range:1-2304")
            public void givePlateFragment(Player sender, int amount) {
                if (amount > 0 || amount > 2304) {
                    if (sender.getInventory().addItem(new ItemBuilder(SurvivalClasicCore.getConfiguration().getPlateFragment()).amount(amount).build()).isEmpty()) {
                        sender.getInventory().addItem(new ItemBuilder(SurvivalClasicCore.getConfiguration().getPlateFragment()).amount(amount).build());
                        Utils.send(sender, "&fHas recibido &b" + amount + " &fde &a" + SurvivalClasicCore.getConfiguration().getPlateFragment().getItemMeta().getDisplayName());
                    } else {
                        if (sender.getInventory().firstEmpty() == -1) {
                            sender.getWorld().dropItem(sender.getLocation(), new ItemBuilder(SurvivalClasicCore.getConfiguration().getPlateFragment()).amount(amount).build());
                            Utils.send(sender, "&fHas recibido &b" + amount + " &fde &a" + SurvivalClasicCore.getConfiguration().getPlateFragment().getItemMeta().getDisplayName());
                            Utils.send(sender, "&7Como tu inventario esta lleno, los items se han soltado en el suelo");
                        }
                    }
                } else {
                    Utils.send(sender, "&cIngresa un numero del 1-2304");
                }
            }
        }
    }

    @Subcommand("giveother|darotro")
    public class GiveOtherItemSubCommand extends BaseCommand {

        @Subcommand("gold|oro")
        public class GiveGoldItemSubCommandOther extends BaseCommand {

            @Subcommand("coin|moneda")
            @CommandCompletion("@players @range:1-2304")
            public void giveGoldCoinOther(Player sender, String target, int amount) {
                Player targetPlayer = Bukkit.getPlayer(target);

                if (sender == targetPlayer) return;
                if (targetPlayer == null) {
                    Utils.send(sender, "&cEl jugador no esta online");
                    return;
                }

                if (amount > 0 || amount > 2304) {
                    if (targetPlayer.getInventory().addItem(new ItemBuilder(SurvivalClasicCore.getConfiguration().getGoldCoin()).amount(amount).build()).isEmpty()) {
                        targetPlayer.getInventory().addItem(new ItemBuilder(SurvivalClasicCore.getConfiguration().getGoldCoin()).amount(amount).build());
                        Utils.send(sender, "&fLe has dado &b" + amount + " &fde &a" + SurvivalClasicCore.getConfiguration().getGoldCoin().getItemMeta().getDisplayName() + " &fa &b" + targetPlayer.getName());
                        Utils.send(targetPlayer, "&fHas recibido &b" + amount + " &fde &a" + SurvivalClasicCore.getConfiguration().getGoldCoin().getItemMeta().getDisplayName() + " &fde &b" + sender.getName());
                    } else {
                        if (targetPlayer.getInventory().firstEmpty() == -1) {
                            targetPlayer.getWorld().dropItem(targetPlayer.getLocation(), new ItemBuilder(SurvivalClasicCore.getConfiguration().getGoldCoin()).amount(amount).build());
                            Utils.send(sender, "&fLe has dado &b" + amount + " &fde &a" + SurvivalClasicCore.getConfiguration().getGoldCoin().getItemMeta().getDisplayName() + " &fa &b" + targetPlayer.getName());
                            Utils.send(targetPlayer, "&fHas recibido &b" + amount + " &fde &a" + SurvivalClasicCore.getConfiguration().getGoldCoin().getItemMeta().getDisplayName() + " &fde &b" + sender.getName());
                            Utils.send(targetPlayer, "&7Como tu inventario esta lleno, los items se han soltado en el suelo");
                        }
                    }
                } else {
                    Utils.send(sender, "&cIngresa un numero del 1-2304");
                }
            }

            @Subcommand("fragment|fragmento")
            @CommandCompletion("@players @range:1-2304")
            public void giveGoldFragmentOther(Player sender, String target, int amount) {
                Player targetPlayer = Bukkit.getPlayer(target);

                if (sender == targetPlayer) return;
                if (targetPlayer == null) {
                    Utils.send(sender, "&cEl jugador no esta online");
                    return;
                }

                if (amount > 0 || amount > 2304) {
                    if (targetPlayer.getInventory().addItem(new ItemBuilder(SurvivalClasicCore.getConfiguration().getGoldFragment()).amount(amount).build()).isEmpty()) {
                        targetPlayer.getInventory().addItem(new ItemBuilder(SurvivalClasicCore.getConfiguration().getGoldFragment()).amount(amount).build());
                        Utils.send(sender, "&fLe has dado &b" + amount + " &fde &a" + SurvivalClasicCore.getConfiguration().getGoldFragment().getItemMeta().getDisplayName() + " &fa &b" + targetPlayer.getName());
                        Utils.send(targetPlayer, "&fHas recibido &b" + amount + " &fde &a" + SurvivalClasicCore.getConfiguration().getGoldFragment().getItemMeta().getDisplayName() + " &fde &b" + sender.getName());
                    } else {
                        if (targetPlayer.getInventory().firstEmpty() == -1) {
                            targetPlayer.getWorld().dropItem(targetPlayer.getLocation(), new ItemBuilder(SurvivalClasicCore.getConfiguration().getGoldFragment()).amount(amount).build());
                            Utils.send(sender, "&fLe has dado &b" + amount + " &fde &a" + SurvivalClasicCore.getConfiguration().getGoldFragment().getItemMeta().getDisplayName() + " &fa &b" + targetPlayer.getName());
                            Utils.send(targetPlayer, "&fHas recibido &b" + amount + " &fde &a" + SurvivalClasicCore.getConfiguration().getGoldFragment().getItemMeta().getDisplayName() + " &fde &b" + sender.getName());
                            Utils.send(targetPlayer, "&7Como tu inventario esta lleno, los items se han soltado en el suelo");
                        }
                    }
                } else {
                    Utils.send(sender, "&cIngresa un numero del 1-2304");
                }
            }
        }

        @Subcommand("plate|plata")
        public class GivePlateItemSubCommandOther extends BaseCommand {

            @Subcommand("coin|moneda")
            @CommandCompletion("@players @range:1-2304")
            public void givePlateCoinOther(Player sender, String target, int amount) {
                Player targetPlayer = Bukkit.getPlayer(target);

                if (sender == targetPlayer) return;
                if (targetPlayer == null) {
                    Utils.send(sender, "&cEl jugador no esta online");
                    return;
                }

                if (amount > 0 || amount > 2304) {
                    if (targetPlayer.getInventory().addItem(new ItemBuilder(SurvivalClasicCore.getConfiguration().getPlateCoin()).amount(amount).build()).isEmpty()) {
                        targetPlayer.getInventory().addItem(new ItemBuilder(SurvivalClasicCore.getConfiguration().getPlateCoin()).amount(amount).build());
                        Utils.send(sender, "&fLe has dado &b" + amount + " &fde &a" + SurvivalClasicCore.getConfiguration().getPlateCoin().getItemMeta().getDisplayName() + " &fa &b" + targetPlayer.getName());
                        Utils.send(targetPlayer, "&fHas recibido &b" + amount + " &fde &a" + SurvivalClasicCore.getConfiguration().getPlateCoin().getItemMeta().getDisplayName() + " &fde &b" + sender.getName());
                    } else {
                        if (targetPlayer.getInventory().firstEmpty() == -1) {
                            targetPlayer.getWorld().dropItem(targetPlayer.getLocation(), new ItemBuilder(SurvivalClasicCore.getConfiguration().getPlateCoin()).amount(amount).build());
                            Utils.send(sender, "&fLe has dado &b" + amount + " &fde &a" + SurvivalClasicCore.getConfiguration().getPlateCoin().getItemMeta().getDisplayName() + " &fa &b" + targetPlayer.getName());
                            Utils.send(targetPlayer, "&fHas recibido &b" + amount + " &fde &a" + SurvivalClasicCore.getConfiguration().getPlateCoin().getItemMeta().getDisplayName() + " &fde &b" + sender.getName());
                            Utils.send(targetPlayer, "&7Como tu inventario esta lleno, los items se han soltado en el suelo");
                        }
                    }
                } else {
                    Utils.send(sender, "&cIngresa un numero del 1-2304");
                }
            }

            @Subcommand("fragment|fragmento")
            @CommandCompletion("@players @range:1-2304")
            public void givePlateFragmentOther(Player sender, String target, int amount) {
                Player targetPlayer = Bukkit.getPlayer(target);

                if (sender == targetPlayer) return;
                if (targetPlayer == null) {
                    Utils.send(sender, "&cEl jugador no esta online");
                    return;
                }

                if (amount > 0 || amount > 2304) {
                    if (targetPlayer.getInventory().addItem(new ItemBuilder(SurvivalClasicCore.getConfiguration().getPlateFragment()).amount(amount).build()).isEmpty()) {
                        targetPlayer.getInventory().addItem(new ItemBuilder(SurvivalClasicCore.getConfiguration().getPlateFragment()).amount(amount).build());
                        Utils.send(sender, "&fLe has dado &b" + amount + " &fde &a" + SurvivalClasicCore.getConfiguration().getPlateFragment().getItemMeta().getDisplayName() + " &fa &b" + targetPlayer.getName());
                        Utils.send(targetPlayer, "&fHas recibido &b" + amount + " &fde &a" + SurvivalClasicCore.getConfiguration().getPlateFragment().getItemMeta().getDisplayName() + " &fde &b" + sender.getName());
                    } else {
                        if (targetPlayer.getInventory().firstEmpty() == -1) {
                            targetPlayer.getWorld().dropItem(targetPlayer.getLocation(), new ItemBuilder(SurvivalClasicCore.getConfiguration().getPlateFragment()).amount(amount).build());
                            Utils.send(sender, "&fLe has dado &b" + amount + " &fde &a" + SurvivalClasicCore.getConfiguration().getPlateFragment().getItemMeta().getDisplayName() + " &fa &b" + targetPlayer.getName());
                            Utils.send(targetPlayer, "&fHas recibido &b" + amount + " &fde &a" + SurvivalClasicCore.getConfiguration().getPlateFragment().getItemMeta().getDisplayName() + " &fde &b" + sender.getName());
                            Utils.send(targetPlayer, "&7Como tu inventario esta lleno, los items se han soltado en el suelo");
                        }
                    }
                } else {
                    Utils.send(sender, "&cIngresa un numero del 1-2304");
                }
            }
        }
    }

}
