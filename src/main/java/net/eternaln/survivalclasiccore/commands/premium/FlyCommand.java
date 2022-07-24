package net.eternaln.survivalclasiccore.commands.premium;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.objects.staff.Staff;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("fly|volar|vuelo")
@CommandPermission("survivalclasic.fly")
public class FlyCommand extends BaseCommand {

    @Default
    public void fly(Player sender) {
        Staff staff = Staff.getStaff(sender.getUniqueId());
        if (!staff.isFlying()) {
            staff.enableFly(true);
        } else {
            staff.disableFly(true);
        }
    }

    @Subcommand("otros|others|other|otro")
    @CommandPermission("survivalclasic.fly.other")
    @CommandCompletion("@players")
    public void other(Player sender, Player target) {
        Staff staff = Staff.getStaff(target.getUniqueId());
        if (!staff.isFlying()) {
            staff.enableFly(true);
            Utils.send(sender, "&aHas activado el vuelo de &b" + target.getName());
        } else {
            staff.disableFly(true);
            Utils.send(sender, "&aHas desactivado el vuelo de &b" + target.getName());
        }
    }
}
