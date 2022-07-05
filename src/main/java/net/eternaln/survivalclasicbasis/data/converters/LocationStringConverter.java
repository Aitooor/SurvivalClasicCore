package net.eternaln.survivalclasicbasis.data.converters;

import de.exlll.configlib.Converter;
import net.eternaln.survivalclasicbasis.utils.LocationUtil;
import org.bukkit.Location;

public class LocationStringConverter implements Converter<Location, String> {

    @Override
    public String convertTo(Location location, Converter.ConversionInfo conversionInfo) {
        return LocationUtil.parseToString(location);
    }

    @Override
    public Location convertFrom(String s, ConversionInfo conversionInfo) {
        return LocationUtil.parseToLocation(s);
    }
}