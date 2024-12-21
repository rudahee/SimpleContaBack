package net.rudahee.shared.service;

import net.rudahee.conta.accounting.model.db.entities.Accounting;
import net.rudahee.conta.incoming.model.db.repository.IncomingRepository;
import net.rudahee.conta.shop.model.db.Repository.ShopRepository;
import net.rudahee.conta.shop.model.db.entity.Shop;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Component
public class StartupService implements ApplicationListener<ContextRefreshedEvent> {

    private final ShopRepository shopRepository;
    private final IncomingRepository incomingRepository;

    public StartupService(ShopRepository shopRepository, IncomingRepository incomingRepository) {
        this.shopRepository = shopRepository;
        this.incomingRepository = incomingRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (shopRepository.count() != 2) {
            Shop shop = new Shop();
            shop.setName("Santa Aurelia");
            shop.setAccumulatedIncome(0D);
            shop.setAccumulatedExpense(0D);
            shop.setAccumulatedProfit(0D);

            List<Accounting> accountings1 = new ArrayList<>();
            Accounting accounting1 = new Accounting();
            accounting1.setDate(Instant.now());
            accounting1.setShop(shop);
            accounting1.setDailyIncome(0D);
            accounting1.setDailyExpense(0D);
            accounting1.setDailyProfit(0D);
            accounting1.setIncomings(null);
            accounting1.setExpensings(null);
            accountings1.add(accounting1);

            shop.setAccountings(accountings1);
            shopRepository.save(shop);

            Shop shop2 = new Shop();
            shop2.setName("Villegas");
            shop2.setAccumulatedIncome(0D);
            shop2.setAccumulatedExpense(0D);
            shop2.setAccumulatedProfit(0D);

            List<Accounting> accountings2 = new ArrayList<>();
            Accounting accounting2 = new Accounting();
            accounting2.setDate(Instant.now());
            accounting2.setShop(shop);
            accounting2.setDailyIncome(0D);
            accounting2.setDailyExpense(0D);
            accounting2.setDailyProfit(0D);
            accounting2.setIncomings(null);
            accounting2.setExpensings(null);
            accountings2.add(accounting2);

            shop2.setAccountings(accountings2);
            shopRepository.save(shop2);

        }
    }
}
