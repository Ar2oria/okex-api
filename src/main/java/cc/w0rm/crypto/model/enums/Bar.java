package cc.w0rm.crypto.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static cc.w0rm.crypto.config.GlobalConfig.*;

@AllArgsConstructor
@Getter
public enum Bar {
    C_1M("1m", "1min", ONE_MIN_MILLIS),
    C_3M("3m", "3min", ONE_MIN_MILLIS * 3),
    C_5M("5m", "5min", ONE_MIN_MILLIS * 5),
    C_15M("15m", "15min", ONE_MIN_MILLIS * 15),
    C_30M("30m", "30min", ONE_MIN_MILLIS * 30),
    C_1H("1H", "1hour", ONE_HOUR_MILLIS),
    C_2H("2H", "2hour", ONE_HOUR_MILLIS * 2),
    C_4H("4H", "4hour", ONE_HOUR_MILLIS * 4),
    HK_6H("6H", "6hour", ONE_HOUR_MILLIS * 6),
    HK_12H("12H", "12hour", ONE_HOUR_MILLIS * 12),
    HK_1D("1D", "1day", ONE_DAY_MILLIS),
    HK_2D("2D", "2day", ONE_DAY_MILLIS * 2),
    HK_3D("3D", "3day", ONE_DAY_MILLIS * 3),
    HK_1W("1W", "1week", ONE_WEEK_MILLIS),
    HK_1M("1M", "1month", ONE_MON_MILLIS),
    HK_3M("3M", "3month", ONE_MON_MILLIS * 3),
    HK_6M("6M", "6month", ONE_MON_MILLIS * 6),
    HK_1Y("1Y", "1year", ONE_YEAR_MILLIS),
    UTC_6H("6Hutc", "6hour_utc", ONE_HOUR_MILLIS * 6),
    UTC_12H("12Hutc", "12hour_utc", ONE_HOUR_MILLIS * 12),
    UTC_1D("1Dutc", "1day_utc", ONE_DAY_MILLIS),
    UTC_2D("2Dutc", "2day_utc", ONE_DAY_MILLIS * 2),
    UTC_3D("3Dutc", "3day_utc", ONE_DAY_MILLIS * 3),
    UTC_1W("1Wutc", "1week_utc", ONE_WEEK_MILLIS),
    UTC_1M("1Mutc", "1month_utc", ONE_MON_MILLIS),
    UTC_3M("3Mutc", "3month_utc", ONE_MON_MILLIS * 3),
    UTC_6M("6Mutc", "6month_utc", ONE_MON_MILLIS * 6),
    UTC_1Y("1Yutc", "1year_utc", ONE_YEAR_MILLIS),


    ;

    private String simple;
    private String desc;
    private long millis;

    @Override
    public String toString() {
        return simple;
    }
}