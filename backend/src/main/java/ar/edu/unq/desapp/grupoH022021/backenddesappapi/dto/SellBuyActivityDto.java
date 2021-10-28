package ar.edu.unq.desapp.grupoH022021.backenddesappapi.dto;

import javax.persistence.Column;
import java.time.LocalDateTime;

public class SellBuyActivityDto {
    public String date;
    public String cripto;
    public long criptoPrice;
    public int criptoCount;
    public int operationAmount;
    public String user;
}
