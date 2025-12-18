package cn.iocoder.yudao.module.biz.service.people.util;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @param unit
 */
public record FuzzyDate(LocalDate date, ChronoUnit unit) implements Comparable<FuzzyDate> {
    /**
     * Unknown date
     */
    public static final FuzzyDate UNKNOWN = new FuzzyDate(LocalDate.MIN, ChronoUnit.FOREVER);
    static final Comparator<FuzzyDate> CMP = Comparator.comparing(FuzzyDate::date)
            .thenComparing(FuzzyDate::unit);

    static final DateTimeFormatter FMT_UNKNOWN = DateTimeFormatter.ofPattern("");
    static final DateTimeFormatter FMT_YYYY = DateTimeFormatter.ofPattern("yyyy");
    static final DateTimeFormatter FMT_YYYY_MM = DateTimeFormatter.ofPattern("yyyy-MM");
    static final DateTimeFormatter FMT_YYYY_MM_DD = DateTimeFormatter.ISO_DATE;
    static final DateTimeFormatter FMT_FULL = DateTimeFormatter.ISO_DATE_TIME;

    static final TreeMap<ChronoUnit, DateTimeFormatter> FMT_MAP = new TreeMap<>(Map.of(
            ChronoUnit.FOREVER, FMT_UNKNOWN,
            ChronoUnit.YEARS, FMT_YYYY,
            ChronoUnit.MONTHS, FMT_YYYY_MM,
            ChronoUnit.DAYS, FMT_YYYY_MM_DD
    ));

    public FuzzyDate(LocalDate day) {
        this(day, ChronoUnit.DAYS);
    }

    public FuzzyDate(Year year, Month month) {
        this(LocalDate.of(year.getValue(), month.getValue(), 1), ChronoUnit.MONTHS);
    }

    public FuzzyDate(Year year) {
        this(LocalDate.of(year.getValue(), 1, 1), ChronoUnit.YEARS);
    }

    @Override
    public int compareTo(FuzzyDate o) {
        // TODO: 12/8/25 field test
        return CMP.compare(this, o);
    }

    static DateTimeFormatter getFormatter(ChronoUnit unit) {
        return FMT_MAP.floorEntry(unit).getValue();
    }

    public static FuzzyDate parse(String date) {
        for (var entry : FMT_MAP.sequencedEntrySet().reversed()) {
            try {
                var formatter = entry.getValue();
                var chronoUnit = entry.getKey();
                var localDate = LocalDate.parse(date, formatter);
                return new FuzzyDate(localDate, chronoUnit);
            } catch (Exception ignored) {
            }
        }
        throw new IllegalArgumentException("Cannot parse date: " + date);
    }

    public String format() {
        // TODO: 12/8/25 field test
        return getFormatter(unit).format(date);
    }

    public LocalDate getLocalDate() {
        return date;
    }
}
