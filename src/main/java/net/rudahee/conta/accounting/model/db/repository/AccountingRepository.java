package net.rudahee.conta.accounting.model.db.repository;

import net.rudahee.conta.accounting.model.db.entities.Accounting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Repository
public interface AccountingRepository extends JpaRepository<Accounting, UUID> {


    @Query("SELECT a " +
            "FROM Accounting a " +
            "WHERE (a.date BETWEEN :startDate AND :endDate) " +
            "AND a.shop.id = :shopId")
    List<Accounting> getAccountingsBetweenDatesAndShopId(@Param("startDate") Instant startDate,
                                                         @Param("endDate") Instant endDate,
                                                         @Param("shopId")UUID shopId);

    @Query("SELECT SUM(a.dailyExpense) " +
            "FROM Accounting a " +
            "WHERE (a.date BETWEEN :startDate AND :endDate)")
    Double getSumOfExpensesBetweenDates(@Param("startDate") Instant startDate,
                                        @Param("endDate") Instant endDate);

    @Query("SELECT SUM(a.dailyIncome) " +
            "FROM Accounting a " +
            "WHERE (a.date BETWEEN :startDate AND :endDate)")
    Double getSumOfIncomesBetweenDates(@Param("startDate") Instant startDate,
                                        @Param("endDate") Instant endDate);

    @Query("SELECT SUM(a.dailyProfit) " +
            "FROM Accounting a " +
            "WHERE (a.date BETWEEN :startDate AND :endDate)")
    Double getSumOfProfitsBetweenDates(@Param("startDate") Instant startDate,
                                        @Param("endDate") Instant endDate);

    @Query("SELECT SUM(a.dailyExpense) " +
            "FROM Accounting a " +
            "WHERE (a.date BETWEEN :startDate AND :endDate) "+
            "AND a.shop.id = :shopId")
    Double getSumOfExpensesBetweenDatesByShop(@Param("startDate") Instant startDate,
                                        @Param("endDate") Instant endDate,
                                        @Param("shopId") UUID shopId);

    @Query("SELECT SUM(a.dailyIncome) " +
            "FROM Accounting a " +
            "WHERE (a.date BETWEEN :startDate AND :endDate) "+
            "AND a.shop.id = :shopId")
    Double getSumOfIncomesBetweenDatesByShop(@Param("startDate") Instant startDate,
                                       @Param("endDate") Instant endDate,
                                       @Param("shopId") UUID shopId);

    @Query("SELECT SUM(a.dailyProfit) " +
            "FROM Accounting a " +
            "WHERE (a.date BETWEEN :startDate AND :endDate) "+
            "AND a.shop.id = :shopId")
    Double getSumOfProfitsBetweenDatesByShop(@Param("startDate") Instant startDate,
                                       @Param("endDate") Instant endDate,
                                       @Param("shopId") UUID shopId);


}
