package fr.fms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.fms.entities.MovieCity;

public interface MovieCityRepository extends JpaRepository<MovieCity, Long> {

	

	public List<MovieCity> findByCityId(long cityId);



	

	
	
}
