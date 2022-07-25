package net.eternaln.survivalclasiccore.commands.admin.staffmode;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.objects.staff.Staff;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.entity.Player;

@CommandAlias("vanish|v|invisible")
@CommandPermission("survivalclasic.vanish")
public class VanishCommand extends BaseCommand {

    

    @Default
    public void vanish(Player sender) {        Staff staff = Staff.getStaff(sender.getUniqueId());
        if(!staff.isStaffMode()) {
            if (staff.isVanished()) {
                staff.disableVanish(true);
            } else {
                staff.enableVanish(true);
            }
        } else {
            Utils.send(sender, "&cNo puedes hacer eso mientras estás en StaffMode");
        }
    }

    @Subcommand("other|others|otros|otro")
    @CommandPermission("survivalclasic.vanish.other")
    @CommandCompletion("@players")
    public void other(Player sender, Player target) {
        Staff staff = Staff.getStaff(target.getUniqueId());
        if(!staff.isStaffMode()) {
            if (staff.isVanished()) {
                staff.disableVanish(true);
                Utils.send(sender, "&aHas desvinculado a &b" + target.getName() + " &ade la lista de invisibles");
            } else {
                staff.enableVanish(true);
                Utils.send(sender, "&aHas vinculado a &b" + target.getName() + " &aen la lista de invisibles");
            }
        } else {
            Utils.send(sender, "&cNo puedes hacer eso mientras " + target.getName() + " está en StaffMode");
        }
    }

}
