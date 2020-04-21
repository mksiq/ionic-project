package com.mck.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mck.domain.City;


@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
	@Transactional(readOnly=true)
	@Query("SELECT obj FROM City obj WHERE obj.province.id = :provinceId ORDER BY obj.name")
	List<City> findCities(@Param("provinceId") Integer province_id);
}