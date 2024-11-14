package net.rudahee.conta.incoming.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.rudahee.conta.incoming.model.db.entity.IncomingConcept;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncomeDTO {

    private Double amount;
    private IncomingConcept incomingConcept;
    private String description;

}
