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
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class SessionGroup {
@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long idLong;

@ManyToOne
private Cinema cinema;
@ManyToOne
private City city;
@ManyToOne
private Movie movie;
@ManyToOne 
private Session session;


}
