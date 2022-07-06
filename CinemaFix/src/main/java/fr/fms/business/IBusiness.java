package fr.fms.business;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fr.fms.entities.Cinema;
import fr.fms.entities.City;
import fr.fms.entities.User;

public interface IBusiness {
public List<City> getAllCities();
public void createCity(String name);


void deleteCityById(Long id);

/**
 * 
 * @param address
 * @param country
 * @param cinema
 * @param image
 */
public void createCity(String address, String name, Cinema cinema,String image);

/**
 * 
 * @param id
 */
public void deleteCityById(long id);
/**
 * 
 * @param id
 * @param address
 * @param country
 * @param cineName
 * @param image
 */
public void updateCity(long id, String address,String name,String cineName,String image);
/**
 * 
 * @param pageable
 * @return
 * @throws Exception
 */
Page<City> getArticlesPages(Pageable pageable) throws Exception;

/**
 * Returns all users in the database
 * @return a List<User> containing all users
 */
public List<User> getAllUsers();

/**
 * 
 * @return
 */
public List<Cinema> getAllCinemas();

/**
 * 
 * @param country
 */
public void createCinema(String country);

/**
 * 
 * @param id
 */
public void deleteCinemaById(long id);

/**
 * 
 * @param id
 * @param name
 */
public void updateCinema(long id, String country);

}
