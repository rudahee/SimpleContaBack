package net.rudahee.conta.accounting.model.db.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.rudahee.conta.expensing.model.db.entity.Expensing;
import net.rudahee.conta.incoming.model.db.entity.Incoming;
import net.rudahee.conta.shop.model.db.entity.Shop;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Accounting {

    @Id
    @GeneratedValue
    private UUID id;

    @OneToMany(mappedBy = "accounting",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    @JsonManagedReference
    private List<Incoming> incomings;

    @OneToMany(mappedBy = "accounting",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    @JsonManagedReference
    private List<Expensing> expensings;

    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    @JsonBackReference
    private Shop shop;

    private Instant date;

    private Double dailyIncome;
    private Double dailyExpense;
    private Double dailyProfit;




}
