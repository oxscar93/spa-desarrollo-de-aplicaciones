package ar.edu.unq.desapp.grupoH022021.backenddesappapi;

import static org.springframework.test.util.AssertionErrors.assertTrue;

import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;

@SpringBootTest
public class i18nTest {
	
	@Autowired
	private MessageSource messages;
	
	@Test
    public void getLocaleMessages() {
		
        try {
        	String title = messages.getMessage("title", null, Locale.ENGLISH);
    		String text = messages.getMessage("text", null, LocaleContextHolder.getLocale());
            assertTrue("msg title: ", title.equalsIgnoreCase("Hello I18N with Spring"));
            //assertTrue("msg text: ", text.equalsIgnoreCase("Esto es un texto"));
    	}
    	catch (NoSuchMessageException ex) {
    		ex.getStackTrace();
    	}
    }

}