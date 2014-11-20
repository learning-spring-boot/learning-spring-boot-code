package learningspringboot;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

// tag::slimmed[]
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document()
public class Team {

	@Id
	private BigInteger id;

	private String name;

	@DBRef
	private List<Teammate> members;
// end::slimmed[]

	private Team() {
		members = new ArrayList<>();
	}

	public Team(String name) {
		this();
		this.name = name;
	}

	public BigInteger getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Teammate> getMembers() {
		return members;
	}

	public void setMembers(List<Teammate> members) {
		this.members = members;
	}
}
