package net.rudahee.conta.shop.controller;


import net.rudahee.conta.shop.model.api.out.GlobalFinancialStatisticsDTO;
import net.rudahee.conta.shop.service.FinancialStatisticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {

    private final FinancialStatisticsService financialStatisticsService;

    public StatisticsController(FinancialStatisticsService financialStatisticsService) {
        this.financialStatisticsService = financialStatisticsService;
    }

    @GetMapping
    public ResponseEntity<?> createIncome() {
        GlobalFinancialStatisticsDTO financialStatisticsDTO = financialStatisticsService.createGlobalFinancialStatistics(Instant.now().minus(10, ChronoUnit.DAYS), Instant.now().plus(10, ChronoUnit.DAYS));

        return ResponseEntity.ok(financialStatisticsDTO);
    }
}
