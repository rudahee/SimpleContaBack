package net.rudahee.conta.accounting.services;

import net.rudahee.conta.accounting.model.api.DailyAccountingDTO;
import net.rudahee.conta.accounting.model.db.entities.Accounting;
import net.rudahee.conta.accounting.model.db.repository.AccountingRepository;
import net.rudahee.conta.expensing.model.api.ExpenseDTO;
import net.rudahee.conta.incoming.model.api.IncomeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AccountingService {

    @Autowired
    private AccountingRepository accountingRepository;

    public List<DailyAccountingDTO> getAccountings(Instant from, Instant to, UUID shopId) {

        List<Accounting> accounting = accountingRepository.getAccountingsByDateBetweenAndShop_Id(from, to, shopId);
        List<DailyAccountingDTO> dtos = new ArrayList<>();
        accounting.forEach(acc -> {
            dtos.add(DailyAccountingDTO.builder()
                            .dailyExpense(acc.getDailyExpense())
                            .dailyIncome(acc.getDailyIncome())
                            .dailyProfit(acc.getDailyProfit())
                            .date(acc.getDate())
                            .listOfExpenses(acc.getExpensings().stream()
                                    .map(expensing -> new ExpenseDTO(
                                            expensing.getAmount(),
                                            expensing.getExpensingConcept(),
                                            expensing.getDescription())
                                    ).toList())
                            .listOfIncomes(acc.getIncomings().stream()
                                    .map(income -> new IncomeDTO(
                                            income.getAmount(),
                                            income.getIncomingConcept(),
                                            income.getDescription())
                                    ).toList())
                        .build());
        });

        return dtos;
    }
}
