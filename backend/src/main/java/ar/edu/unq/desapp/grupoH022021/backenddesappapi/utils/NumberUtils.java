package ar.edu.unq.desapp.grupoH022021.backenddesappapi.utils;

import org.springframework.context.i18n.LocaleContextHolder;

import java.text.NumberFormat;

public class NumberUtils {

    public static String formatWithCurrency(Double price){
        NumberFormat moneyFormat = NumberFormat.getCurrencyInstance(LocaleContextHolder.getLocale());

        return moneyFormat.format(price);
    }
}
