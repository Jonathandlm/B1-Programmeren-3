package be.leerstad.eindwerk.util;

import be.leerstad.eindwerk.business.cache.ICache;
import be.leerstad.eindwerk.model.Interaction;
import org.apache.log4j.Logger;

import java.time.Year;
import java.time.YearMonth;
import java.util.Set;
import java.util.TreeSet;

public class CacheUtil {

    private static final Logger LOG = Logger.getLogger(CacheUtil.class.getName());

    public static Set<YearMonth> getMonthsFromCache(ICache<?, ? extends Interaction> cache) {
        Set<YearMonth> months = new TreeSet<>();

        for (Interaction interaction : cache) {
            months.add(YearMonth.from(interaction.getLogfile().getLogfileDate()));
        }

        return months;
    }

    public static Set<Year> getYearsFromCache(ICache<?, ? extends Interaction> cache) {
        Set<Year> years = new TreeSet<>();

        for (Interaction interaction : cache) {
            years.add(Year.from(interaction.getLogfile().getLogfileDate()));
        }

        return years;
    }
}
