package ar.edu.unq.desapp.grupoH022021.backenddesappapi.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "sellBuyActivity")
public class SellBuyActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column
    public LocalDate date;

    @Column
    public String cripto;

    @Column
    public int criptoCount;

    @Column
    public long criptoPrice;

    @Column
    public long operationAmount;

    @Column
    public String user;

    @Column
    public int operationCount;

    @Column
    public int reputationPoints;

    @Column
    public int type;
}
