package net.eternaln.survivalclasiccore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.data.configuration.Configuration;
import net.eternaln.survivalclasiccore.data.configuration.MessagesFile;
import net.eternaln.survivalclasiccore.data.mongo.PlayerData;
import net.eternaln.survivalclasiccore.managers.CooldownManager;
import net.eternaln.survivalclasiccore.utils.CooldownOld;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.entity.Player;

import java.util.concurrent.TimeUnit;

@CommandAlias("borrarcasa|delhome|borrarcasas|delhomes|dhome|bcasa")
public class DelHomeCommand extends BaseCommand {

    private Configuration config = SurvivalClasicCore.getInstance().getConfiguration();
    private MessagesFile messagesFile = SurvivalClasicCore.getMessagesFile();
    private CooldownManager cooldowns = SurvivalClasicCore.getInstance().getCooldowns();
    private int cooldownConfig = config.cmdCooldown;

    @Default
    @CatchUnknown
    @CommandCompletion("@homes")
    public void onHome(Player sender, String name) {
        if (cooldowns.getCooldown(sender.getUniqueId()) == null) {
            onDelHomeCommand(sender, name);
            cooldowns.create(sender.getUniqueId(), new CooldownOld(TimeUnit.SECONDS.toMillis(cooldownConfig)));
            return;
        }
        CooldownOld cooldownOld = cooldowns.getOrCreate(sender.getUniqueId(), TimeUnit.SECONDS.toMillis(cooldownConfig));
        if (!cooldownOld.hasExpired()) {
            Utils.send(sender, messagesFile.cooldown.replace("%time%", String.valueOf(TimeUnit.MILLISECONDS.toSeconds(cooldownOld.getRemaining()))));
            return;
        }
        cooldownOld.stop();
        cooldowns.create(sender.getUniqueId(), new CooldownOld(TimeUnit.SECONDS.toMillis(cooldownConfig)));
        onDelHomeCommand(sender, name);
    }

    public int getMaxInteger(Integer[] array) {
        int i = array[0];
        for (byte b = 1; b < array.length; b++) {
            if (array[b] > i)
                i = array[b];
        }
        return i;
    }
    
    public void onDelHomeCommand(Player sender, String home) {
        PlayerData data = SurvivalClasicCore.getDataManager().getData(sender);

        if (!data.getHomes().containsKey(home)) {
            Utils.send(sender, messagesFile.homeNotExists);
            return;
        }

        data.getHomes().remove(home);
        data.save();
        Utils.send(sender, messagesFile.homeDeleted.replace("%home%", home));
    }
}
