package ar.edu.unq.desapp.grupoH022021.backenddesappapi.model;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "sellBuyActivity")
public class SellBuyActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column
    public Instant date;

    @Column
    public String cripto;

    @Column
    public int criptoCount;

    @Column
    public double criptoPrice;

    @Column
    public double operationAmount;

    @Column
    public String user;

    @Column
    public int operationCount;

    @Column
    public int reputationPoints;

    @Column
    public int type;

    @Column
    public String dateTimeFormatted;

    @Column
    public String criptoPriceFormatted;

    @Column
    public String operationAmountFormatted;
}
