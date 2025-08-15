package com.demo.credits.repository;

import com.demo.credits.entity.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for the Credit entity.
 *
 * @author Jose Gregorio Perez Manosalva
 */
@Repository
public interface CreditRepository extends JpaRepository<Credit, Long> {
}
