package net.rudahee.conta.shop.service;

import jakarta.transaction.Transactional;
import net.rudahee.conta.accounting.model.db.entities.Accounting;
import net.rudahee.conta.accounting.model.db.repository.AccountingRepository;
import net.rudahee.conta.expensing.model.api.ExpenseDTO;
import net.rudahee.conta.expensing.model.db.entity.Expensing;
import net.rudahee.conta.expensing.model.db.repository.ExpensingRepository;
import net.rudahee.conta.incoming.model.api.IncomeDTO;
import net.rudahee.conta.incoming.model.db.entity.Incoming;
import net.rudahee.conta.incoming.model.db.repository.IncomingRepository;
import net.rudahee.conta.shop.model.api.in.DailyAccountingDTO;
import net.rudahee.conta.shop.model.db.Repository.ShopRepository;
import net.rudahee.conta.shop.model.db.entity.Shop;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopService {

    private final IncomingRepository incomingRepository;
    private final ExpensingRepository expensingRepository;
    private final AccountingRepository accountingRepository;
    private final ShopRepository shopRepository;

    public ShopService(IncomingRepository incomingRepository,
                       ExpensingRepository expensingRepository,
                       AccountingRepository accountingRepository,
                       ShopRepository shopRepository) {
        this.incomingRepository = incomingRepository;
        this.expensingRepository = expensingRepository;
        this.accountingRepository = accountingRepository;
        this.shopRepository = shopRepository;
    }

    @Transactional
    public Accounting saveDailyAccounting(DailyAccountingDTO dailyAccountingDTO) {

        Shop shop = shopRepository.findById(dailyAccountingDTO.getShopId()).orElseThrow();

        Accounting accounting = new Accounting();


        double dailyIncome = 0.0;
        double dailyExpense = 0.0;

        List<Incoming> incomingsToSave = new ArrayList<>();
        for (IncomeDTO incomeDTO: dailyAccountingDTO.getIncome()) {
            Incoming incoming = new Incoming();
            incoming.setAmount(incomeDTO.getAmount());
            incoming.setIncomingConcept(incomeDTO.getIncomingConcept());
            incoming.setDate(dailyAccountingDTO.getDate());
            incoming.setDescription(incomeDTO.getDescription());
            incoming.setAccounting(accounting);
            incomingsToSave.add(incoming);
            shop.setAccumulatedIncome(shop.getAccumulatedIncome() + incomeDTO.getAmount());
            dailyIncome += incomeDTO.getAmount();

        }

        List<Expensing> expensingsToSave = new ArrayList<>();
        for (ExpenseDTO expensingDTO: dailyAccountingDTO.getExpense()) {
            Expensing expensing = new Expensing();
            expensing.setAmount(expensingDTO.getAmount());
            expensing.setExpensingConcept(expensingDTO.getExpensingConcept());
            expensing.setDate(dailyAccountingDTO.getDate());
            expensing.setDescription(expensingDTO.getDescription());
            expensing.setAccounting(accounting);
            expensingsToSave.add(expensing);
            shop.setAccumulatedExpense(shop.getAccumulatedExpense() + expensingDTO.getAmount());
            dailyExpense += expensingDTO.getAmount();

        }

        accounting.setShop(shop);
        accounting.setDate(dailyAccountingDTO.getDate());
        accounting.setDailyIncome(dailyIncome);
        accounting.setDailyExpense(dailyExpense);
        accounting.setDailyProfit(dailyIncome - dailyExpense);


        shop.setAccumulatedProfit(shop.getAccumulatedIncome() - shop.getAccumulatedExpense());

        Accounting accOut = accountingRepository.save(accounting);
        expensingRepository.saveAll(expensingsToSave);
        incomingRepository.saveAll(incomingsToSave);
        shopRepository.save(shop);

        return accountingRepository.findById(accOut.getId()).orElseThrow();
    }


}
