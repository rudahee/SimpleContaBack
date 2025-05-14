package net.rudahee.conta.incoming.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.rudahee.conta.incoming.model.db.entity.IncomingConcept;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncomeDTO {

    private UUID id;
    private Double amount;
    private IncomingConcept incomingConcept;
    private String description;

}
