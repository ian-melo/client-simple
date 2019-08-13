package net.ism.crud.clientsimple.config.validation.dto;

public class FormErrorDTO {
	private String field;
	private String problem;
	
	public FormErrorDTO(String field, String problem) {
		super();
		this.field = field;
		this.problem = problem;
	}

	public String getField() {
		return field;
	}
	public String getProblem() {
		return problem;
	}
}
