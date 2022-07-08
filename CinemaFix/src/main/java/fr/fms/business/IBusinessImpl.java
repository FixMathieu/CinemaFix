package fr.fms.business;

import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import fr.fms.security.SecurityConfig;
import fr.fms.dao.CinemaRepository;
import fr.fms.dao.CityRepository;
import fr.fms.dao.MovieCityRepository;
import fr.fms.dao.MovieRepository;
import fr.fms.dao.RoleRepository;
import fr.fms.dao.SessionRepository;
import fr.fms.dao.UserRepository;
import fr.fms.entities.Cinema;
import fr.fms.entities.City;
import fr.fms.entities.Movie;
import fr.fms.entities.MovieCity;
import fr.fms.entities.Role;
import fr.fms.entities.Session;
import fr.fms.entities.User;

@Service
public class IBusinessImpl implements IBusiness {
	public HashMap<Long, Integer> reservation;

	public User userCurrent;
	@Autowired
	CinemaRepository cinemaRepository;

	@Autowired
	CityRepository cityRepository;

	@Autowired
	MovieRepository movieRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	SessionRepository sessionRepository;

	@Autowired
	UserRepository userRepository;
	@Autowired
	MovieCityRepository movieCityRepository;

	@Autowired
	SecurityConfig securityConfig;

	public IBusinessImpl() {
		this.reservation = new HashMap<Long, Integer>();
		this.userCurrent = null;
	}

	public void setUserCurrent(User user) {
		userCurrent = user;
	}

	public User getUserCurrent() {
		return userCurrent;
	}

	@Override
	@PostConstruct
	public List<City> getAllCities() {
		return cityRepository.findAll();
	}

	@Override
	public void createCity(String address, String name, Cinema cinema, String image) {
		cityRepository.save(new City(address, name, cinema, image));
	}

	@Override
	@Transactional
	public void deleteCityById(Long id) {

		cityRepository.deleteById(id);
	}

	@Override
	public void updateCity(long id, String address, String name, String cineCountry, String image) {
		cityRepository.save(new City(id, address, name, cinemaRepository.findByCountry(cineCountry), image));

	}

	@Override
	public List<Cinema> getAllCinemas() {
		// TODO Auto-generated method stub
		return cinemaRepository.findAll();
	}

	@Override
	public void createCity(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public Page<City> getArticlesPages(Pageable pageable) throws Exception {
		return cityRepository.findAll(pageable);
	}

	public void generateValues() {

		cityRepository.deleteAll();
		roleRepository.deleteAll();
		userRepository.deleteAll();
		movieRepository.deleteAll();
		sessionRepository.deleteAll();
		cinemaRepository.deleteAll();
		Cinema toulouse = cinemaRepository.save(new Cinema(null, "Toulouse"));
		Cinema bordeaux = cinemaRepository.save(new Cinema(null, "Bordeaux"));
		Cinema paris = cinemaRepository.save(new Cinema(null, "Paris"));
		Cinema lyon = cinemaRepository.save(new Cinema(null, "Lyon"));
		Cinema marseille = cinemaRepository.save(new Cinema(null, "Marseille"));

		City toulouseGare = cityRepository.save(new City(null, "rue de la joie", "asterix", toulouse, "toulouse.jpg"));
		City toulouseCentre = cityRepository
				.save(new City(null, "route du centre", "obelix", toulouse, "toulouse.jpg"));
		City toulouseNord = cityRepository
				.save(new City(null, "chemin du Nord", "panoramix", toulouse, "toulouse.jpg"));
		City toulouseSud = cityRepository
				.save(new City(null, "Boulevard du Sud", "abraracourcix", toulouse, "toulouse.jpg"));
		City toulouseEst = cityRepository.save(new City(null, "impasse du Est", "idefix", toulouse, "toulouse.jpg"));

		City bordeauxGare = cityRepository
				.save(new City(null, "rue Sainte Catherine", "asterix", bordeaux, "bordeaux.jpg"));
		City bordeauxCentre = cityRepository
				.save(new City(null, "route du centre", "obelix", bordeaux, "bordeaux.jpg"));
		City bordeauxNord = cityRepository
				.save(new City(null, "chemin du Nord", "panoramix", bordeaux, "bordeaux.jpg"));
		City bordeauxSud = cityRepository
				.save(new City(null, "Boulevard du Sud", "abraracourcix", bordeaux, "bordeaux.jpg"));
		City bordeauxEst = cityRepository.save(new City(null, "impasse du Est", "idefix", bordeaux, "bordeaux.jpg"));

		City parisGare = cityRepository.save(new City(null, "rue de la Paix", "asterix", paris, "paris.jpg"));
		City parisCentre = cityRepository.save(new City(null, "route du centre", "obelix", paris, "paris.jpg"));
		City parisNord = cityRepository.save(new City(null, "chemin du Nord", "panoramix", paris, "paris.jpg"));
		City parisSud = cityRepository.save(new City(null, "Boulevard du Sud", "abraracourcix", paris, "paris.jpg"));
		City parisEst = cityRepository.save(new City(null, "impasse du Est", "idefix", paris, "paris.jpg"));

		City lyonGare = cityRepository.save(new City(null, "gare de Lyon", "asterix", lyon, "lyon.jpg"));
		City lyonCentre = cityRepository.save(new City(null, "route du centre", "obelix", lyon, "lyon.jpg"));
		City lyonNord = cityRepository.save(new City(null, "chemin du Nord", "panoramix", lyon, "lyon.jpg"));
		City lyonSud = cityRepository.save(new City(null, "Boulevard du Sud", "abraracourcix", lyon, "lyon.jpg"));
		City lyonEst = cityRepository.save(new City(null, "impasse du Est", "idefix", lyon, "lyon.jpg"));

		City marseilleGare = cityRepository
				.save(new City(null, "gare de Marseille", "asterix", marseille, "marseille.jpg"));
		City marseilleCentre = cityRepository
				.save(new City(null, "route du centre", "obelix", marseille, "marseille.jpg"));
		City marseilleNord = cityRepository
				.save(new City(null, "chemin du Nord", "panoramix", marseille, "marseille.jpg"));
		City marseilleSud = cityRepository
				.save(new City(null, "Boulevard du Sud", "abraracourcix", marseille, "marseille.jpg"));
		City marseilleEst = cityRepository.save(new City(null, "impasse du Est", "idefix", marseille, "marseille.jpg"));

		Movie film1 = movieRepository.save(new Movie(null, "StarWars", "starwars.jpg"));
		Movie film2 = movieRepository.save(new Movie(null, "Harry potter", "harrypotter.jpg"));
		Movie film3 = movieRepository.save(new Movie(null, "Jurassic World", "jurassicworld.jpg"));
		Movie film4 = movieRepository.save(new Movie(null, "Terminator", "terminator.jpg"));
		Movie film5 = movieRepository.save(new Movie(null, "Alien", "alien.jpg"));
		Movie film6 = movieRepository.save(new Movie(null, "Thor", "thor.jpg"));
		Movie film7 = movieRepository.save(new Movie(null, "Iron Man", "ironman.jpg"));
		Movie film8 = movieRepository.save(new Movie(null, "Hulk", "hulk.jpg"));
		Movie film9 = movieRepository.save(new Movie(null, "Le seigneur des anneaux", "lsda.jpg"));
		Movie film10 = movieRepository.save(new Movie(null, "Gladiator", "gladiator.jpg"));
		Movie film11 = movieRepository.save(new Movie(null, "Top Gun", "topgun.jpg"));

		Session h11 = sessionRepository.save(new Session(null, 11));
		Session h13 = sessionRepository.save(new Session(null, 13));
		Session h15 = sessionRepository.save(new Session(null, 15));
		Session h17 = sessionRepository.save(new Session(null, 17));
		Session h20 = sessionRepository.save(new Session(null, 20));
		Session h22 = sessionRepository.save(new Session(null, 22));

		userRepository.save(new User(null, "Mathieu", securityConfig.encodePassword("fms2022"), "ADMIN", true, null));
		userRepository.save(new User(null, "Mathieu", securityConfig.encodePassword("fms2022"), "USER", true, null));
		userRepository.save(new User(null, "Tristan", securityConfig.encodePassword("fms2022"), "USER", true, null));
		userRepository.save(new User(null, "Martial", securityConfig.encodePassword("fms2022"), "USER", true, null));
		userRepository.save(new User(null, "Eric", securityConfig.encodePassword("fms2022"), "USER", true, null));

		roleRepository.save(new Role(null, "USER"));
		roleRepository.save(new Role(null, "ADMIN"));
		
		movieCityRepository.save(new MovieCity(null,film1,toulouseCentre));
		movieCityRepository.save(new MovieCity(null,film2,toulouseCentre));
		movieCityRepository.save(new MovieCity(null,film3,toulouseCentre));
		movieCityRepository.save(new MovieCity(null,film4,toulouseCentre));
		movieCityRepository.save(new MovieCity(null,film11,toulouseCentre));
		
		movieCityRepository.save(new MovieCity(null,film1,toulouseGare));
		movieCityRepository.save(new MovieCity(null,film10,toulouseGare));
		movieCityRepository.save(new MovieCity(null,film9,toulouseGare));
		movieCityRepository.save(new MovieCity(null,film3,toulouseGare));
		movieCityRepository.save(new MovieCity(null,film5,toulouseGare));
		
		movieCityRepository.save(new MovieCity(null,film1,toulouseNord));
		movieCityRepository.save(new MovieCity(null,film7,toulouseNord));
		movieCityRepository.save(new MovieCity(null,film8,toulouseNord));
		movieCityRepository.save(new MovieCity(null,film9,toulouseNord));
		movieCityRepository.save(new MovieCity(null,film2,toulouseNord));
		
		movieCityRepository.save(new MovieCity(null,film1,toulouseSud));
		movieCityRepository.save(new MovieCity(null,film2,toulouseSud));
		movieCityRepository.save(new MovieCity(null,film8,toulouseSud));
		movieCityRepository.save(new MovieCity(null,film9,toulouseSud));
		movieCityRepository.save(new MovieCity(null,film10,toulouseSud));
		
		movieCityRepository.save(new MovieCity(null,film1,toulouseEst));
		movieCityRepository.save(new MovieCity(null,film8,toulouseEst));
		movieCityRepository.save(new MovieCity(null,film4,toulouseEst));
		movieCityRepository.save(new MovieCity(null,film6,toulouseEst));
		movieCityRepository.save(new MovieCity(null,film10,toulouseEst));
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCityById(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void createCinema(String country) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCinemaById(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateCinema(long id, String country) {
		// TODO Auto-generated method stub

	}

	public Object great() {
		
		return "Hello World";
	}

}
