package com.mck.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mck.domain.Province;


@Repository
public interface ProvinceRepository extends JpaRepository<Province, Integer> {
	
}