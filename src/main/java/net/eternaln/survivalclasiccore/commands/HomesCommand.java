package net.eternaln.survivalclasiccore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.data.configuration.MessagesFile;
import net.eternaln.survivalclasiccore.data.mongo.PlayerData;
import net.eternaln.survivalclasiccore.utils.LocationUtil;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.entity.Player;

@CommandAlias("casa|home|casas|homes")
public class HomesCommand extends BaseCommand {

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

        sender.teleport(LocationUtil.parseToLocation(data.getHomes().get(name)));
        Utils.send(sender, "&fHas sido teletransportado a &a" + name);
        Utils.send(sender, messagesFile.homeTeleported.replace("%home%", name));
    }
}
