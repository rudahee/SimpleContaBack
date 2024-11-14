package net.rudahee.conta.expensing.model.db.repository;

import net.rudahee.conta.expensing.model.db.entity.Expensing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ExpensingRepository extends JpaRepository<Expensing, UUID> {
}
