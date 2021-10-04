package ar.edu.unq.desapp.grupoH022021.backenddesappapi.configuration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix="spring.api")
@Configuration
public class EnvironmentConfig {
    private String binanceUrl;
    private String brcaUrl;
    private String brcaToken;

    public String getBinanceUrl() {
        return binanceUrl;
    }

    public void setBinanceUrl(String binanceUrl) {
        this.binanceUrl = binanceUrl;
    }

    public String getBrcaUrl() {
        return brcaUrl;
    }

    public void setBrcaUrl(String brcaUrl) {
        this.brcaUrl = brcaUrl;
    }

    public String getBrcaToken() {
        return brcaToken;
    }

    public void setBrcaToken(String brcaToken) {
        this.brcaToken = brcaToken;
    }
}