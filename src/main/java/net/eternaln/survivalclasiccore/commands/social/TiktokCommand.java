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
    public void tiktokCommand(Player player) { Utils.send(player, messageFile.tiktokUrl); }

    @Subcommand("otro|other|others|otro")
    @CommandPermission("survivalclasiccore.menu.other")
    @CommandCompletion("@players")
    public void otherCommand(Player target) { Utils.send(target, messageFile.tiktokUrl); }

}
