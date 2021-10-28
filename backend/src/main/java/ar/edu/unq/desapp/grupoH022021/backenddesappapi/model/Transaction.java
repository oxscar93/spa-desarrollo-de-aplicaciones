package ar.edu.unq.desapp.grupoH022021.backenddesappapi.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

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
    public int status;

    @Column
    public int type;
}
