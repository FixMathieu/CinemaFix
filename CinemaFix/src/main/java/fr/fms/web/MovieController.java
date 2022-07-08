package fr.fms.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import fr.fms.dao.CinemaRepository;
import fr.fms.dao.CityRepository;
import fr.fms.dao.MovieCityRepository;
import fr.fms.dao.MovieRepository;
import fr.fms.entities.Cinema;
import fr.fms.entities.City;
import fr.fms.entities.Movie;
import fr.fms.entities.MovieCity;

@Controller
public class MovieController {

	@Autowired
	CityRepository cityRepository;
	@Autowired
	MovieRepository movieRepository;
	@Autowired
	MovieCityRepository movieCityRepository;
	@Autowired
	CinemaRepository cinemaRepository;

	@GetMapping("/movie")
	public String movie(long id, Model model) {
		List<City> cities = cityRepository.findAll();
		model.addAttribute("listCity", cities);
		
		List<Cinema> cinema = cinemaRepository.findAll();
		model.addAttribute("listCinema", cinema);

		City city = cityRepository.findById(id).get();
		model.addAttribute("city", city);
		List<Movie> movies = movieRepository.findAll();
		model.addAttribute("ListMovies", movies);
		
		List<MovieCity> movieCity = movieCityRepository.findAll();
		model.addAttribute("listMovieCity", movieCity);
		return "movie";

	}

}
