package ar.edu.unq.desapp.grupoH022021.backenddesappapi.utils;

import org.springframework.context.i18n.LocaleContextHolder;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class DateTimeUtils {

    public static String formatDate(Instant date){
        ZoneId zone = ZoneId.systemDefault();
        ZonedDateTime zoneDiteTime = date.atZone(zone);
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(LocaleContextHolder.getLocale());
        return zoneDiteTime.format(formatter);
    }
}
