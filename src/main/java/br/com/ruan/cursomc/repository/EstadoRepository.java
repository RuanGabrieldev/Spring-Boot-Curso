package br.com.ruan.cursomc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ruan.cursomc.model.EstadoModel;

@Repository
public interface EstadoRepository extends JpaRepository<EstadoModel, Integer>{

}
