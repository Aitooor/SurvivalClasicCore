package net.eternaln.survivalclasiccore.commands.homes;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.data.configuration.MessagesFile;
import net.eternaln.survivalclasiccore.data.mongo.PlayerData;
import net.eternaln.survivalclasiccore.utils.Cooldown;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@CommandAlias("borrarcasa|delhome|borrarcasas|delhomes|dhome|bcasa")
public class DelHomeCommand extends BaseCommand {

    private MessagesFile messagesFile = SurvivalClasicCore.getMessagesFile();
    private Cooldown<UUID> cooldown = new Cooldown<>(SurvivalClasicCore.getConfiguration().getCmdCooldown(), TimeUnit.SECONDS);

    @Default
    @CatchUnknown
    @CommandCompletion("@homes")
    public void onHome(Player sender, String name) {
        if (!cooldown.isCooldownOver(sender.getUniqueId()) && !sender.hasPermission("survivalclasic.cooldown.bypass")) {
            String cooldownTime = cooldown.getFormattedRemainingString(sender.getUniqueId());
            Utils.send(sender, messagesFile.cooldown.replace("%time%", cooldownTime));
            return;
        }
        cooldown.addToCooldown(sender.getUniqueId());
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
