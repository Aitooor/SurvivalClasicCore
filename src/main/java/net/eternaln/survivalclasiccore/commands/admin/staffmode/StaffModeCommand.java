package net.eternaln.survivalclasiccore.commands.admin.staffmode;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CatchUnknown;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import net.eternaln.survivalclasiccore.objects.staff.Staff;
import org.bukkit.entity.Player;

@CommandAlias("staffmode|staff|mod")
@CommandPermission("survivalclasic.staffmode")
public class StaffModeCommand extends BaseCommand {

    @Default
    @CatchUnknown
    public void staffMode(Player sender) {
        Staff staff = Staff.getStaff(sender.getUniqueId());

        if (!staff.isStaffMode()) {
            staff.enableStaffMode(true);
        } else {
            staff.disableStaffMode(true);
        }
    }

}
