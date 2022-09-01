package net.eternaln.survivalclasiccore.commands.admin.time;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.data.configuration.MessagesFile;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

@CommandAlias("night")
@CommandPermission("survivalclasic.time")
public class NightCommand extends BaseCommand {

    MessagesFile messageFile = SurvivalClasicCore.getMessagesFile();

    @Default
    @CommandCompletion("@worlds")
    public void nightCommand(Player sender, @Optional World world) {
        if(world != null) {
            world.setTime(24000);
        }
        if(world == null) {
            world = Bukkit.getServer().getWorld(String.valueOf(sender.getWorld()));
            world.setTime(24000);
        }
    }
}
