package net.eternaln.survivalclasiccore.commands.premium;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.data.mongo.PlayerData;
import net.eternaln.survivalclasiccore.utils.Cooldown;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

@CommandAlias("nick|apodo|nombre")
@CommandPermission("survivalclasic.nick")
public class NickCommand extends BaseCommand {

    private final Cooldown<UUID> cooldown = new Cooldown<>(SurvivalClasicCore.getConfiguration().getCmdCooldown());

    @Default
    public void nick(Player sender, String string) {
        if (!sender.hasPermission("survivalclasic.cooldown.bypass") && !cooldown.isCooledDown(sender.getUniqueId())) {
            long cooldownTime = cooldown.getSecondsRemaining(sender.getUniqueId());
            Utils.send(sender, SurvivalClasicCore.getMessagesFile().cooldown.replace("%time%", String.valueOf(cooldownTime)));
            return;
        }
        if (Utils.checkNameLength(string)) {
            PlayerData data = SurvivalClasicCore.getDataManager().getData(sender.getUniqueId());
            sender.setDisplayName(string);
            sender.setPlayerListName(Utils.ct(string));
            data.setNickName(string);
            data.save();
            Utils.send(sender, "&fTu nombre ha sido cambiado a " + string);
        } else {
            Utils.send(sender, "&cNo puedes usar un nombre con mas de 16 caracteres");
        }
    }

    @Subcommand("limpiar|clear|borrar")
    @CommandPermission("survivalclasic.nick")
    public void clear(Player sender) {
        if(!sender.hasPermission("survivalclasic.cooldown.bypass") && !cooldown.isCooledDown(sender.getUniqueId())) {
            long cooldownTime = cooldown.getSecondsRemaining(sender.getUniqueId());
            Utils.send(sender, SurvivalClasicCore.getMessagesFile().cooldown.replace("%time%", String.valueOf(cooldownTime)));
            return;
        }
        if (!sender.getDisplayName().equals(sender.getName())) {
            PlayerData data = SurvivalClasicCore.getDataManager().getData(sender.getUniqueId());
            sender.setDisplayName(sender.getName());
            sender.setPlayerListName(sender.getName());
            data.setNickName(null);
            data.save();
            Utils.send(sender, "&fTu nombre ha sido borrado");
        } else {
            Utils.send(sender, "&cTu nombre ya es tu nombre original");
        }
    }

    @Subcommand("clearother|clearo")
    @CommandPermission("survivalclasic.nick.other")
    @CommandCompletion("@players")
    public void clearOther(Player sender, OnlinePlayer target) {
        Player targetPlayer = target.getPlayer();
        if (!targetPlayer.getDisplayName().equals(targetPlayer.getName())) {
            PlayerData data = SurvivalClasicCore.getDataManager().getData(targetPlayer.getUniqueId());
            targetPlayer.setDisplayName(targetPlayer.getName());
            targetPlayer.setPlayerListName(targetPlayer.getName());
            data.setNickName(null);
            data.save();
            Utils.send(sender, "&fEl nombre de &b" + targetPlayer.getName() + " &fha sido borrado");
            Utils.send(targetPlayer, "&fTu nombre ha sido borrado por &b" + sender.getName());
        } else {
            Utils.send(sender, "&cEl jugador " + sender.getName() + " ya tiene su nombre original");
        }
    }

    @Subcommand("otros|others|other|otro")
    @CommandPermission("survivalclasic.nick.other")
    @CommandCompletion("@players")
    public void other(CommandSender sender, OnlinePlayer target, String string) {
        Player targetPlayer = target.getPlayer();

        if (Utils.checkNameLength(string)) {
            targetPlayer.setDisplayName(string);
            targetPlayer.setPlayerListName(Utils.ct(string));
            PlayerData data = SurvivalClasicCore.getDataManager().getData(targetPlayer.getUniqueId());
            data.setNickName(string);
            data.save();
            Utils.send(sender, "&fEl nombre de &b" + targetPlayer.getName() + " &fha sido cambiado a " + string);
            Utils.send(targetPlayer, "&fTu nombre ha sido cambiado a " + string + " &fpor &b" + sender.getName());
        } else {
            Utils.send(sender, "&cNo puedes usar un nombre con mas de 16 caracteres");
        }
    }


}
