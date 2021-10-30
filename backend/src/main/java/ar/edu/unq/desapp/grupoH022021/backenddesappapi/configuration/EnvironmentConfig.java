package ar.edu.unq.desapp.grupoH022021.backenddesappapi.configuration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix="spring.api")
@Configuration
public class EnvironmentConfig {
    private String binanceUrl;
    private String dollarPrice;

    public String getDollarPrice() {
		return dollarPrice;
	}

	public void setDollarPrice(String dollarPrice) {
		this.dollarPrice = dollarPrice;
	}

	public String getBinanceUrl() {
        return binanceUrl;
    }

    public void setBinanceUrl(String binanceUrl) {
        this.binanceUrl = binanceUrl;
    }

}