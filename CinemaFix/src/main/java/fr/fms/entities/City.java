package fr.fms.entities;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class City {

	public City(String address, String name, Cinema cinema, String image) {
		this.address=address;
		this.name=name;
		this.cinema=cinema;
		this.image=image;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	String address;
	String name;
	@ManyToOne
	private Cinema cinema;
	private String image;

}
