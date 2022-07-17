package net.eternaln.survivalclasiccore.commands.admin;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.ArrayList;
import java.util.List;

@CommandAlias("vanish|v|invisible")
@CommandPermission("survivalclasiccore.vanish")
public class VanishCommand extends BaseCommand {

    @Default
    public void vanish(Player sender) {
        if (sender.hasMetadata("survivalclasiccore.vanish")) {
            sender.removeMetadata("survivalclasiccore.vanish", SurvivalClasicCore.getInstance());
            Utils.send(sender,"&cHas desactivado el modo vanish");
        } else {
            sender.setMetadata("survivalclasiccore.vanish", new FixedMetadataValue(SurvivalClasicCore.getInstance(), ""));

            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                onlinePlayer.hidePlayer(SurvivalClasicCore.getInstance(), sender);
            }

            Utils.send(sender,"&aHas activado el modo vanish");
        }
    }

    @Subcommand("list")
    public void list(Player sender) {
        List<String> players = new ArrayList<>();
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if (onlinePlayer.hasMetadata("survivalclasiccore.vanish")) {
                players.add(onlinePlayer.getName());
            }
        }
        Utils.send(sender, "&aLista de jugadores invisible\n" + players.toString());
    }

    @Subcommand("other|others|otros|otro")
    public void other(Player sender, Player target) {
        if (target.hasMetadata("survivalclasiccore.vanish")) {
            target.removeMetadata("survivalclasiccore.vanish", SurvivalClasicCore.getInstance());
            Utils.send(sender,"&aHas desactivado el modo vanish de &b" + target.getName());
        } else {
            target.setMetadata("survivalclasiccore.vanish", new FixedMetadataValue(SurvivalClasicCore.getInstance(), ""));
            Utils.send(sender,"&aHas activado el modo vanish de &b" + target.getName());
        }
    }
}
