package ar.edu.unq.desapp.grupoH022021.backenddesappapi.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TransactionDto {
    public int id;
    public int activityId;
    @NotBlank(message = "Cripto is mandatory")
    @NotNull(message = "Cripto is mandatory")
    public String cripto;
    @NotBlank(message = "User is mandatory")
    @NotNull(message = "User is mandatory")
    public String user;
    public int criptoCount;
    public long criptoPrice;
    public long operationAmount;
    public int type;
    public int status;
}
