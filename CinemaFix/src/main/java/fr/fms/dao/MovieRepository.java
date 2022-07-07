package fr.fms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import fr.fms.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long>{
//	 public Movie findByName(String name);
	public List<Movie> findById(long id);

	
}
