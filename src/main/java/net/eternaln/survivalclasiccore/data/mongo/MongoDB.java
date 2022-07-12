package net.eternaln.survivalclasiccore.data.mongo;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import lombok.Getter;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

@Getter
public class MongoDB {
    private MongoDatabase mongoDb;
    private MongoCollection<Document> mongoCol;

    public MongoDB() {
        connect();
    }

    public void connect() {
        Utils.async(() -> {
            MongoCredentials credentials = SurvivalClasicCore.getConfiguration().getCredentials();

            try {
                ConnectionString connectionString = new ConnectionString(credentials.link);
                MongoClientSettings settings = MongoClientSettings.builder()
                        .applyConnectionString(connectionString)
                        .serverApi(ServerApi.builder()
                                .version(ServerApiVersion.V1)
                                .build())
                        .build();
                MongoClient mongoClient = MongoClients.create(settings);
                mongoDb = mongoClient.getDatabase(credentials.getDatabase());
                mongoCol = mongoDb.getCollection(credentials.getCollection());

                Bukkit.getLogger().info("[SurvivalClasicCore] " + ChatColor.GREEN + "Connected to MongoDB!");
            } catch (Exception e) {
                Bukkit.getLogger().info("[SurvivalClasicCore] " + ChatColor.RED + "The plugin can't reach the MongoDB connection");
            }
        });
    }

    public Document getPlayer(UUID uuid) {
        AtomicReference<Document> rt = new AtomicReference<>(null);
        Utils.async(() -> {
            if (mongoCol.find(Filters.eq("uuid", uuid.toString())).first() != null) {
                rt.set(mongoCol.find(Filters.eq("uuid", uuid.toString())).first());
            }
        });
        return rt.get();
    }
}