package net.eternaln.survivalclasiccore.commands.admin;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.objects.staff.Staff;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.ArrayList;
import java.util.List;

@CommandAlias("vanish|v|invisible")
@CommandPermission("survivalclasic.vanish")
public class VanishCommand extends BaseCommand {

    @Default
    public void vanish(Player sender) {
        Staff staff = new Staff(sender.getUniqueId());
        if(staff.isVanished()) {
            staff.disableVanish(true);
            Utils.send(sender, "&aHas desactivado el modo vanish");
        } else {
            staff.enableVanish(true);
            Utils.send(sender, "&aHas activado el modo vanish");
        }
    }

    @Subcommand("list")
    @CommandPermission("survivalclasic.vanish.list")
    public void list(Player sender) {
        List<String> players = new ArrayList<>();
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            Staff staff = new Staff(onlinePlayer.getUniqueId());
            if(staff.isVanished()) {
                players.add(onlinePlayer.getName());
            }
        }
        if(!players.isEmpty()) {
            Utils.sendNoPrefix(sender, "&6&lJUGADORES INVISIBLES\n&b" + players.toString().replace("[", "").replace("]", ""));
        } else {
            Utils.sendNoPrefix(sender, "&6&lJUGADORES INVISIBLES\n&cNo hay jugadores invisibles");
        }
    }

    @Subcommand("other|others|otros|otro")
    @CommandPermission("survivalclasic.vanish.other")
    @CommandCompletion("@players")
    public void other(Player sender, Player target) {
        Staff staff = new Staff(target.getUniqueId());
        if(staff.isVanished()) {
            staff.disableVanish(true);
            Utils.send(sender, "&aHas desvinculado a &b" + target.getName() + " &ade la lista de invisibles");
            Utils.send(target, "&fEl staff &c" + sender.getName() + " &fte ha vuelto visible");
        } else {
            staff.enableVanish(true);
            Utils.send(sender, "&aHas vinculado a &b" + target.getName() + " &aen la lista de invisibles");
            Utils.send(target, "&fEl staff &c" + sender.getName() + " &fte ha vuelto invisible");
        }
    }
}
