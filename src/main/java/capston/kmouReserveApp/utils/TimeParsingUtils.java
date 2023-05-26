package capston.kmouReserveApp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TimeParsingUtils {
    public static LocalDateTime formatterLocalDateTime(String time){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(time,formatter);
    }

    public static String formatterString(LocalDateTime time){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return time.format(formatter);
    }

    public static Date formatterDate(String dateFormat) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(dateFormat);
        return date;
    }

    public static Date LocalToDate(LocalDateTime dateTime) throws ParseException{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateFormat = formatterString(dateTime);
        dateFormat = dateFormat.substring(0,10);
        Date date = simpleDateFormat.parse(dateFormat);
        return date;
    }
}
