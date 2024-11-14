package net.rudahee.conta.incoming.model.db.repository;

import net.rudahee.conta.incoming.model.db.entity.Incoming;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IncomingRepository extends JpaRepository<Incoming, UUID> {


}
