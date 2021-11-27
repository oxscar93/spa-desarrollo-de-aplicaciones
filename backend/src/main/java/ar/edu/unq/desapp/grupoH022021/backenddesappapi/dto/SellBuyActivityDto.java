package ar.edu.unq.desapp.grupoH022021.backenddesappapi.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

public class SellBuyActivityDto {
    public String date;

    @NotBlank(message = "Cripto is mandatory")
    @NotNull(message = "Cripto is mandatory")
    public String cripto;
    public Double criptoPrice;
    public int criptoCount;
    public Double operationAmount;
    @NotBlank(message = "User is mandatory")
    @NotNull(message = "User is mandatory")
    public String user;
    public Instant dateTime;
}
