package net.rudahee.conta.accounting.model.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.rudahee.conta.expensing.model.api.ExpenseDTO;
import net.rudahee.conta.incoming.model.api.IncomeDTO;

import java.time.Instant;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DailyAccountingDTO {


    private Instant date;
    private Double dailyExpense;
    private Double dailyIncome;
    private Double dailyProfit;
    private List<ExpenseDTO> listOfExpenses;
    private List<IncomeDTO> listOfIncomes;
}
