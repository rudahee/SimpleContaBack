package net.rudahee.conta.shop.controller;


import net.rudahee.authentication.model.api.TokenDTO;
import net.rudahee.authentication.service.jvm.SessionService;
import net.rudahee.conta.shop.model.api.out.GlobalFinancialStatisticsDTO;
import net.rudahee.conta.shop.service.FinancialStatisticsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {

    private final FinancialStatisticsService financialStatisticsService;
    private final SessionService sessionService;

    public StatisticsController(FinancialStatisticsService financialStatisticsService, SessionService sessionService) {
        this.financialStatisticsService = financialStatisticsService;
        this.sessionService = sessionService;
    }

    @GetMapping
    public ResponseEntity<GlobalFinancialStatisticsDTO> getFinancialStatistics(@RequestParam String token, @RequestParam(defaultValue = "2024-11-00T01:00:00.000000000Z") Instant from, @RequestParam(required = false) Instant to) throws NoSuchAlgorithmException {
        if (!sessionService.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (from == null) {
            from = Instant.now();
        }

        GlobalFinancialStatisticsDTO financialStatisticsDTO = financialStatisticsService.createGlobalFinancialStatistics(from, to);

        return ResponseEntity.ok(financialStatisticsDTO);
    }
}
