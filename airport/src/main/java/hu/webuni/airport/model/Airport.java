package hu.webuni.airport.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Size;

@Entity
//@NamedQuery(name="Airport.countByIata", query = "SELECT COUNT(a.id) from Airport a WHERE a.sIata = :sIata")
//@NamedQuery(name="Airport.countByIataAndIdNotIn", query = "SELECT COUNT(a.id) from Airport a WHERE a.sIata = :sIata AND a.id != :id")
public class Airport 
{
	@Id
	@GeneratedValue 
	private long id;
	
	@Size(min = 3, max = 20)
	private String name;
	private String iata;
	
	public Airport()
	{
	}
	
	public Airport(long lid, String sName, String sIata) {
		super();
		this.id = lid;
		this.name = sName;
		this.iata = sIata;
	}
	
	
	public long getLid() {
		return id;
	}
	public void setLid(long lid) {
		this.id = lid;
	}
	public String getsName() {
		return name;
	}
	public void setsName(String sName) {
		this.name = sName;
	}
	public String getsIata() {
		return iata;
	}
	public void setsIata(String sIata) {
		this.iata = sIata;
	}
}
