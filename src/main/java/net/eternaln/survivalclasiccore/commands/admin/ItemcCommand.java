package net.eternaln.survivalclasiccore.commands.admin;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.data.configuration.MessagesFile;
import net.eternaln.survivalclasiccore.utils.Utils;
import net.eternaln.survivalclasiccore.utils.items.ItemBuilder;
import org.bukkit.entity.Player;

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
                    if(sender.getInventory().isEmpty()) {
                        Utils.send(sender, SurvivalClasicCore.getMessagesFile().getItemGived());
                        sender.getInventory().addItem(new ItemBuilder(SurvivalClasicCore.getConfiguration().getGoldCoin()).amount(amount).build());
                    } else {
                        if(amount <= 127) {
                            Utils.send(sender, SurvivalClasicCore.getMessagesFile().getItemGived());
                            Utils.send(sender, "&7Como no tienes espacio en tu inventario, has recibido los objetos en el suelo");
                            sender.getWorld().dropItem(sender.getLocation(), new ItemBuilder(SurvivalClasicCore.getConfiguration().getGoldCoin()).amount(amount).build());
                        } else {
                            Utils.send(sender, "&cAl no tener espacio en tu inventario, no puedes recibir más de 127 monedas de oro");
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
                    if(sender.getInventory().isEmpty()) {
                        Utils.send(sender, SurvivalClasicCore.getMessagesFile().getItemGived());
                        sender.getInventory().addItem(new ItemBuilder(SurvivalClasicCore.getConfiguration().getGoldFragment()).amount(amount).build());
                    } else {
                        if(amount <= 127) {
                            Utils.send(sender, SurvivalClasicCore.getMessagesFile().getItemGived());
                            Utils.send(sender, "&7Como no tienes espacio en tu inventario, has recibido los objetos en el suelo");
                            sender.getWorld().dropItem(sender.getLocation(), new ItemBuilder(SurvivalClasicCore.getConfiguration().getGoldFragment()).amount(amount).build());
                        } else {
                            Utils.send(sender, "&cAl no tener espacio en tu inventario, no puedes recibir más de 127 fragmentos de oro");
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
                    if(sender.getInventory().isEmpty()) {
                        Utils.send(sender, SurvivalClasicCore.getMessagesFile().getItemGived());
                        sender.getInventory().addItem(new ItemBuilder(SurvivalClasicCore.getConfiguration().getPlateCoin()).amount(amount).build());
                    } else {
                        if(amount <= 127) {
                            Utils.send(sender, SurvivalClasicCore.getMessagesFile().getItemGived());
                            Utils.send(sender, "&7Como no tienes espacio en tu inventario, has recibido los objetos en el suelo");
                            sender.getWorld().dropItem(sender.getLocation(), new ItemBuilder(SurvivalClasicCore.getConfiguration().getPlateCoin()).amount(amount).build());
                        } else {
                            Utils.send(sender, "&cAl no tener espacio en tu inventario, no puedes recibir más de 127 monedas de plata");
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
                    if(sender.getInventory().isEmpty()) {
                        Utils.send(sender, SurvivalClasicCore.getMessagesFile().getItemGived());
                        sender.getInventory().addItem(new ItemBuilder(SurvivalClasicCore.getConfiguration().getPlateFragment()).amount(amount).build());
                    } else {
                        if(amount <= 127) {
                            Utils.send(sender, SurvivalClasicCore.getMessagesFile().getItemGived());
                            Utils.send(sender, "&7Como no tienes espacio en tu inventario, has recibido los objetos en el suelo");
                            sender.getWorld().dropItem(sender.getLocation(), new ItemBuilder(SurvivalClasicCore.getConfiguration().getPlateFragment()).amount(amount).build());
                        } else {
                            Utils.send(sender, "&cAl no tener espacio en tu inventario, no puedes recibir más de 127 fragmentos de plata");
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
        public class GiveGoldItemSubCommand extends BaseCommand {

            @Subcommand("coin|moneda")
            @CommandCompletion("@players @range:1-2304")
            public void giveGoldCoin(Player sender, Player target, int amount) {
                if (amount > 0 || amount > 2304) {
                    if(target.getInventory().isEmpty()) {
                        Utils.send(sender, "&fLe has dado &a" + amount + " &fmonedas de oro a &a" + target.getName());
                        Utils.send(target, SurvivalClasicCore.getMessagesFile().getItemGived());
                        target.getInventory().addItem(new ItemBuilder(SurvivalClasicCore.getConfiguration().getGoldCoin()).amount(amount).build());
                    } else {
                        if(amount <= 127) {
                            Utils.send(sender, "&fLe has dado &a" + amount + " &fmonedas de oro a &a" + target.getName());
                            Utils.send(sender, "&7Como no tiene espacio en su inventario, se le ha dado los objetos en el suelo");
                            Utils.send(target, SurvivalClasicCore.getMessagesFile().getItemGived());
                            Utils.send(target, "&7Como no tienes espacio en tu inventario, has recibido los objetos en el suelo");
                            target.getWorld().dropItem(target.getLocation(), new ItemBuilder(SurvivalClasicCore.getConfiguration().getGoldCoin()).amount(amount).build());
                        } else {
                            Utils.send(sender, "&cAl no tener espacio en su inventario, no puedes darle más de 127 monedas de oro");
                            Utils.send(target, "&cAl no tener espacio en tu inventario, no puedes recibir más de 127 monedas de oro");
                        }
                    }
                } else {
                    Utils.send(sender, "&cIngresa un numero del 1-2304");
                }
            }

            @Subcommand("fragment|fragmento")
            @CommandCompletion("@players @range:1-2304")
            public void giveGoldFragment(Player sender, Player target, int amount) {
                if (amount > 0 || amount > 2304) {
                    if(sender.getInventory().isEmpty()) {
                        Utils.send(sender, "&fLe has dado &a" + amount + " &ffragmentos de oro a &a" + target.getName());
                        Utils.send(target, SurvivalClasicCore.getMessagesFile().getItemGived());
                        target.getInventory().addItem(new ItemBuilder(SurvivalClasicCore.getConfiguration().getGoldFragment()).amount(amount).build());
                    } else {
                        if(amount <= 127) {
                            Utils.send(sender, "&fLe has dado &a" + amount + " &ffragmentos de oro a &a" + target.getName());
                            Utils.send(sender, "&7Como no tiene espacio en su inventario, se le ha dado los objetos en el suelo");
                            Utils.send(target, SurvivalClasicCore.getMessagesFile().getItemGived());
                            Utils.send(target, "&7Como no tienes espacio en tu inventario, has recibido los objetos en el suelo");
                            target.getWorld().dropItem(target.getLocation(), new ItemBuilder(SurvivalClasicCore.getConfiguration().getGoldFragment()).amount(amount).build());
                        } else {
                            Utils.send(sender, "&cAl no tener espacio en su inventario, no puedes darle más de 127 fragmentos de oro");
                            Utils.send(target, "&cAl no tener espacio en tu inventario, no puedes recibir más de 127 fragmentos de oro");
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
            @CommandCompletion("@players @range:1-2304")
            public void givePlateCoin(Player sender, Player target, int amount) {
                if (amount > 0 || amount > 2304) {
                    if(sender.getInventory().isEmpty()) {
                        Utils.send(sender, "&fLe has dado &a" + amount + " &fmonedas de plata a &a" + target.getName());
                        Utils.send(target, SurvivalClasicCore.getMessagesFile().getItemGived());
                        target.getInventory().addItem(new ItemBuilder(SurvivalClasicCore.getConfiguration().getPlateCoin()).amount(amount).build());
                    } else {
                        if(amount <= 127) {
                            Utils.send(sender, "&fLe has dado &a" + amount + " &fmonedas de plata a &a" + target.getName());
                            Utils.send(sender, "&7Como no tiene espacio en su inventario, se le ha dado los objetos en el suelo");
                            Utils.send(target, SurvivalClasicCore.getMessagesFile().getItemGived());
                            Utils.send(target, "&7Como no tienes espacio en tu inventario, has recibido los objetos en el suelo");
                            target.getWorld().dropItem(target.getLocation(), new ItemBuilder(SurvivalClasicCore.getConfiguration().getPlateCoin()).amount(amount).build());
                        } else {
                            Utils.send(sender, "&cAl no tener espacio en su inventario, no puedes darle más de 127 monedas de plata");
                            Utils.send(target, "&cAl no tener espacio en tu inventario, no puedes recibir más de 127 monedas de plata");
                        }
                    }
                } else {
                    Utils.send(sender, "&cIngresa un numero del 1-2304");
                }
            }

            @Subcommand("fragment|fragmento")
            @CommandCompletion("@players @range:1-2304")
            public void givePlateFragment(Player sender, Player target, int amount) {
                if (amount > 0 || amount > 2304) {
                    if(sender.getInventory().isEmpty()) {
                        Utils.send(sender, "&fLe has dado &a" + amount + " &ffragmentos de plata a &a" + target.getName());
                        Utils.send(target, SurvivalClasicCore.getMessagesFile().getItemGived());
                        target.getInventory().addItem(new ItemBuilder(SurvivalClasicCore.getConfiguration().getPlateFragment()).amount(amount).build());
                    } else {
                        if(amount <= 127) {
                            Utils.send(sender, "&fLe has dado &a" + amount + " &ffragmentos de plata a &a" + target.getName());
                            Utils.send(sender, "&7Como no tiene espacio en su inventario, se le ha dado los objetos en el suelo");
                            Utils.send(target, SurvivalClasicCore.getMessagesFile().getItemGived());
                            Utils.send(target, "&7Como no tienes espacio en tu inventario, has recibido los objetos en el suelo");
                            target.getWorld().dropItem(target.getLocation(), new ItemBuilder(SurvivalClasicCore.getConfiguration().getPlateFragment()).amount(amount).build());
                        } else {
                            Utils.send(sender, "&cAl no tener espacio en su inventario, no puedes darle más de 127 fragmentos de plata");
                            Utils.send(target, "&cAl no tener espacio en tu inventario, no puedes recibir más de 127 fragmentos de plata");
                        }
                    }
                } else {
                    Utils.send(sender, "&cIngresa un numero del 1-2304");
                }
            }
        }
    }
}
