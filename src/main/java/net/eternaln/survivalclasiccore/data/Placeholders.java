package net.eternaln.survivalclasiccore.data;

import lombok.RequiredArgsConstructor;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.data.mongo.PlayerData;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor
public class Placeholders extends PlaceholderExpansion {

    private final SurvivalClasicCore survivalClasicCore;

    @Override
    public String onPlaceholderRequest(Player player, @NotNull String params) {
        PlayerData data = SurvivalClasicCore.getDataManager().handleDataCreation(player.getUniqueId());

        if (params.equalsIgnoreCase("nick")) {
            if (data.getNickName() != null) {
                return Utils.ct(data.getNickName());
            }
            return player.getName();
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