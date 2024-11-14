package net.rudahee.conta.expensing.model.db.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.rudahee.conta.accounting.model.db.entities.Accounting;

import java.time.Instant;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(indexes = @Index(columnList = "expensingConcept", name = "expensing_concept_idx"))
public class Expensing {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private Double amount;

    @Enumerated(EnumType.STRING)
    private ExpensingConcept expensingConcept;

    private String description;

    private Instant date;

    @ManyToOne
    @JoinColumn(name="accounting_id")
    @JsonBackReference
    private Accounting accounting;

}
