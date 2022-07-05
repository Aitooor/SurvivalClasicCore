package net.eternaln.survivalclasiccore.commands.admin;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("itemc|objetoc|itemclassic")
@CommandPermission("survivalclasiccore.item.give")
public class ItemCommand extends BaseCommand {

    @HelpCommand
    @Default
    @CatchUnknown
    public void onHelp(CommandSender sender) {
        Utils.sendNoPrefix(sender, SurvivalClasicCore.getConfiguration().getItemHelp().toArray(String[]::new));
    }

    @Subcommand("give|dar")
    public class GiveItemSubCommand extends BaseCommand {

        @Subcommand("gold|oro")
        public class GiveGoldItemSubCommand extends BaseCommand {

            @Subcommand("coin|moneda")
            public void giveGoldCoin(Player sender) {
                Utils.send(sender, SurvivalClasicCore.getConfiguration().getItemGived());
                sender.getInventory().addItem(SurvivalClasicCore.getConfiguration().getGoldCoin());
            }

            @Subcommand("fragment|fragmento")
            public void giveGoldFragment(Player sender) {
                Utils.send(sender, SurvivalClasicCore.getConfiguration().getItemGived());
                sender.getInventory().addItem(SurvivalClasicCore.getConfiguration().getGoldFragment());
            }
        }

        @Subcommand("plate|plata")
        public class GivePlateItemSubCommand extends BaseCommand {

            @Subcommand("coin|moneda")
            public void givePlateCoin(Player sender) {
                Utils.send(sender, SurvivalClasicCore.getConfiguration().getItemGived());
                sender.getInventory().addItem(SurvivalClasicCore.getConfiguration().getPlateCoin());
            }

            @Subcommand("fragment|fragmento")
            public void givePlateFragment(Player sender) {
                Utils.send(sender, SurvivalClasicCore.getConfiguration().getItemGived());
                sender.getInventory().addItem(SurvivalClasicCore.getConfiguration().getPlateFragment());
            }
        }
    }
}
