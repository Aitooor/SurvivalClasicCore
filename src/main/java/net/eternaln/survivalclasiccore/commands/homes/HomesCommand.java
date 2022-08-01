package net.eternaln.survivalclasiccore.commands.homes;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.data.configuration.MessagesFile;
import net.eternaln.survivalclasiccore.data.mongo.PlayerData;
import net.eternaln.survivalclasiccore.utils.Cooldown;
import net.eternaln.survivalclasiccore.utils.LocationUtil;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@CommandAlias("casa|home|casas|homes")
public class HomesCommand extends BaseCommand {

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
        onHomeCommand(sender, name);
    }
    
    public void onHomeCommand(Player sender, String home) {
        PlayerData data = SurvivalClasicCore.getDataManager().getData(sender);

        if (!data.getHomes().containsKey(home)) {
            Utils.send(sender, messagesFile.homeNotExists);
            return;
        }

        sender.teleport(LocationUtil.parseToLocation(data.getHomes().get(home)));
        Utils.send(sender, "&fHas sido teletransportado a &a" + home);
        Utils.send(sender, messagesFile.homeTeleported.replace("%home%", home));
    }
}
