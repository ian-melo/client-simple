package net.ism.crud.clientsimple.controller.dto.tpp;

public class IpVigilanteResponseDataDTO {
	private String ipv4;
	private String continentName;
	private String countryName;
	private String subdivision1Name;
	private String subdivision2Name;
	private String cityName;
	private String latitude;
	private String longitude;

	public String getIpv4() {
		return ipv4;
	}

	public void setIpv4(String ipv4) {
		this.ipv4 = ipv4;
	}

	public String getContinentName() {
		return continentName;
	}

	public void setContinentName(String continentName) {
		this.continentName = continentName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getSubdivision1Name() {
		return subdivision1Name;
	}

	public void setSubdivision1Name(String subdivision1Name) {
		this.subdivision1Name = subdivision1Name;
	}

	public String getSubdivision2Name() {
		return subdivision2Name;
	}

	public void setSubdivision2Name(String subdivision2Name) {
		this.subdivision2Name = subdivision2Name;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
}
