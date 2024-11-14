package net.rudahee.conta.shop.service;


import net.rudahee.conta.accounting.model.db.entities.Accounting;
import net.rudahee.conta.accounting.model.db.repository.AccountingRepository;
import net.rudahee.conta.shop.model.api.out.DailyRegisterDTO;
import net.rudahee.conta.shop.model.api.out.GlobalFinancialStatisticsDTO;
import net.rudahee.conta.shop.model.api.out.ShopFinancialStatisticsDTO;
import net.rudahee.conta.shop.model.db.Repository.ShopRepository;
import net.rudahee.conta.shop.model.db.entity.Shop;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
public class FinancialStatisticsService {

    private final ShopRepository shopRepository;
    private final AccountingRepository accountingRepository;

    public FinancialStatisticsService(ShopRepository shopRepository,
                                      AccountingRepository accountingRepository) {
        this.shopRepository = shopRepository;
        this.accountingRepository = accountingRepository;
    }


    public GlobalFinancialStatisticsDTO createGlobalFinancialStatistics(Instant startDate, Instant endDate) {
        List<Shop> shops = shopRepository.findAll();


        List<ShopFinancialStatisticsDTO> shopDtos = new ArrayList<>();
        for (Shop shop : shops) {
            List<Accounting> accountings = accountingRepository.getAccountingsBetweenDatesAndShopId(startDate, endDate, shop.getId());

            List<DailyRegisterDTO> dailyDtos = new ArrayList<>();
            for (Accounting accounting : accountings) {

                dailyDtos.add(DailyRegisterDTO.builder()
                                .id(accounting.getId())
                                .profit(accounting.getDailyProfit())
                                .buy(accounting.getDailyExpense())
                                .sell(accounting.getDailyIncome())
                                .day(LocalDate.from(accounting.getDate().atZone(ZoneId.systemDefault())))
                                .build());
            }


            double accumulatedBetweenDatesIncomeByShop = accountingRepository.getSumOfIncomesBetweenDatesByShop(startDate, endDate, shop.getId());
            double accumulatedBetweenDatesExpenseByShop = accountingRepository.getSumOfExpensesBetweenDatesByShop(startDate, endDate, shop.getId());
            double accumulatedBetweenDatesProfitByShop = accountingRepository.getSumOfProfitsBetweenDatesByShop(startDate, endDate, shop.getId());
            double accumulatedTotalPercentageProfitByShop = (accumulatedBetweenDatesIncomeByShop != 0) ? (accumulatedBetweenDatesProfitByShop / accumulatedBetweenDatesIncomeByShop * 100) : 0;

            shopDtos.add(ShopFinancialStatisticsDTO.builder()
                                .id(shop.getId())
                                .accumulatedTotalIncome(shop.getAccumulatedIncome())
                                .accumulatedTotalExpense(shop.getAccumulatedExpense())
                                .accumulatedTotalProfit(shop.getAccumulatedProfit())
                                .accumulatedTotalPercentageProfit((shop.getAccumulatedIncome() != 0) ? (shop.getAccumulatedProfit() /shop.getAccumulatedIncome() * 100) : 0 )
                                .accumulatedStartToEndDateIncome(accumulatedBetweenDatesIncomeByShop)
                                .accumulatedStartToEndDateExpense(accumulatedBetweenDatesExpenseByShop)
                                .accumulatedStartToEndDateProfit(accumulatedBetweenDatesProfitByShop)
                                .accumulatedStartToEndDatePercentageProfit(accumulatedTotalPercentageProfitByShop)
                                .startDate(startDate)
                                .endDate(endDate)
                                .dailyRegisters(dailyDtos)
                                .build());
        }


        double accumulatedTotalIncome = shops.stream().mapToDouble(Shop::getAccumulatedIncome).sum();;
        double accumulatedTotalExpense = shops.stream().mapToDouble(Shop::getAccumulatedExpense).sum();
        double accumulatedTotalProfit = shops.stream().mapToDouble(Shop::getAccumulatedProfit).sum();;
        double accumulatedTotalPercentageProfit = (accumulatedTotalIncome != 0) ? (accumulatedTotalProfit / accumulatedTotalIncome * 100) : 0;
        double accumulatedBetweenDatesIncome = accountingRepository.getSumOfIncomesBetweenDates(startDate, endDate);
        double accumulatedBetweenDatesExpense = accountingRepository.getSumOfExpensesBetweenDates(startDate, endDate);
        double accumulatedBetweenDatesProfit = accountingRepository.getSumOfProfitsBetweenDates(startDate, endDate);
        double accumulateddBetweenDatesPercentageProfit = (accumulatedBetweenDatesIncome != 0) ? (accumulatedBetweenDatesProfit / accumulatedBetweenDatesIncome * 100) : 0;


        return GlobalFinancialStatisticsDTO.builder()
                .accumulatedTotalIncome(accumulatedTotalIncome)
                .accumulatedTotalExpense(accumulatedTotalExpense)
                .accumulatedTotalProfit(accumulatedTotalProfit)
                .accumulatedTotalPercentageProfit(accumulatedTotalPercentageProfit)
                .startDate(startDate)
                .endDate(endDate)
                .accumulatedStartToEndDateExpense(accumulatedBetweenDatesExpense)
                .accumulatedStartToEndDateIncome(accumulatedBetweenDatesIncome)
                .accumulatedStartToEndDateProfit(accumulatedBetweenDatesProfit)
                .accumulatedStartToEndDatePercentageProfit(accumulateddBetweenDatesPercentageProfit)
                .shopsFinancialStatistics(shopDtos)
                .build();
    }

}