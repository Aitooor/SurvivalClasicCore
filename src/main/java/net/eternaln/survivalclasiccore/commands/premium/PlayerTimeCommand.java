package net.eternaln.survivalclasiccore.commands.premium;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.data.configuration.MessagesFile;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.WeatherType;
import org.bukkit.World;
import org.bukkit.entity.Player;

@CommandAlias("playertime|ptime")
@CommandPermission("survivalclasic.ptime")
public class PlayerTimeCommand extends BaseCommand {

    MessagesFile messageFile = SurvivalClasicCore.getMessagesFile();

    @Subcommand("dia|day")
    public void playerTimeDay(Player sender) {
        if (sender.isPlayerTimeRelative()) {
            sender.setPlayerTime(3600, true);
            Utils.send(sender, "&aTu tiempo ha sido modificado");
        }
        if (!sender.isPlayerTimeRelative()) {
            sender.resetPlayerTime();
            Utils.send(sender, "&aTu tiempo ha sido reseteado");
        }
    }

    @Subcommand("noche|night")
    public void playerTimeNight(Player sender) {
        if (sender.isPlayerTimeRelative()) {
            sender.setPlayerTime(20000, true);
            Utils.send(sender, "&aTu tiempo ha sido modificado");
        }
        if (!sender.isPlayerTimeRelative()) {
            sender.resetPlayerTime();
            Utils.send(sender, "&aTu tiempo ha sido reseteado");
        }
    }

    @Subcommand("lluvia!rain")
    public void playerRain(Player sender) {
        if(sender.getPlayerWeather() == null) {
            sender.setPlayerWeather(WeatherType.DOWNFALL);
            Utils.send(sender, "&aTu clima ha sido modificado");
        }
        if(sender.getPlayerWeather() != null) {
            sender.setPlayerWeather(WeatherType.CLEAR);
            Utils.send(sender, "&aTu clima ha sido reseteado");
        }
    }

    @Subcommand("limpiar|clear")
    public void clear(Player sender) {
        if (sender.isPlayerTimeRelative()) {
            sender.resetPlayerTime();
            Utils.send(sender, "&aTu tiempo ha sido reseteado");
        }
        if(sender.getPlayerWeather() == null) {
            sender.setPlayerWeather(WeatherType.CLEAR);
            Utils.send(sender, "&aTu clima ha sido reseteado");
        }
    }
}
