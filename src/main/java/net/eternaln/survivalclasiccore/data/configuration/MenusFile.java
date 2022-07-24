package net.eternaln.survivalclasiccore.data.configuration;

import de.exlll.configlib.annotation.Comment;
import de.exlll.configlib.configs.yaml.BukkitYamlConfiguration;
import de.exlll.configlib.format.FieldNameFormatters;
import lombok.Getter;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;

import java.io.File;

@Getter
public class MenusFile extends BukkitYamlConfiguration {

    @Comment({"", "Main"})
    public String mainMenuTitle = "Menu Principal";

    @Comment({"", "Warps"})
    public String warpsMenuTitle = "Lista de Warps";
    public String helpCommand =
            "\n&r" +
                    "\n&6&lETERNAL &8(&fAyuda&7)"+
                    "\n&r"+
                    "\n&b/menu &7- &fAbre el menu principal"+
                    "\n&b/warps &7- &fAbre el menu de warps"+
                    "\n&b/pwarp &7- &fAbre el menu de warps de usuarios"+
                    "\n&b/kits &7- &fAbre el menu de kits"+
                    "\n&b/ah &7- &fAbre el menu de subastas"+
                    "\n&b/trabajos ayuda &7- &fAyuda de trabajos"+
                    "\n&r";

    @Comment({"", "Social"})
    public String socialMenuTitle = "Redes Sociales";

    public MenusFile() {
        super(
                new File(SurvivalClasicCore.getInstance().getDataFolder(), "messages/menus.yml").toPath(),
                BukkitYamlProperties.builder().setFormatter(FieldNameFormatters.LOWER_UNDERSCORE).build()
        );
        this.loadAndSave();
    }
}
