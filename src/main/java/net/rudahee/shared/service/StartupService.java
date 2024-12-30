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
        System.out.println("Initialized!");
    }
}
