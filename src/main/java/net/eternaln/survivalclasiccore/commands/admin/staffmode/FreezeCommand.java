package net.eternaln.survivalclasiccore.commands.admin.staffmode;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CatchUnknown;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import net.eternaln.survivalclasiccore.objects.freeze.Freeze;
import net.eternaln.survivalclasiccore.objects.staff.Staff;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.entity.Player;

@CommandAlias("freeze|ss|congelar")
@CommandPermission("survivalclasic.freeze")
public class FreezeCommand extends BaseCommand {

    @Default
    @CatchUnknown
    public void freeze(Player sender, Player target) {
        if (target == null) {
            Utils.send(sender,"&cJugador " + target + " no encontrado");
            return;
        }

        Staff staff = Staff.getStaff(sender.getUniqueId());

        if (staff != null) {
            Freeze freeze;

            if (Freeze.getFreezes().get(target.getUniqueId()) == null) {
                freeze = new Freeze(target.getUniqueId());
            }
            else {
                freeze = Freeze.getFreezes().get(target.getUniqueId());
            }

            freeze.setStaff(staff);

            if (freeze.isFrozen()) {
                freeze.unFreezePlayer(true);
            }
            else {
                freeze.freezePlayer(true);
            }
        } else {
            Utils.send(sender,"&cNo eres del STAFF");
        }
    }

}
