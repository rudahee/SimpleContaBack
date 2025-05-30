package net.rudahee.conta.expensing.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.rudahee.conta.expensing.model.db.entity.ExpensingConcept;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseDTO {

    private UUID id;
    private Double amount;
    private ExpensingConcept expensingConcept;
    private String description;
}
