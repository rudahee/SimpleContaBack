package net.rudahee.conta.shop.model.api.out;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Builder
@Setter
@Getter
public class ShopFinancialStatisticsDTO {

    private UUID id;
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
    private List<DailyRegisterDTO> dailyRegisters;

    public ShopFinancialStatisticsDTO() {}

    private ShopFinancialStatisticsDTO(UUID id, Double accumulatedTotalIncome, Double accumulatedTotalExpense,
                                       Double accumulatedTotalProfit, Double accumulatedTotalPercentageProfit,
                                       Instant startDate, Instant endDate,
                                       Double accumulatedStartToEndDateIncome, Double accumulatedStartToEndDateExpense,
                                       Double accumulatedStartToEndDateProfit, Double accumulatedStartToEndDatePercentageProfit,
                                       List<DailyRegisterDTO> dailyRegisters) {
        this.id = id;
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
        this.dailyRegisters = dailyRegisters;
    }
}
