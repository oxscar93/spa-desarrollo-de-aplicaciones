package ar.edu.unq.desapp.grupoH022021.backenddesappapi.dto;

import java.time.LocalDateTime;

public class TransactionDto {
    public int id;
    public int activityId;
    public String cripto;
    public String user;
    public int criptoCount;
    public long criptoPrice;
    public long operationAmount;
    public int type;
    public int status;
}
