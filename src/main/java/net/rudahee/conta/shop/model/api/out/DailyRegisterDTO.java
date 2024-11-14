package net.rudahee.conta.shop.model.api.out;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Getter
@Setter
public class DailyRegisterDTO {
    private UUID id;
    private Double sell;
    private Double buy;
    private Double profit;
    private Double profitPercent;
    private LocalDate day;

    public DailyRegisterDTO() {}

    private DailyRegisterDTO(UUID id, Double sell, Double buy, Double profit, Double profitPercent, LocalDate day) {
        this.id = id;
        this.sell = sell;
        this.buy = buy;
        this.profit = profit;
        this.profitPercent = profitPercent;
        this.day = day;
    }
}
