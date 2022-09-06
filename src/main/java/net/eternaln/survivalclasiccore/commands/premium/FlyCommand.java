package net.eternaln.survivalclasiccore.commands.premium;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.data.configuration.Configuration;
import net.eternaln.survivalclasiccore.data.configuration.MessagesFile;
import net.eternaln.survivalclasiccore.objects.staff.Staff;
import net.eternaln.survivalclasiccore.utils.Cooldown;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@CommandAlias("fly|volar|vuelo")
@CommandPermission("survivalclasic.fly")
public class FlyCommand extends BaseCommand {

    private MessagesFile messageFile = SurvivalClasicCore.getMessagesFile();
    private Cooldown<UUID> cooldown = new Cooldown<>(SurvivalClasicCore.getConfiguration().getCmdCooldown(), TimeUnit.SECONDS);

    @Default
    public void fly(Player sender) {
        Staff staff = Staff.getStaff(sender.getUniqueId());
        if (!cooldown.isCooldownOver(sender.getUniqueId()) && !sender.hasPermission("survivalclasic.cooldown.bypass")) {
            String cooldownTime = cooldown.getFormattedRemainingString(sender.getUniqueId());
            Utils.send(sender, messageFile.cooldown.replace("%time%", cooldownTime));
            return;
        }
        cooldown.addToCooldown(sender.getUniqueId());

        if (!staff.isFlying()) {
            staff.enableFly(true);
            Utils.send(sender, "&aFly Activado");
        } else {
            staff.disableFly(true);
        }
    }

    @Subcommand("otros|others|other|otro")
    @CommandPermission("survivalclasic.fly.other")
    @CommandCompletion("@players")
    public void other(Player sender, Player target) {
        if (!cooldown.isCooldownOver(sender.getUniqueId()) && !sender.hasPermission("survivalclasic.cooldown.bypass")) {
            String cooldownTime = cooldown.getFormattedRemainingString(sender.getUniqueId());
            Utils.send(sender, messageFile.cooldown.replace("%time%", cooldownTime));
            return;
        }
        cooldown.addToCooldown(sender.getUniqueId());

        Staff staff = Staff.getStaff(target.getUniqueId());
        if (!staff.isFlying()) {
            staff.enableFly(true);
            Utils.send(sender, "&aFly Activado");
            Utils.send(sender, "&fHas activado el vuelo de &a" + target.getName());
        } else {
            staff.disableFly(true);
            Utils.send(sender, "&fHas desactivado el vuelo de &c" + target.getName());
        }
    }
}
