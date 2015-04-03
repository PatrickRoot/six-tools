/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package org.eu.sixlab.sixtools.sixplan;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.IsoFields;

/**
 * //TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/4/3 18:22
 */
public class SixPlanUtil {

    public static LocalDate str2Date(String str) {

        Integer year = Integer.valueOf(str.substring(0, 4));
        Integer month = Integer.valueOf(str.substring(5, 7));
        Integer day = Integer.valueOf(str.substring(9, 11));

        return LocalDate.of(year, month, day);
    }

    public static String date2Str(LocalDate localDate) {

        Integer year = localDate.getYear();
        Integer month = localDate.getMonthValue();
        Integer day = localDate.getDayOfMonth();
        Integer season = (month - 1) / 3 + 1;
        Integer week = localDate.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);

        LocalDate newLocalDate = LocalDate.of(year, month ,day);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        StringBuilder sb = new StringBuilder(newLocalDate.format(formatter));

        sb.replace(4,5,String.valueOf(season));
        sb.replace(7,9,String.valueOf(week));

        return sb.toString();
    }
}
