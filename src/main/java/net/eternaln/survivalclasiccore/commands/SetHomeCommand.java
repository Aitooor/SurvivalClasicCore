package net.eternaln.survivalclasiccore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.data.configuration.Configuration;
import net.eternaln.survivalclasiccore.data.configuration.MenusFile;
import net.eternaln.survivalclasiccore.data.configuration.MessagesFile;
import net.eternaln.survivalclasiccore.data.mongo.PlayerData;
import net.eternaln.survivalclasiccore.managers.CooldownManager;
import net.eternaln.survivalclasiccore.utils.Cooldown;
import net.eternaln.survivalclasiccore.utils.LocationUtil;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachmentInfo;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@CommandAlias("setcasa|sethome|setcasas|sethomes")
public class SetHomeCommand extends BaseCommand {

    private Configuration config = SurvivalClasicCore.getInstance().getConfiguration();
    private MessagesFile messagesFile = SurvivalClasicCore.getMessagesFile();
    private MenusFile menusFile = SurvivalClasicCore.getMenusFile();
    private CooldownManager cooldowns = SurvivalClasicCore.getInstance().getCooldowns();
    private int cooldownConfig = config.cmdCooldown;

    @Default
    @CatchUnknown
    @CommandCompletion("@homes")
    public void onHome(Player sender, String name) {
        if (cooldowns.getCooldown(sender.getUniqueId()) == null) {
            onSetHome(sender, name);
            cooldowns.create(sender.getUniqueId(), new Cooldown(TimeUnit.SECONDS.toMillis(cooldownConfig)));
            return;
        }
        Cooldown cooldown = cooldowns.getOrCreate(sender.getUniqueId(), TimeUnit.SECONDS.toMillis(cooldownConfig));
        if (!cooldown.hasExpired()) {
            Utils.send(sender, messagesFile.cooldown.replace("%time%", String.valueOf(TimeUnit.MILLISECONDS.toSeconds(cooldown.getRemaining()))));
            return;
        }
        cooldown.stop();
        cooldowns.create(sender.getUniqueId(), new Cooldown(TimeUnit.SECONDS.toMillis(cooldownConfig)));
    }

    public int getMaxInteger(Integer[] array) {
        int i = array[0];
        for (byte b = 1; b < array.length; b++) {
            if (array[b] > i)
                i = array[b];
        }
        return i;
    }

    public void onSetHome(Player sender, String home) {
        PlayerData data = SurvivalClasicCore.getDataManager().getData(sender);

        ArrayList<Integer> arrayList = new ArrayList<>();
        for (PermissionAttachmentInfo permissionAttachmentInfo : sender.getEffectivePermissions()) {
            if (permissionAttachmentInfo.getPermission().startsWith("survivalclasic.homes.max.")) {
                int i = Integer.parseInt(permissionAttachmentInfo.getPermission().toLowerCase().replaceAll("survivalclasic\\.homes\\.max\\.", ""));
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

        if (data.getHomes().containsKey(home)) {
            Utils.send(sender, messagesFile.homeExists);
            return;
        }

        data.getHomes().put(home, LocationUtil.parseToString(sender.getLocation()));
        Utils.send(sender, messagesFile.homeSet.replace("%home%", home));
        data.save();
    }
}
