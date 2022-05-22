package com.serviceorder.api.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.serviceorder.api.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

	@Query(value = "SELECT c FROM Client c WHERE c.removedAt IS NULL AND c.uid = :uid") //JPQL
	Optional<Client> findByUid(UUID uid);
	
	@Query(value = "SELECT c FROM Client c WHERE c.removedAt IS NULL") //JPQL
	Page<Client> findAllByFilter(Pageable pageable); //Query Method JPA
	
	@Query(nativeQuery = true, value = "SELECT * FROM client c "
			+ "WHERE LOWER(UNACCENT(c.fullname)) LIKE %:keyword% "
			+ "OR  LOWER(UNACCENT(c.identity)) LIKE %:keyword% "
			+ "OR  LOWER(UNACCENT(c.phone)) LIKE %:keyword% "
			+ "AND c.removed_at IS NULL")
	List<Client> findByKeyword(String keyword);
	

}
