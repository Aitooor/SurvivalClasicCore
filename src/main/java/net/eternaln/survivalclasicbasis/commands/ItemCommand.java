package net.eternaln.survivalclasicbasis.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasicbasis.SurvivalClasicBasis;
import net.eternaln.survivalclasicbasis.utils.Utils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("itemc|objetoc|itemclassic")
@CommandPermission("survivalclasicbasis.item.give")
public class ItemCommand extends BaseCommand {

    @HelpCommand
    @Default
    @CatchUnknown
    public void onHelp(CommandSender sender) {
        Utils.sendNoPrefix(sender, SurvivalClasicBasis.getConfiguration().getItemHelp().toArray(String[]::new));
    }

    @Subcommand("give|dar")
    public class GiveItemSubCommand extends BaseCommand {

        @Subcommand("gold|oro")
        public class GiveGoldItemSubCommand extends BaseCommand {

            @Subcommand("coin|moneda")
            public void giveGoldCoin(Player sender) {
                Utils.send(sender, SurvivalClasicBasis.getConfiguration().getItemGived());
                sender.getInventory().addItem(SurvivalClasicBasis.getConfiguration().getGoldCoin());
            }

            @Subcommand("fragment|fragmento")
            public void giveGoldFragment(Player sender) {
                Utils.send(sender, SurvivalClasicBasis.getConfiguration().getItemGived());
                sender.getInventory().addItem(SurvivalClasicBasis.getConfiguration().getGoldFragment());
            }
        }

        @Subcommand("plate|plata")
        public class GivePlateItemSubCommand extends BaseCommand {

            @Subcommand("coin|moneda")
            public void givePlateCoin(Player sender) {
                Utils.send(sender, SurvivalClasicBasis.getConfiguration().getItemGived());
                sender.getInventory().addItem(SurvivalClasicBasis.getConfiguration().getPlateCoin());
            }

            @Subcommand("fragment|fragmento")
            public void givePlateFragment(Player sender) {
                Utils.send(sender, SurvivalClasicBasis.getConfiguration().getItemGived());
                sender.getInventory().addItem(SurvivalClasicBasis.getConfiguration().getPlateFragment());
            }
        }
    }
}
