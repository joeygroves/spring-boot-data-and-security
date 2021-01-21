package co2103.hw2.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * This is an entity called Room which deals with the room description, category and the maximum number of guests allowed in that room.
 * @author jwcg2
 *
 */
@Entity
public class Room {
	
	/**
	 * The primary key of Room is an automatically generated integer
	 */
	@Id
	@GeneratedValue
	private int id;
	
	/**
	 * The description of the room. I.e: how many beds does it have? etc.
	 */
	private String description;
	
	/**
	 * Category description of the room. Is it a Double Room? King? etc.
	 */
	private String category;
	
	/**
	 * The maximum amount of guests that are allowed to stay in the room.
	 */
	private int maxGuests;
	

	public Room() {
		super();
	}

	public Room(String description, String category, int maxGuests) {
		this.category = category;
		this.description = description;
		this.maxGuests = maxGuests;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getMaxGuests() {
		return maxGuests;
	}

	public void setMaxGuests(int maxGuests) {
		this.maxGuests = maxGuests;
	}

}
