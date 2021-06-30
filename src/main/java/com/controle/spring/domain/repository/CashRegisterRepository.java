package com.controle.spring.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.controle.spring.domain.model.CashRegister;

@Repository
public interface CashRegisterRepository extends JpaRepository<CashRegister, Long>{

}
