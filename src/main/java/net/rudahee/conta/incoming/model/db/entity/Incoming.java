package net.rudahee.conta.incoming.model.db.entity;

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
@Table(indexes = @Index(columnList = "incomingConcept", name = "accounting_concept_idx"))
public class Incoming {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private Double amount;

    @Enumerated(EnumType.STRING)
    private IncomingConcept incomingConcept;

    private String description;

    private Instant date;

    @ManyToOne
    @JoinColumn(name="accounting_id")
    @JsonBackReference
    private Accounting accounting;



}
