package net.eternaln.survivalclasiccore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.data.configuration.MessagesFile;
import net.eternaln.survivalclasiccore.data.mongo.PlayerData;
import net.eternaln.survivalclasiccore.utils.Cooldown;
import net.eternaln.survivalclasiccore.utils.LocationUtil;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachmentInfo;

import java.util.ArrayList;
import java.util.UUID;

@CommandAlias("setcasa|sethome|setcasas|sethomes")
public class SetHomeCommand extends BaseCommand {

    MessagesFile messagesFile = SurvivalClasicCore.getMessagesFile();

    private final Cooldown<UUID> cooldown = new Cooldown<>(SurvivalClasicCore.getConfiguration().getCmdCooldown());

    @Default
    @CatchUnknown
    @CommandCompletion("@homes")
    public void onHome(Player sender, String name) {
        if (!sender.hasPermission("survivalclasiccore.cooldown.bypass") && !cooldown.isCooledDown(sender.getUniqueId())) {
            long cooldownTime = cooldown.getSecondsRemaining(sender.getUniqueId());
            Utils.send(sender, SurvivalClasicCore.getMessagesFile().cooldown.replace("%time%", String.valueOf(cooldownTime)));
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
            Utils.send(sender, messagesFile.maxHomes);
            return;
        }

        Integer[] arrayOfInteger = arrayList.toArray(new Integer[0]);
        int i = getMaxInteger(arrayOfInteger);
        if (data.getHomes().size() >= i) {
            Utils.send(sender, messagesFile.maxHomes);
            return;
        }

        if (data.getHomes().containsKey(name)) {
            Utils.send(sender, messagesFile.homeExists);
            return;
        }

        data.getHomes().put(name, LocationUtil.parseToString(sender.getLocation()));
        Utils.send(sender, messagesFile.homeSet).replace("%home%", name);
        data.save();
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
