package fr.fms.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import fr.fms.business.IBusinessImpl;
import fr.fms.dao.CinemaRepository;
import fr.fms.dao.CityRepository;
import fr.fms.entities.Cinema;
import fr.fms.entities.City;

@Controller
public class CinemaController {
	@Autowired
	IBusinessImpl businessImpl;
@Autowired
CityRepository cityRepository;
@Autowired
CinemaRepository cinemaRepository;
	@GetMapping("/cinema")
	public String article(Model model) {
		nameAuth(model);
		List<City> cities = cityRepository.findAll();
		model.addAttribute("listCity", cities);
		List<Cinema> cinema = cinemaRepository.findAll();
		model.addAttribute("listCinema", cinema);
		model.addAttribute("city", new City());
		return "cinema";
	}
	public void nameAuth(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); // verifie utilisateur Connecte
		String currentUserCo = auth.getName(); 
		model.addAttribute("auth", currentUserCo);
	}
	@PostMapping("/saveCinema")
	public String saveCinema(@Valid Cinema cinema, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "edit";
		cinemaRepository.save(cinema);
		return "redirect:/cities";
	}
}
