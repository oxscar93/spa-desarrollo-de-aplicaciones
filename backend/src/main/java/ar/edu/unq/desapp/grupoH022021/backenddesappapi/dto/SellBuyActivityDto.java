package ar.edu.unq.desapp.grupoH022021.backenddesappapi.dto;

import javax.persistence.Column;
import java.time.Instant;
import java.time.LocalDateTime;

public class SellBuyActivityDto {
    public String date;
    public String cripto;
    public Double criptoPrice;
    public int criptoCount;
    public Double operationAmount;
    public String user;
    public Instant dateTime;
}
