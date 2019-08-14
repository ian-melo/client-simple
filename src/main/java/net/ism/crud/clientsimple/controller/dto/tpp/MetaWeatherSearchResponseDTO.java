package net.ism.crud.clientsimple.controller.dto.tpp;

public class MetaWeatherSearchResponseDTO {

	private Integer distance;
	private String title;
	private String locationType;
	private Integer woeid;
	private String lattLong;

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public Integer getWoeid() {
		return woeid;
	}

	public void setWoeid(Integer woeid) {
		this.woeid = woeid;
	}

	public String getLattLong() {
		return lattLong;
	}

	public void setLattLong(String lattLong) {
		this.lattLong = lattLong;
	}

}
