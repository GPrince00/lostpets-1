package br.lostpets.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.lostpets.project.model.PetPerdido;
import br.lostpets.project.model.Usuario;

@Repository
public interface PetPerdidoRepository extends JpaRepository<PetPerdido, Integer>{ 

	@Query("from PetPerdido where status = 'P'")
	List<PetPerdido> getAtivos();

	@Query("from PetPerdido where status = 'P' and idAnimal = ?1")
	public PetPerdido getAtivosByIdAnimal(int idAnimal);

	@Query("from PetPerdido where status = 'P' and pathImg is not null")
	public List<PetPerdido> getAtivosNNull();

	@Query("from PetPerdido where usuario = ?1")
	public List<PetPerdido> encontrarTodosByUsuario(Usuario usuario);

}
