package be.leerstad.eindwerk.business.report;

import be.leerstad.eindwerk.business.cache.SessionCache;
import be.leerstad.eindwerk.business.cache.SiteApplicationCache;
import be.leerstad.eindwerk.business.cache.VisitCache;
import be.leerstad.eindwerk.model.Session;
import be.leerstad.eindwerk.model.Visit;
import be.leerstad.eindwerk.util.CacheUtil;

import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.ToIntFunction;

import static java.util.Collections.reverseOrder;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

public class Query {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM yyyy");

    public LinkedHashMap<String, LinkedHashMap<String, Integer>> getMonthlyApplicationTotals(ToIntFunction<? super Visit> mapper) {
        LinkedHashMap<String, LinkedHashMap<String, Integer>> result = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> monthlyVisits;
        VisitCache visitCache = VisitCache.getInstance();
        SiteApplicationCache siteApplicationCache = SiteApplicationCache.getInstance();
        Set<YearMonth> months = CacheUtil.getMonthsFromCache(visitCache);
        int total;

        for (String siteApplication : siteApplicationCache) {
            monthlyVisits = new LinkedHashMap<>();
            for (YearMonth month : months) {
                total = visitCache.values().stream().filter(visit -> month.equals(YearMonth.from(visit.getLogfile().getLogfileDate()))
                        && siteApplication.equals(visit.getSiteApplication().getApplication())).mapToInt(mapper).sum();
                monthlyVisits.put(month.format(formatter), total);
            }
            result.put(siteApplication, monthlyVisits);
        }

        return result;
    }

    public LinkedHashMap<String, Integer> getSchoolVisitsByMonth(YearMonth yearMonth) {
        LinkedHashMap<String, Integer> result = new LinkedHashMap<>();
        VisitCache visitCache = VisitCache.getInstance();

        visitCache.values().stream().filter(visit -> yearMonth.equals(YearMonth.from(visit.getLogfile().getLogfileDate())))
                .collect(groupingBy(Visit::getSchool, summingInt(Visit::getNumberOfRequests)))
                .entrySet().stream().sorted(Map.Entry.comparingByValue(reverseOrder())).limit(10)
                .forEachOrdered(x -> result.put(x.getKey().getSite(), x.getValue()));

        return result;
    }

    public LinkedHashMap<String, Integer> getUserTotalsByMonth(YearMonth yearMonth, ToIntFunction<? super Session> mapper) {
        LinkedHashMap<String, Integer> result = new LinkedHashMap<>();
        SessionCache sessionCache = SessionCache.getInstance();

        sessionCache.values().stream().filter(session -> yearMonth.equals(YearMonth.from(session.getLogfile().getLogfileDate())))
                .collect(groupingBy(Session::getUser, summingInt(mapper)))
                .entrySet().stream().sorted(Map.Entry.comparingByValue(reverseOrder())).limit(10)
                .forEachOrdered(x -> result.put(x.getKey().getFullName(), x.getValue()));

        return result;
    }

    public LinkedHashMap<String, Integer> getMonthTotalsByYear(Year year, ToIntFunction<? super Session> mapper) {
        LinkedHashMap<String, Integer> result = new LinkedHashMap<>();
        SessionCache sessionCache = SessionCache.getInstance();

        sessionCache.values().stream().filter(session -> year.equals(Year.from(session.getLogfile().getLogfileDate())))
                .collect(groupingBy(session -> session.getLogfile().getLogfileDate().getMonth().toString(), summingInt(mapper)))
                .entrySet().stream().sorted(Map.Entry.comparingByValue(reverseOrder()))
                .forEachOrdered(x -> result.put(x.getKey(), x.getValue()));

        return result;
    }

    public LinkedHashMap<String, Integer> getSiteTotals(ToIntFunction<? super Session> mapper) {
        LinkedHashMap<String, Integer> result = new LinkedHashMap<>();
        SessionCache sessionCache = SessionCache.getInstance();

        sessionCache.values().stream()
                .collect(groupingBy(Session::getSite, summingInt(mapper)))
                .entrySet().stream().sorted(Map.Entry.comparingByValue(reverseOrder())).limit(10)
                .forEachOrdered(x -> result.put(x.getKey().getSite(), x.getValue()));

        return result;
    }

}
