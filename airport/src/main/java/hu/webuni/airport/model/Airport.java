package hu.webuni.airport.model;

import javax.validation.constraints.Size;

public class Airport 
{
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
