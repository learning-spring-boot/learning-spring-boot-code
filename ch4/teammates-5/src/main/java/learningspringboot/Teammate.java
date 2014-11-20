package learningspringboot;

import java.math.BigInteger;

// tag::slimmed[]
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Teammate {

	@Id
	private BigInteger id;

	private String firstName;
	private String lastName;
	private String position;

	private BigInteger teamId;
// end::slimmed[]

	private Teammate() {
	}

	public Teammate(String firstName, String lastName) {
		this();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public BigInteger getTeamId() {
		return teamId;
	}

	public void setTeamId(BigInteger teamId) {
		this.teamId = teamId;
	}

	@Override
	public String toString() {
		return id + ": " + firstName + " " + lastName + " is playing " + position;
	}
}
