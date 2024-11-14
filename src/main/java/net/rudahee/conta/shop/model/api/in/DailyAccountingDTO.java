package net.rudahee.conta.shop.model.api.in;

import lombok.*;
import net.rudahee.conta.expensing.model.api.ExpenseDTO;
import net.rudahee.conta.incoming.model.api.IncomeDTO;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailyAccountingDTO {

    private UUID shopId;
    private List<IncomeDTO> income;
    private List<ExpenseDTO> expense;
    private Instant date;

}
