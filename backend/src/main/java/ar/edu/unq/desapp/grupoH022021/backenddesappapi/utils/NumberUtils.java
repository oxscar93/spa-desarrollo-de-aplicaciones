package ar.edu.unq.desapp.grupoH022021.backenddesappapi.utils;

import org.springframework.context.i18n.LocaleContextHolder;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

public class NumberUtils {

    public static String formatWithCurrency(Double price){
        NumberFormat moneyFormat = NumberFormat.getCurrencyInstance(LocaleContextHolder.getLocale());

        return moneyFormat.format(price);
    }
    
    public static Double formatWithCurrencyAux(Double price){
        NumberFormat moneyFormat = DecimalFormat.getNumberInstance(LocaleContextHolder.getLocale());
        Double res = 0.0;
        
        try {
			res =  moneyFormat.parse(moneyFormat.format(price)).doubleValue();
		} catch (ParseException e) {
			e.printStackTrace();
		}
        return res;
    }
}
