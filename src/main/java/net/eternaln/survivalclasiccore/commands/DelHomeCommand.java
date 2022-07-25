package net.eternaln.survivalclasiccore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.data.configuration.MessagesFile;
import net.eternaln.survivalclasiccore.data.mongo.PlayerData;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.entity.Player;

@CommandAlias("borrarcasa|delhome|borrarcasas|delhomes|dhome|bcasa")
public class DelHomeCommand extends BaseCommand {

    MessagesFile messagesFile = SurvivalClasicCore.getMessagesFile();

    @Default
    @CatchUnknown
    @CommandCompletion("@homes")
    public void onHome(Player sender, String name) {
        PlayerData data = SurvivalClasicCore.getDataManager().getData(sender);

        if (!data.getHomes().containsKey(name)) {
            Utils.send(sender, messagesFile.homeNotExists);
            return;
        }

        data.getHomes().remove(name);
        data.save();
        Utils.send(sender, messagesFile.homeDeleted.replace("%home%", name));
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
