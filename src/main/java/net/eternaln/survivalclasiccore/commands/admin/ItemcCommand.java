package net.eternaln.survivalclasiccore.commands.admin;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.data.configuration.MessagesFile;
import net.eternaln.survivalclasiccore.utils.Utils;
import net.eternaln.survivalclasiccore.utils.items.ItemBuilder;
import org.bukkit.entity.Player;

@CommandAlias("itemc|objeto|objetos|objetosc|itemclassic")
@CommandPermission("survivalclasiccore.itemc.give")
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
                    Utils.send(sender, SurvivalClasicCore.getMessagesFile().getItemGived());
                    sender.getInventory().addItem(new ItemBuilder(SurvivalClasicCore.getConfiguration().getGoldCoin()).amount(amount).build());
                } else {
                    Utils.send(sender, "&cIngresa un numero del 1-2304");
                }
            }

            @Subcommand("fragment|fragmento")
            @CommandCompletion("@range:1-2304")
            public void giveGoldFragment(Player sender, int amount) {
                if (amount > 0 || amount > 2304) {
                    Utils.send(sender, SurvivalClasicCore.getMessagesFile().getItemGived());
                    sender.getInventory().addItem(new ItemBuilder(SurvivalClasicCore.getConfiguration().getGoldFragment()).amount(amount).build());
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
                    Utils.send(sender, SurvivalClasicCore.getMessagesFile().getItemGived());
                    sender.getInventory().addItem(new ItemBuilder(SurvivalClasicCore.getConfiguration().getPlateCoin()).amount(amount).build());
                } else {
                    Utils.send(sender, "&cIngresa un numero del 1-2304");
                }
            }

            @Subcommand("fragment|fragmento")
            @CommandCompletion("@range:1-2304")
            public void givePlateFragment(Player sender, int amount) {
                if (amount > 0 || amount > 2304) {
                    Utils.send(sender, SurvivalClasicCore.getMessagesFile().getItemGived());
                    sender.getInventory().addItem(new ItemBuilder(SurvivalClasicCore.getConfiguration().getPlateFragment()).amount(amount).build());
                } else {
                    Utils.send(sender, "&cIngresa un numero del 1-2304");
                }
            }
        }
    }
}
