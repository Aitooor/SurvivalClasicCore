package net.eternaln.survivalclasiccore.commands.social;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.data.configuration.MessagesFile;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.entity.Player;

@CommandAlias("twitter")
public class TwitterCommand extends BaseCommand {

    MessagesFile messageFile = SurvivalClasicCore.getMessagesFile();

    @Default
    @CatchUnknown
    public void twitterCommand(Player player) {
        Utils.send(player, messageFile.twitterUrl);
    }

    @Subcommand("otro|other|others|otro")
    @CommandPermission("survivalclasic.menu.other")
    @CommandCompletion("@players")
    public void otherCommand(Player sender, Player target) {
        Utils.send(target, messageFile.twitterUrl);
        Utils.send(sender, "&fHas enviado el url de Twitter a &a" + target.getName());
    }

}
