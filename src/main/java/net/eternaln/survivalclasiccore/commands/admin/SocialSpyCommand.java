package net.eternaln.survivalclasiccore.commands.admin;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CatchUnknown;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import lombok.Getter;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@CommandAlias("socialspy|revisarchat")
@CommandPermission("survivalclasiccore.socialspy")
public class SocialSpyCommand extends BaseCommand {

    @Getter
    private static final List<UUID> socialSpyList = new ArrayList<>();

    @Default
    @CatchUnknown
    public void toggleSocialSpy(Player sender) {
        if (socialSpyList.contains(sender.getUniqueId())) {
            socialSpyList.remove(sender.getUniqueId());
            Utils.send(sender, "&cSocial spy desactivado");
        } else {
            socialSpyList.add(sender.getUniqueId());
            Utils.send(sender, "&aSocial spy activado");
        }
    }
}
