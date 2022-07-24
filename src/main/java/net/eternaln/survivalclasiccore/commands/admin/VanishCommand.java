package net.eternaln.survivalclasiccore.commands.admin;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.objects.staff.Staff;
import net.eternaln.survivalclasiccore.utils.Cooldown;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@CommandAlias("vanish|v|invisible")
@CommandPermission("survivalclasic.vanish")
public class VanishCommand extends BaseCommand {

    private final Cooldown<UUID> cooldown = new Cooldown<>(SurvivalClasicCore.getConfiguration().getCmdCooldown());

    @Default
    public void vanish(Player sender) {
        if (!sender.hasPermission("survivalclasic.cooldown.bypass") && !cooldown.isCooledDown(sender.getUniqueId())) {
            long cooldownTime = cooldown.getSecondsRemaining(sender.getUniqueId());
            Utils.send(sender, SurvivalClasicCore.getMessagesFile().cooldown.replace("%time%", String.valueOf(cooldownTime)));
            return;
        }
        Staff staff = new Staff(sender.getUniqueId());
        if(staff.isVanished()) {
            staff.disableVanish(true);
        } else {
            staff.enableVanish(true);
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
        } else {
            staff.enableVanish(true);
            Utils.send(sender, "&aHas vinculado a &b" + target.getName() + " &aen la lista de invisibles");
        }
    }

}
