package bbsapi.domain.model;

import java.io.Serializable;
import javax.persistence.*;


@Entity
public class Genru implements Serializable{

	@Id
	private Long id;
	
	private String name;
	
	// Constructor	
	public Genru() {}

	public Genru(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
	
	
	
	
	
	
