package net.rudahee.conta.shop.model.db.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.rudahee.conta.accounting.model.db.entities.Accounting;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shop {


    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;


    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private List<Accounting> accountings;


    private Double accumulatedIncome;
    private Double accumulatedExpense;
    private Double accumulatedProfit;
}
