package net.eternaln.survivalclasiccore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.data.mongo.PlayerData;
import net.eternaln.survivalclasiccore.utils.Cooldown;
import net.eternaln.survivalclasiccore.utils.LocationUtil;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachmentInfo;

import java.util.ArrayList;
import java.util.UUID;

@CommandAlias("casa|home|casas|homes")
public class HomesCommand extends BaseCommand {

    private final Cooldown<UUID> cooldown = new Cooldown<>(SurvivalClasicCore.getConfiguration().getCmdCooldown());

    @Default
    @CatchUnknown
    @CommandCompletion("@homes")
    public void onHome(Player sender, String name) {
        if (!sender.hasPermission("survivalclasiccore.cooldown.bypass") && !cooldown.isCooledDown(sender.getUniqueId())) {
            sender.sendMessage(Utils.ct("&cDebes esperar &b" + cooldown.getSecondsRemaining(sender.getUniqueId()) + " &csegundos"));
            return;
        }

        PlayerData data = SurvivalClasicCore.getDataManager().getData(sender);

        if (!data.getHomes().containsKey(name)) {
            sender.sendMessage(Utils.ct("&cLa casa no existe"));
            return;
        }

        sender.teleport(LocationUtil.parseToLocation(data.getHomes().get(name)));
        Utils.send(sender, "&fHas sido teletransportado a &a" + name);
    }

    @Subcommand("establece|set|add|agregar")
    public void setHome(Player sender, String name) {
        if (!sender.hasPermission("survivalclasiccore.cooldown.bypass") && !cooldown.isCooledDown(sender.getUniqueId())) {
            sender.sendMessage(Utils.ct("&cDebes esperar &b" + cooldown.getSecondsRemaining(sender.getUniqueId()) + " &csegundos"));
            return;
        }

        PlayerData data = SurvivalClasicCore.getDataManager().getData(sender);

        ArrayList<Integer> arrayList = new ArrayList<>();
        for (PermissionAttachmentInfo permissionAttachmentInfo : sender.getEffectivePermissions()) {
            if (permissionAttachmentInfo.getPermission().startsWith("survivalclasiccore.homes.max.")) {
                int i = Integer.parseInt(permissionAttachmentInfo.getPermission().toLowerCase().replaceAll("survivalclasiccore\\.homes\\.max\\.", ""));
                arrayList.add(i);
            }
        }

        if (arrayList.size() == 0) {
            sender.sendMessage(Utils.ct("&cYa tienes el máximo de casas"));
            return;
        }

        Integer[] arrayOfInteger = arrayList.toArray(new Integer[0]);
        int i = getMaxInteger(arrayOfInteger);
        if (data.getHomes().size() >= i) {
            sender.sendMessage(Utils.ct("&cYa tienes el máximo de casas"));
            return;
        }

        if (data.getHomes().containsKey(name)) {
            sender.sendMessage(Utils.ct("&cLa casa ya existe"));
            return;
        }

        data.getHomes().put(name, LocationUtil.parseToString(sender.getLocation()));
        sender.sendMessage(Utils.ct("&fHas establecido la casa &a" + name));
        data.save();
    }

    @Subcommand("eliminar|remove|delete|borrar")
    public void removeHome(Player sender, String name) {
        if (!sender.hasPermission("survivalclasiccore.cooldown.bypass") && !cooldown.isCooledDown(sender.getUniqueId())) {
            sender.sendMessage(Utils.ct("&cDebes esperar &b" + cooldown.getSecondsRemaining(sender.getUniqueId()) + " &csegundos"));
            return;
        }

        PlayerData data = SurvivalClasicCore.getDataManager().getData(sender);

        if (!data.getHomes().containsKey(name)) {
            sender.sendMessage(Utils.ct("&cLa casa no existe"));
            return;
        }

        data.getHomes().remove(name);
        data.save();
        sender.sendMessage(Utils.ct("&fHas eliminado la casa &a" + name));
    }

    public int getMaxInteger(Integer[] array) {
        int i = array[0];
        for (byte b = 1; b < array.length; b++) {
            if (array[b] > i)
                i = array[b];
        }
        return i;
    }
}
