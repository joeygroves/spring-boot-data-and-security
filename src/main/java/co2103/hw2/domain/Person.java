package co2103.hw2.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * This is an entity which deals with roles and authorisation for the login form, as well as the log in details itself.
 * @author jwcg2
 *
 */
@Entity
public class Person {
	
	/**
	 * The primary key of Person is a String called username
	 */
	@Id
	private String username;
	
	/**
	 * This is the full name of the person
	 */
	private String fullName;
	
	/**
	 * The password that the person will use to log in
	 */
	private String password;
	
	/**
	 * The roles associated with the person i.e Staff, Manager or Guest
	 */
	private UserKind kind;

	public Person() {
		super();
	}

	public Person(String fullName, String username, String password, UserKind kind) {
		this.fullName = fullName;
		this.kind = kind;
		this.username = username;
		this.password = password;
	}

	public UserKind getKind() {
		return kind;
	}

	public void setKind(UserKind kind) {
		this.kind = kind;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public String toString() {	
		return fullName;
	}
}
