package com.mck.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mck.domain.Client;


@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
	
}