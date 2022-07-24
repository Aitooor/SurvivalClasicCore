package net.eternaln.survivalclasiccore.commands.admin.gamemode;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.data.configuration.MessagesFile;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

@CommandAlias("gamemodeto|gmto|modoa|gmother|gamemodeother")
@CommandPermission("survivalclasic.gm.other")
public class GamemodeOtherCommand extends BaseCommand {

    MessagesFile messageFile = SurvivalClasicCore.getMessagesFile();

    @Default
    @CatchUnknown
    @HelpCommand("ayuda|help")
    public void help(CommandHelp help) {
        help.showHelp();
    }

    @Subcommand("survival|s|supervivencia|0")
    @CommandCompletion("@players")
    public void setSurvival(Player sender, String target) {
        Player targetPlayer = Bukkit.getPlayer(target);
        if (!(targetPlayer == null || !targetPlayer.isOnline())) {
            if (targetPlayer.getGameMode() != GameMode.SURVIVAL) {
                if (targetPlayer != sender) {
                    targetPlayer.setGameMode(GameMode.SURVIVAL);
                    Utils.send(sender, messageFile.setSurvivalSender).replace("%player%", targetPlayer.getName());
                    Utils.send(targetPlayer, messageFile.setSurvivalTarget).replace("%player%", sender.getName());
                } else {
                    Utils.send(sender, messageFile.cannotChangeGamemode);
                }
            } else {
                Utils.send(sender, messageFile.alreadySurvivalTarget).replace("%player%", targetPlayer.getName());
            }
        } else {
            Utils.send(sender, messageFile.noOnlinePlayer);
        }
    }

    @Subcommand("creative|c|creacion|1")
    @CommandCompletion("@players")
    public void setCreative(Player sender, String target) {
        Player targetPlayer = Bukkit.getPlayer(target);
        if (!(targetPlayer == null || !targetPlayer.isOnline())) {
            if (targetPlayer.getGameMode() != GameMode.CREATIVE) {
                if (targetPlayer != sender) {
                    targetPlayer.setGameMode(GameMode.CREATIVE);
                    Utils.send(sender, messageFile.setCreativeSender).replace("%player%", targetPlayer.getName());
                    Utils.send(targetPlayer, messageFile.setCreativeTarget).replace("%player%", sender.getName());
                } else {
                    Utils.send(sender, messageFile.cannotChangeGamemode);
                }
            } else {
                Utils.send(sender, messageFile.alreadyCreativeTarget).replace("%player%", targetPlayer.getName());
            }
        } else {
            Utils.send(sender, messageFile.noOnlinePlayer);
        }
    }

    @Subcommand("adventure|a|aventura|2")
    @CommandCompletion("@players")
    public void setAdventure(Player sender, String target) {
        Player targetPlayer = Bukkit.getPlayer(target);
        if (!(targetPlayer == null || !targetPlayer.isOnline())) {
            if (targetPlayer.getGameMode() != GameMode.ADVENTURE) {
                if (targetPlayer != sender) {
                    targetPlayer.setGameMode(GameMode.ADVENTURE);
                    Utils.send(sender, messageFile.setAdventureSender).replace("%player%", targetPlayer.getName());
                    Utils.send(targetPlayer, messageFile.setAdventureTarget).replace("%player%", sender.getName());
                } else {
                    Utils.send(sender, messageFile.cannotChangeGamemode);
                }
            } else {
                Utils.send(sender, messageFile.alreadyAdventureTarget).replace("%player%", targetPlayer.getName());
            }
        } else {
            Utils.send(sender, messageFile.noOnlinePlayer);
        }
    }

    @Subcommand("spectator|spec|spectator|3")
    @CommandCompletion("@players")
    public void setSpectator(Player sender, String target) {
        Player targetPlayer = Bukkit.getPlayer(target);
        if (!(targetPlayer == null || !targetPlayer.isOnline())) {
            if (targetPlayer.getGameMode() != GameMode.SPECTATOR) {
                if (targetPlayer != sender) {
                    targetPlayer.setGameMode(GameMode.SPECTATOR);
                    Utils.send(sender, messageFile.setSpectatorSender).replace("%player%", targetPlayer.getName());
                    Utils.send(targetPlayer, messageFile.setSpectatorTarget).replace("%player%", sender.getName());
                } else {
                    Utils.send(sender, messageFile.cannotChangeGamemode);
                }
            } else {
                Utils.send(sender, messageFile.alreadySpectatorTarget).replace("%player%", targetPlayer.getName());
            }
        } else {
            Utils.send(sender, messageFile.noOnlinePlayer);
        }
    }

}
