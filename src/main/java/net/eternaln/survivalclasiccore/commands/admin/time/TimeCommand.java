package net.eternaln.survivalclasiccore.commands.admin.time;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.data.configuration.MessagesFile;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

@CommandAlias("time")
@CommandPermission("survivalclasic.time")
public class TimeCommand extends BaseCommand {

    MessagesFile messageFile = SurvivalClasicCore.getMessagesFile();

    @Default
    @CommandCompletion("@range:0-24000 @worlds")
    public void timeCommand(Player sender, long time, @Optional World world) {
        if(world != null) {
            world.setTime(time);
        }
        if(world == null) {
         world = Bukkit.getWorld(String.valueOf(sender.getWorld()));

         world.setTime(time);
        }
    }
}
