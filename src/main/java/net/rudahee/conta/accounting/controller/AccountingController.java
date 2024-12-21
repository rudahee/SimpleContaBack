package net.rudahee.conta.accounting.controller;

import net.rudahee.authentication.service.jvm.SessionService;
import net.rudahee.conta.accounting.model.api.DailyAccountingDTO;
import net.rudahee.conta.accounting.services.AccountingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/accounting")
public class AccountingController {

    private final AccountingService accountingService;
    private final SessionService sessionService;

    public AccountingController(AccountingService accountingService, SessionService sessionService) {
        this.accountingService = accountingService;
        this.sessionService = sessionService;
    }

    @GetMapping
    public ResponseEntity<List<DailyAccountingDTO>> getAccounting(@RequestParam String token, @RequestParam(defaultValue = "2024-11-00T01:00:00.000000000Z") Instant from, @RequestParam(required = false) Instant to, UUID id) throws NoSuchAlgorithmException {
        if (!sessionService.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (from == null) {
            from = Instant.now();
        }

        return ResponseEntity.ok(accountingService.getAccountings(from, to, id));
    }
}
