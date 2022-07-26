package net.eternaln.survivalclasiccore.commands.teleport;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CatchUnknown;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.HelpCommand;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.data.configuration.Configuration;
import net.eternaln.survivalclasiccore.data.configuration.MenusFile;
import net.eternaln.survivalclasiccore.data.configuration.MessagesFile;
import net.eternaln.survivalclasiccore.managers.CooldownManager;
import net.eternaln.survivalclasiccore.utils.Cooldown;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@CommandAlias("tpaccept")
public class TpacceptCommand extends BaseCommand {

    private Configuration config = SurvivalClasicCore.getInstance().getConfiguration();
    private MessagesFile messagesFile = SurvivalClasicCore.getMessagesFile();
    private CooldownManager cooldowns = SurvivalClasicCore.getInstance().getCooldowns();
    private int cooldownConfig = config.cmdCooldown;

    HashMap<Player, Player> requests = TpaCommand.getRequests();

    @CatchUnknown
    @HelpCommand("ayuda|help")
    public void help(CommandHelp help, Player sender) {
        if (cooldowns.getCooldown(sender.getUniqueId()) == null) {
            help.showHelp();
            cooldowns.create(sender.getUniqueId(), new Cooldown(TimeUnit.SECONDS.toMillis(cooldownConfig)));
            return;
        }
        Cooldown cooldown = cooldowns.getOrCreate(sender.getUniqueId(), TimeUnit.SECONDS.toMillis(cooldownConfig));
        if (!cooldown.hasExpired()) {
            Utils.send(sender, messagesFile.cooldown.replace("%time%", String.valueOf(TimeUnit.MILLISECONDS.toSeconds(cooldown.getRemaining()))));
            return;
        }
        cooldown.stop();
        cooldowns.create(sender.getUniqueId(), new Cooldown(TimeUnit.SECONDS.toMillis(cooldownConfig)));
        help.showHelp();
    }

    @Default
    public void teleportAccept(Player sender) {
        if (cooldowns.getCooldown(sender.getUniqueId()) == null) {
            onTeleportAccept(sender);
            cooldowns.create(sender.getUniqueId(), new Cooldown(TimeUnit.SECONDS.toMillis(cooldownConfig)));
            return;
        }
        Cooldown cooldown = cooldowns.getOrCreate(sender.getUniqueId(), TimeUnit.SECONDS.toMillis(cooldownConfig));
        if (!cooldown.hasExpired()) {
            Utils.send(sender, messagesFile.cooldown.replace("%time%", String.valueOf(TimeUnit.MILLISECONDS.toSeconds(cooldown.getRemaining()))));
            return;
        }
        cooldown.stop();
        cooldowns.create(sender.getUniqueId(), new Cooldown(TimeUnit.SECONDS.toMillis(cooldownConfig)));
        onTeleportAccept(sender);
    }

    private void onTeleportAccept(Player sender) {
        if (requests.containsKey(sender)) {
            Utils.send(sender, messagesFile.tpAccept).replace("%player%", requests.get(sender).getName());
            requests.get(sender).sendMessage(Utils.ct(messagesFile.tpAcceptTarget).replace("%player%", sender.getName()));
            sender.teleport(requests.get(sender).getLocation());
        } else {
            Utils.send(sender, messagesFile.tpAcceptNoRequest);
        }
    }

}
