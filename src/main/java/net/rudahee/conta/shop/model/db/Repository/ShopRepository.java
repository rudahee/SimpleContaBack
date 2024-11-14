package net.rudahee.conta.shop.model.db.Repository;

import net.rudahee.conta.shop.model.db.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ShopRepository extends JpaRepository<Shop, UUID> {


}
