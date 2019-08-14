package net.ism.crud.clientsimple.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ism.crud.clientsimple.model.Estatistica;

public interface EstatisticaRepository extends JpaRepository<Estatistica, Long> {

	public Estatistica findByCliente_Id(Long clienteId);

}
