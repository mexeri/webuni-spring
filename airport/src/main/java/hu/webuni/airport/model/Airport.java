package hu.webuni.airport.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Size;

@Entity
@NamedQuery(name="Airport.countByIata", query = "SELECT COUNT(a.id) from Airport a WHERE a.sIata = :sIata")
public class Airport 
{
	@Id
	@GeneratedValue 
	private long lid;
	
	@Size(min = 3, max = 20)
	private String sName;
	private String sIata;
	
	public Airport()
	{
	}
	
	public Airport(long lid, String sName, String sIata) {
		super();
		this.lid = lid;
		this.sName = sName;
		this.sIata = sIata;
	}
	
	
	public long getLid() {
		return lid;
	}
	public void setLid(long lid) {
		this.lid = lid;
	}
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	public String getsIata() {
		return sIata;
	}
	public void setsIata(String sIata) {
		this.sIata = sIata;
	}
}
