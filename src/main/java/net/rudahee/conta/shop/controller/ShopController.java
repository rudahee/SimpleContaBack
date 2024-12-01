package net.rudahee.conta.shop.controller;

import net.rudahee.conta.accounting.model.db.entities.Accounting;
import net.rudahee.conta.shop.model.api.in.DailyAccountingDTO;
import net.rudahee.conta.shop.service.ShopService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shop")
public class ShopController {

    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @PostMapping
    public ResponseEntity<Accounting> createIncome(@RequestBody DailyAccountingDTO dailyAccountingDTO) {
        Accounting savedAccounting = shopService.saveDailyAccounting(dailyAccountingDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAccounting);
    }

    @GetMapping
    public ResponseEntity<?> getShop() {




        return null;
    }
}
