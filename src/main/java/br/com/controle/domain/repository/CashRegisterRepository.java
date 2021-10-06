package br.com.controle.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.controle.domain.model.CashRegister;

@Repository
public interface CashRegisterRepository extends JpaRepository<CashRegister, Long> {

}
