package net.eternaln.survivalclasiccore.data;

import lombok.RequiredArgsConstructor;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.data.mongo.PlayerData;
import net.eternaln.survivalclasiccore.objects.staff.Staff;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor
public class Placeholders extends PlaceholderExpansion {

    private final SurvivalClasicCore survivalClasicCore;

    @Override
    public String onPlaceholderRequest(Player player, @NotNull String params) {
        PlayerData data = SurvivalClasicCore.getDataManager().handleDataCreation(player.getUniqueId());
        Staff staff = Staff.getStaff(player.getUniqueId());

        // %survivalclasic_nick%
        if (params.equalsIgnoreCase("nick")) {
            if (data.getNickName() != null) {
                return Utils.ct(data.getNickName());
            }
            return player.getName();
        }

        // %survivalclasic_vanish%
        if (params.equalsIgnoreCase("vanish")) {
            if (staff.isVanished()) {
                return Utils.ct(" &7(Oculto)");
            }
            if (!staff.isVanished()) {
                return "";
            }
        }

        // %survivalclasic_staffonline%
        if (params.equalsIgnoreCase("staffonline")) {
            if (Utils.getOnlineStaff().isEmpty()) {
                return Utils.ct("&cNo hay staff online");
            }

            return String.valueOf(Utils.getOnlineStaff().size());
        }

        return "Placeholder not found";
    }

    @Override
    public @NotNull String getIdentifier() {
        return "survivalclasic";
    }

    @Override
    public @NotNull String getAuthor() {
        return String.valueOf(survivalClasicCore.getDescription().getAuthors());
    }

    @Override
    public @NotNull String getVersion() {
        return survivalClasicCore.getDescription().getVersion();
    }
}