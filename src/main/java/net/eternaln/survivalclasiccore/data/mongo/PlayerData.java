package net.eternaln.survivalclasiccore.data.mongo;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import lombok.Getter;
import lombok.Setter;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import org.bson.Document;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
public class PlayerData {

    private final UUID uuid;
    @Setter
    private String nickName;
    private Map<String, String> homes;

    public PlayerData(UUID uuid) {
        this.uuid = uuid;
        this.homes = new HashMap<>();
        load();
    }

    public void load() {
        //TODO On Mongo PlayerData load error document = null
        Document document = SurvivalClasicCore.getMongo().getPlayer(this.getUuid());
        System.out.println("PlayerData load: " + document);
        if (document != null) {
            this.nickName = document.getString("nick");
            this.homes = document.get("homes", Map.class);
        }
    }

    public void save() {
        Document document = new Document();
        document.put("uuid", getUuid().toString());
        document.put("nick", getNickName());
        document.put("homes", homes);
        SurvivalClasicCore.getMongo().getMongoCol().replaceOne(Filters.eq("uuid", getUuid().toString()), document, new ReplaceOptions().upsert(true));
    }
}
