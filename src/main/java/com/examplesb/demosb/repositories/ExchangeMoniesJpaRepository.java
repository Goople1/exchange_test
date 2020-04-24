package com.examplesb.demosb.repositories;

import com.examplesb.demosb.model.entity.ExchangeMoniesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ExchangeMoniesJpaRepository extends JpaRepository<ExchangeMoniesEntity, Long> {

  @Query("select e from ExchangeMoniesEntity e where (e.fromOne = ?1 and e.toOne = ?2 ) or  " +
      " (e.fromTwo = ?1 and e.toTwo = ?2)")
  ExchangeMoniesEntity findByFromTo(String from, String to);
}
