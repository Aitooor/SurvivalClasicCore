package net.eternaln.survivalclasiccore.data.mongo;

import de.exlll.configlib.annotation.ConfigurationElement;
import lombok.AllArgsConstructor;
import lombok.Getter;

@ConfigurationElement
@AllArgsConstructor
@Getter
public class MongoCredentials {
    public String link;
    public String database;
    public String collection;

    public MongoCredentials() {
        this("", "", "");
    }
}
