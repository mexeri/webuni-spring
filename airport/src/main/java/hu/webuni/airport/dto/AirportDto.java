package hu.webuni.airport.dto;

import javax.validation.constraints.Size;

public class AirportDto 
{
	private long lid;
	
	@Size(min = 3, max = 20)
	private String sName;
	private String sIata;
	
	public AirportDto()
	{
	}
	
	public AirportDto(long lid, String sName, String sIata) {
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
