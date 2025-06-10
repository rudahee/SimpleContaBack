package net.rudahee.conta.accounting.controller;

import net.rudahee.authentication.service.jvm.SessionService;
import net.rudahee.conta.accounting.model.api.DailyAccountingDTO;
import net.rudahee.conta.accounting.services.AccountingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<DailyAccountingDTO>> getAccounting(@RequestParam String token, @RequestParam(defaultValue = "2024-11-00T01:00:00.000000000Z") Instant from, @RequestParam(required = false) Instant to,  @RequestParam UUID id) throws NoSuchAlgorithmException {
        if (!sessionService.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (from == null) {
            from = Instant.now();
        }

        return ResponseEntity.ok(accountingService.getAccountings(from, to, id));
    }


    @GetMapping("/{id}")
    public ResponseEntity<DailyAccountingDTO> getOneAccounting(@RequestParam String token, @PathVariable UUID id) throws NoSuchAlgorithmException {
        if (!sessionService.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(accountingService.getAccountingById(id));
    }




}
