package net.eternaln.survivalclasiccore.commands.admin;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

@CommandAlias("xp|exp|experiencia")
@CommandPermission("survivalclasiccore.exp")
public class ExpCommand extends BaseCommand {

    @Subcommand("add|añadir")
    public class GiveExpSubCommand extends BaseCommand {
        @Subcommand("amount|cantidad")
        public class AmountExpSubCommand extends BaseCommand {
            @Subcommand("me|yo")
            public void give(Player sender, int amount) {
                sender.giveExp(amount);
                sender.playSound(sender.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
            }

            @Subcommand("other|otro|otros|others")
            @CommandPermission("survivalclasiccore.exp.other")
            @CommandCompletion("@players")
            public void giveOther(String target, int amount) {
                Player targetPlayer = Bukkit.getPlayer(target);
                targetPlayer.giveExp(amount);
                targetPlayer.playSound(targetPlayer.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
            }
        }

        @Subcommand("level|nivel")
        public class LevelExpSubCommand extends BaseCommand {
            @Subcommand("me|yo")
            public void give(Player sender, int amount) {
                sender.giveExpLevels(amount);
                sender.playSound(sender.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
            }

            @Subcommand("other|otro|otros|others")
            @CommandPermission("survivalclasiccore.exp.other")
            @CommandCompletion("@players")
            public void giveOther(String target, int amount) {
                Player targetPlayer = Bukkit.getPlayer(target);
                targetPlayer.giveExpLevels(amount);
                targetPlayer.playSound(targetPlayer.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
            }
        }
    }

    @Subcommand("set|establecer|establecerxp|establecerexp")
    public class SetExpSubCommand extends BaseCommand {
        @Subcommand("me|yo")
        public void set(Player sender, int amount) {
            sender.setExp(0);
            sender.setLevel(amount);
            sender.playSound(sender.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
        }

        @Subcommand("other|otro|otros|others")
        @CommandPermission("survivalclasiccore.exp.other")
        @CommandCompletion("@players")
        public void setOther(String target, int amount) {
            Player targetPlayer = Bukkit.getPlayer(target);
            targetPlayer.setExp(0);
            targetPlayer.setLevel(amount);
            targetPlayer.playSound(targetPlayer.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
        }
    }

    @Subcommand("clear|limpiar")
    public class RemoveExpSubCommand extends BaseCommand {
        @Subcommand("me|yo")
        public void remove(Player sender) {
            int currentExp = sender.getTotalExperience();
            sender.setExp(0);
            sender.setLevel(0);
            sender.playSound(sender.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
        }

        @Subcommand("other|otro|otros|others")
        @CommandPermission("survivalclasiccore.exp.other")
        @CommandCompletion("@players")
        public void removeOther(String target) {
            Player targetPlayer = Bukkit.getPlayer(target);
            targetPlayer.setExp(0);
            targetPlayer.setLevel(0);
            targetPlayer.playSound(targetPlayer.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
        }
    }
}
