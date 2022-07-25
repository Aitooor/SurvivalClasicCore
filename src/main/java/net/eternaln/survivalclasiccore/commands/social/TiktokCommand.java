package net.eternaln.survivalclasiccore.commands.social;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.data.configuration.MessagesFile;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.entity.Player;

@CommandAlias("tiktok")
public class TiktokCommand extends BaseCommand {

    MessagesFile messageFile = SurvivalClasicCore.getMessagesFile();

    @Default
    @CatchUnknown
    public void tiktokCommand(Player player) {
        Utils.send(player, messageFile.tiktokUrl);
    }

    @Subcommand("otro|other|others|otro")
    @CommandPermission("survivalclasic.menu.other")
    @CommandCompletion("@players")
    public void otherCommand(Player sender, Player target) {
        Utils.send(target, messageFile.tiktokUrl);
        Utils.send(sender, "&fHas enviado el url de Tiktok a &a" + target.getName());
    }

}
