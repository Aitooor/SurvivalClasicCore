package net.eternaln.survivalclasiccore.commands.admin;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.data.configuration.MessagesFile;
import net.eternaln.survivalclasiccore.utils.Cooldown;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@CommandAlias("speed|velocidad")
@CommandPermission("survivalclasic.speed")
public class SpeedCommand extends BaseCommand {

    MessagesFile messageFile = SurvivalClasicCore.getMessagesFile();
    private Cooldown<UUID> cooldown = new Cooldown<>(SurvivalClasicCore.getConfiguration().getCmdCooldown(), TimeUnit.SECONDS);

    @Subcommand("fly|volar|vuelo")
    @CommandPermission("survivalclasic.speed.fly")
    public class SpeedCommandFly extends BaseCommand {

        @Default
        @CommandCompletion("0-10")
        public void flySpeedInternal(Player sender, int speed) {
            if (!cooldown.isCooldownOver(sender.getUniqueId()) && !sender.hasPermission("survivalclasic.cooldown.bypass")) {
                String cooldownTime = cooldown.getFormattedRemainingString(sender.getUniqueId());
                Utils.send(sender, messageFile.cooldown.replace("%time%", cooldownTime));
                return;
            }
            cooldown.addToCooldown(sender.getUniqueId());

            float speeds = (float) speed / 10;

            if (speed > 10 || speed < 0) {
                Utils.send(sender, "&cLa velocidad debe estar entre 0 y 10");
                return;
            }

            if (speeds == sender.getFlySpeed()) {
                Utils.send(sender, "&cTienes la velocidad de caminar desactivada");
                return;
            }

            sender.setFlySpeed((float) speed / 10);
            Utils.send(sender, "&aHas cambiado la velocidad de vuelo a &b" + speed);
        }

        @Subcommand("remove|delete|borrar|quitar")
        public void remove(Player sender) {
            if (!cooldown.isCooldownOver(sender.getUniqueId()) && !sender.hasPermission("survivalclasic.cooldown.bypass")) {
                String cooldownTime = cooldown.getFormattedRemainingString(sender.getUniqueId());
                Utils.send(sender, messageFile.cooldown.replace("%time%", cooldownTime));
                return;
            }
            cooldown.addToCooldown(sender.getUniqueId());

            if (sender.getFlySpeed() == 0.1F) {
                Utils.send(sender, "&cYa tienes la velocidad de vuelo desactivada");
                return;
            }
            sender.setFlySpeed(0.1F);
            Utils.send(sender, "&cHas restablecido la velocidad de vuelo");
        }

    }

    @Subcommand("walk|caminar|caminar")
    @CommandPermission("survivalclasic.speed.walk")
    public class SpeedCommandWalk extends BaseCommand {

        @Default
        @CommandCompletion("0-10")
        public void walkSpeedInternal(Player sender, int speed) {
            if (!cooldown.isCooldownOver(sender.getUniqueId()) && !sender.hasPermission("survivalclasic.cooldown.bypass")) {
                String cooldownTime = cooldown.getFormattedRemainingString(sender.getUniqueId());
                Utils.send(sender, messageFile.cooldown.replace("%time%", cooldownTime));
                return;
            }
            cooldown.addToCooldown(sender.getUniqueId());

            float speeds = (float) speed / 10;

            if (speed > 10 || speed < 0) {
                Utils.send(sender, "&cLa velocidad debe estar entre 0 y 10");
                return;
            }

            if (speeds == sender.getWalkSpeed()) {
                Utils.send(sender, "&cTienes la velocidad de caminar desactivada");
                return;
            }

            sender.setWalkSpeed(speeds);
            Utils.send(sender, "&fHas cambiado la velocidad de caminar a &b" + speed);
        }

        @Subcommand("remove|delete|borrar|quitar")
        public void remove(Player sender) {
            if (!cooldown.isCooldownOver(sender.getUniqueId()) && !sender.hasPermission("survivalclasic.cooldown.bypass")) {
                String cooldownTime = cooldown.getFormattedRemainingString(sender.getUniqueId());
                Utils.send(sender, messageFile.cooldown.replace("%time%", cooldownTime));
                return;
            }
            cooldown.addToCooldown(sender.getUniqueId());

            if (sender.getWalkSpeed() == 0.2F) {
                Utils.send(sender, "&cYa tienes la velocidad de caminar desactivada");
                return;
            }
            sender.setWalkSpeed(0.2F);
            Utils.send(sender, "&cHas restablecido la velocidad de caminar");
        }
    }

}
