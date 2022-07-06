package fr.fms.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.fms.entities.City;

public interface CityRepository extends JpaRepository<City, Long> {
	Page<City> findByNameContains(String name, Pageable pageable);
	public List<City> findByCinemaId(long id);
	public List<City>deleteById(long id);
	public Page<City>findByNameContainsAndCinemaId(String name, Long cinemaId,Pageable pageable );
	Page<City>findByCinemaId(long cinemaId,Pageable pageable);
	List<City>findByNameContains(String string);
	Page<City> findByAddressContains(String address, Pageable pageable);
	
}
