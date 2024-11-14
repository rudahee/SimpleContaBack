package net.rudahee.conta.shop.model.api.out;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

import java.time.Instant;
import java.util.List;

@Builder
@Setter
@Getter
public class GlobalFinancialStatisticsDTO {

    private Double accumulatedTotalIncome;
    private Double accumulatedTotalExpense;
    private Double accumulatedTotalProfit;
    private Double accumulatedTotalPercentageProfit;

    private Instant startDate;
    private Instant endDate;

    private Double accumulatedStartToEndDateIncome;
    private Double accumulatedStartToEndDateExpense;
    private Double accumulatedStartToEndDateProfit;
    private Double accumulatedStartToEndDatePercentageProfit;

    @Singular
    private List<ShopFinancialStatisticsDTO> shopsFinancialStatistics;

    public GlobalFinancialStatisticsDTO() {}

    private GlobalFinancialStatisticsDTO(Double accumulatedTotalIncome, Double accumulatedTotalExpense,
                                         Double accumulatedTotalProfit, Double accumulatedTotalPercentageProfit,
                                         Instant startDate, Instant endDate,
                                         Double accumulatedStartToEndDateIncome, Double accumulatedStartToEndDateExpense,
                                         Double accumulatedStartToEndDateProfit, Double accumulatedStartToEndDatePercentageProfit,
                                         List<ShopFinancialStatisticsDTO> shopsFinancialStatistics) {
        this.accumulatedTotalIncome = accumulatedTotalIncome;
        this.accumulatedTotalExpense = accumulatedTotalExpense;
        this.accumulatedTotalProfit = accumulatedTotalProfit;
        this.accumulatedTotalPercentageProfit = accumulatedTotalPercentageProfit;
        this.startDate = startDate;
        this.endDate = endDate;
        this.accumulatedStartToEndDateIncome = accumulatedStartToEndDateIncome;
        this.accumulatedStartToEndDateExpense = accumulatedStartToEndDateExpense;
        this.accumulatedStartToEndDateProfit = accumulatedStartToEndDateProfit;
        this.accumulatedStartToEndDatePercentageProfit = accumulatedStartToEndDatePercentageProfit;
        this.shopsFinancialStatistics = shopsFinancialStatistics;
    }
}
