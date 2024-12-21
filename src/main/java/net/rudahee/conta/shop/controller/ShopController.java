package net.rudahee.conta.shop.controller;

import net.rudahee.authentication.model.api.TokenDTO;
import net.rudahee.authentication.service.jvm.SessionService;
import net.rudahee.conta.accounting.model.db.entities.Accounting;
import net.rudahee.conta.shop.model.api.in.DailyAccountingDTO;
import net.rudahee.conta.shop.model.api.out.ShopDTO;
import net.rudahee.conta.shop.service.ShopService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/shop")
public class ShopController {

    private final ShopService shopService;
    private final SessionService sessionService;

    public ShopController(ShopService shopService, SessionService sessionService) {
        this.shopService = shopService;
        this.sessionService = sessionService;
    }

    @PostMapping
    public ResponseEntity<Accounting> createIncome(@RequestParam String token, @RequestBody DailyAccountingDTO dailyAccountingDTO) throws NoSuchAlgorithmException {
        if (!sessionService.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Accounting savedAccounting = shopService.saveDailyAccounting(dailyAccountingDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAccounting);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ShopDTO>> getShops(@RequestParam String token) throws NoSuchAlgorithmException {

        if (!sessionService.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(shopService.getShops());
    }


}
