package net.ism.crud.clientsimple.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ism.crud.clientsimple.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
