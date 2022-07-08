package fr.fms.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import fr.fms.dao.CinemaRepository;
import fr.fms.dao.CityRepository;
import fr.fms.entities.Cinema;
import fr.fms.entities.City;


@Controller
public class CitiesController {
	
	@Autowired
	CityRepository cityRepository;
	@Autowired
	CinemaRepository cinemaRepository;
	@GetMapping("/cities")
	public String cities(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "keyword", defaultValue = "") String kw,
			@RequestParam(name = "cinema", defaultValue = "-1") long cineId,
			@RequestParam(name = "isAdmin", defaultValue = "true") String isAdmin) {
		Page<City> cities;
		if (cineId != -1) {
			cities= cityRepository.findByNameContainsAndCinemaId(kw, cineId, PageRequest.of(page, 5));
		} else {
			cities = cityRepository.findByNameContains(kw, PageRequest.of(page, 5));
		}
		List<Cinema>cinemas=cinemaRepository.findAll();
		nameAuth(model);
		model.addAttribute("listCinema", cinemas);
		model.addAttribute("cinema", cineId);
		model.addAttribute("listCity", cities.getContent());
		model.addAttribute("pages", new int[cities.getTotalPages()]);
		model.addAttribute("currentPage", page);
		model.addAttribute("isAdmin", isAdmin);
		
		return "cities";
	}
	public void nameAuth(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); // verifie utilisateur Connecte
		String currentUserCo = auth.getName(); // recupere son nom
		model.addAttribute("auth", currentUserCo);
	}
	
	@GetMapping("/edit")
	public String edit(Long id, Model model) {
		nameAuth(model);
		List<City> cities = cityRepository.findAll();
		model.addAttribute("listCity", cities);
		List<Cinema> cinema = cinemaRepository.findAll();
		model.addAttribute("listCinema", cinema);
		City city = cityRepository.findById(id).get();
		model.addAttribute("city", city);
		return "edit";
	}
	
	
	@GetMapping("/login")
	public String login(Model model, @RequestParam(name = "error", defaultValue = "false") boolean loginError) {
		if (loginError) {

		}
		nameAuth(model);
		return "login";
	}
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login";
	}

	@GetMapping("/403")
	public String refused() {
		return "403";
	}
	@GetMapping("/404")
	public String notFound() {
		return "404";
	}
	@RequestMapping(value = "/")
	public  String home() {
		return "redirect:/cities";
	}
	@GetMapping("/loginAuth")
	public String loginAuth(Model model) {
		nameAuth(model);
		return "redirect:/cities";
	}
}
