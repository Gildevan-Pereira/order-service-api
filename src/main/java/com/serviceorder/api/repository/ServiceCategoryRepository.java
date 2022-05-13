package com.serviceorder.api.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.serviceorder.api.entity.ServiceCategory;

@Repository
public interface ServiceCategoryRepository extends JpaRepository<ServiceCategory, Long>{

	Optional<ServiceCategory> findByUid(UUID uid); 
	
	Optional<ServiceCategory> findByName(String name);
	
	@Query(nativeQuery = true, value = "SELECT * FROM service_category sc WHERE sc.removed_at IS NULL") //Query Nativa
	List<ServiceCategory> findAll();

}
