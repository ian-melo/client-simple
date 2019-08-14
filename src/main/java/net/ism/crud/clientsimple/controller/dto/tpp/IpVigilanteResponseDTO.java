package net.ism.crud.clientsimple.controller.dto.tpp;

public class IpVigilanteResponseDTO {

	private String status;
	private IpVigilanteResponseDataDTO data;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public IpVigilanteResponseDataDTO getData() {
		return data;
	}

	public void setData(IpVigilanteResponseDataDTO data) {
		this.data = data;
	}
}
