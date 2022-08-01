package net.eternaln.survivalclasiccore.commands.admin.staffmode;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CatchUnknown;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import net.eternaln.survivalclasiccore.objects.freeze.Freeze;
import net.eternaln.survivalclasiccore.objects.staff.Staff;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@CommandAlias("freeze|ss|congelar")
@CommandPermission("survivalclasic.freeze")
public class FreezeCommand extends BaseCommand {

    @Default
    public void freeze(Player sender, String target) {
        Player targetPlayer = Bukkit.getPlayer(target);
        if (targetPlayer == null) {
            Utils.send(sender,"&cJugador " + target + " no encontrado");
            return;
        }

        if (targetPlayer == sender) {
            Utils.send(sender,"&cNo puedes congelarte a ti mismo");
            return;
        }

        Staff staff = Staff.getStaff(sender.getUniqueId());

        Freeze freeze;

        if (Freeze.getFreezes().get(targetPlayer.getUniqueId()) == null) {
            freeze = new Freeze(targetPlayer.getUniqueId());
        }
        else {
            freeze = Freeze.getFreezes().get(targetPlayer.getUniqueId());
        }

        freeze.setStaff(staff);

        if (freeze.isFrozen()) {
            freeze.unFreezePlayer(true);
        }
        else {
            freeze.freezePlayer(true);
        }
    }

}
