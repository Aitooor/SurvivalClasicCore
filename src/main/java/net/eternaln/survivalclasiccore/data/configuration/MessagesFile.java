package net.eternaln.survivalclasiccore.data.configuration;

import de.exlll.configlib.annotation.Comment;
import de.exlll.configlib.configs.yaml.BukkitYamlConfiguration;
import de.exlll.configlib.format.FieldNameFormatters;
import lombok.Getter;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@Getter
public class MessagesFile extends BukkitYamlConfiguration {

    @Comment({"", "Common"})
    public String prefix = "&6&lETERNAL &r";
    public String noPermission = "&cNo tienes permisos para hacer eso";
    public String playerNotFound = "&cEl jugador no existe";
    public String noOnlinePlayer = "&fEl jugador &b%player% &fno esta online";
    public String cooldown = "&cDebes esperar &b%time% &csegundos para hacer eso";
    public String reload = "&aConfig recargada";
    public String setSpawn = "&aSpawn establecido";

    @Comment({"", "Teleport"})
    public String tpSpawn = "&aTeletransportado al spawn";
    public String tpSpawnOther = "&aTeletransportado al jugador &b%player%";
    public String tpSpawnAll = "&aTeletransportado a todos los jugadores";
    public String tpDeny = "&cHas denegado la peticion de &b%player%";
    public String tpDenyNoRequest = "&cNo tienes peticiones pendientes";
    public String tpAccept = "&aHas aceptado la peticion de &b%player%";
    public String tpAcceptTarget = "&fEl jugador &b%player% &fha aceptado tu peticion";
    public String tpAcceptNoRequest = "&cNo tienes peticiones pendientes";
    public String tpaSender = "&aHas enviado solicitud de teletransporte a &b%player%";
    public String tpaTarget = "&aHas recibido una solicitud de teletransporte de &b%player%\n&7Usa &b/tpaccept &7para aceptarla\n&7o &b/tpdeny &7para denegarla";
    public String tpSelf = "&cNo puedes teletransportarte a ti mismo";
    public String tpAll = "&aTeletransportado a &b%player%";
    public String tpAllSelf = "&cNo puedes teletransportarte a ti mismo";
    public String tpAllSender = "&fHas teletransportado a todos hacia &b%player%";
    public String tpTop = "&aHas sido teletransportado hacia el top";
    public String tpTopFail = "&cNo hay ningun bloque mas arriba";
    public String tpPos = "&aHas sido teletransportado a la posicion &7X &b%x% &7Y &b%y% &7Z &b%z%";
    public String tpPosRealNumber = "&cLos datos deben ser numeros de cordenadas";
    public String tpPosWriteNumber = "&cEspecifica las cordenadas &7X, Y y Z";
    public String tpHereSender = "&aHas teletransportado hacia ti a &b%player%";
    public String tpHereTarget = "&aHas teletransportado a &b%player% &ahacia ti";

    @Comment({"", "Home"})
    public String homeNotFound = "&cLa casa no existe";
    public String homeNotSet = "&cNo has establecido una casa";
    public String homeSet = "&aHas establecido la casa &b%home%";
    public String maxHomes = "&cYa tienes el máximo de casas";
    public String homeExists = "&cYa tienes una casa con ese nombre";
    public String homeDeleted = "&aHas eliminado la casa &b%home%";
    public String homeNotExists = "&cNo tienes una casa con ese nombre";
    public String homeTeleported = "&aHas teletransportado a la casa &b%home%";

    @Comment({"", "Warps"})
    public String warpNotFound = "&cEl warp no existe";
    public String tpWarp = "&aTeletransportado al warp &b%warp%";
    public String alreadyExistWarp = "&cYa tienes un warp con el nombre &b%warp%";
    public String warpSet = "&aWarp &b%warp% &aestablecido";
    public String warpRemoved = "&fWarp &c%warp% &fborrado";

    @Comment({"", "Gamemode"})
    public String cannotChangeGamemode = "&cNo puedes cambiar de gamemode";

    public String setSurvival = "&fHas cambiado tu modo de juego a &eSurvival";
    public String alreadySurvival = "&fYa tienes el modo de juego &eSurvival";
    public String alreadySurvivalTarget = "&fYa tienes el modo de juego &eSurvival";
    public String setSurvivalSender = "&fHas cambiado el modo de juego de &b%player% &fa &eSurvival";
    public String setSurvivalTarget = "&fTu modo de juego ha sido cambiado por &b%player% &fa &eSurvival";

    public String setCreative = "&fHas cambiado tu modo de juego a &eCreativo";
    public String alreadyCreative = "&fYa tienes el modo de juego &eCreativo";
    public String alreadyCreativeTarget = "&fYa tienes el modo de juego &eCreativo";
    public String setCreativeSender = "&fHas cambiado el modo de juego de &b%player% &fa &eCreativo";
    public String setCreativeTarget = "&fTu modo de juego ha sido cambiado por &b%player% &fa &eCreativo";

    public String setAdventure = "&fHas cambiado tu modo de juego a &eAventura";
    public String alreadyAdventure = "&fYa tienes el modo de juego &eAventura";
    public String alreadyAdventureTarget = "&fYa tienes el modo de juego &eAventura";
    public String setAdventureSender = "&fHas cambiado el modo de juego de &b%player% &fa &eAventura";
    public String setAdventureTarget = "&fTu modo de juego ha sido cambiado por &b%player% &fa &eAventura";

    public String setSpectator = "&fHas cambiado tu modo de juego a &eEspectador";
    public String alreadySpectator = "&fYa tienes el modo de juego &eEspectador";
    public String alreadySpectatorTarget = "&fYa tienes el modo de juego &eEspectador";
    public String setSpectatorSender = "&fHas cambiado el modo de juego de &b%player% &fa &eEspectador";
    public String setSpectatorTarget = "&fTu modo de juego ha sido cambiado por &b%player% &fa &eEspectador";

    @Comment({"", "Item"})
    public String itemGived = "&aObjeto Entregado";
    public List<String> itemHelp = Arrays.asList(
            "&8&m-----------------------------------------",
            "&6&lETERNAL &7Ayuda Items",
            "&r",
            "&b/itemc give &7(gold/plate) (coin/fragment)",
            "&b/objetoc dar &7(oro/plata) (moneda/fragmento)",
            "&8&m-----------------------------------------"
    );

    public MessagesFile() {
        super(
                new File(SurvivalClasicCore.getInstance().getDataFolder(), "messages.yml").toPath(),
                BukkitYamlProperties.builder().setFormatter(FieldNameFormatters.LOWER_UNDERSCORE).build()
        );
        this.loadAndSave();
    }
}
