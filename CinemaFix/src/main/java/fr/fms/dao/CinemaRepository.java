package fr.fms.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.fms.entities.Cinema;

public interface CinemaRepository extends JpaRepository<Cinema, Long> {
 public List<Cinema> deleteById(long id);
 public Page<Cinema>findByCountryContains(String country,Pageable pageable);
 public Cinema  findByCountry(String country);
// Page<Cinema>findbyCountryContains(String country,Pageable pageable);

}
