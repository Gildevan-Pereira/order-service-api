package com.serviceorder.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.serviceorder.api.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {


}