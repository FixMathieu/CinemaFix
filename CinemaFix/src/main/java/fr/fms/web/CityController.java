package fr.fms.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import fr.fms.business.IBusinessImpl;
import fr.fms.dao.CinemaRepository;
import fr.fms.dao.CityRepository;
import fr.fms.entities.Cinema;
import fr.fms.entities.City;



@Controller
public class CityController {
	
	@Autowired
	IBusinessImpl businessImpl;
@Autowired
CityRepository cityRepository;
@Autowired
CinemaRepository cinemaRepository;

@PostMapping("/save")
public String save(@Valid City city, BindingResult bindingResult) {
	if (bindingResult.hasErrors())
		return "edit";
	cityRepository.save(city);
	return "redirect:/cities";
}
@GetMapping("/delete")
public String delete(Long id, int page, String keyword) {
	businessImpl.deleteCityById(id);
	return "redirect:/cities?page=" + page + "&keyword=" + keyword;
}

@GetMapping("/city")
public String cinema(Model model) {
	nameAuth(model);
	List<City> cities = cityRepository.findAll();
	model.addAttribute("listCity", cities);
	List<Cinema> cinemas = cinemaRepository.findAll();
	model.addAttribute("listCinema", cinemas);
	model.addAttribute("cinema", new Cinema());
	return "city";
}

public void nameAuth(Model model) {
	Authentication auth = SecurityContextHolder.getContext().getAuthentication(); // verifie utilisateur Connecte
	String currentUserCo = auth.getName(); // recupere son nom
	model.addAttribute("auth", currentUserCo);
}
}
