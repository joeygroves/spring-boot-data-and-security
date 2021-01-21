package co2103.hw2.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 * Hotel is the entity used for listing out the different hotel names, descriptions, 
 * and dealing with the rooms, staff and the bookings made for the specific hotel.
 * @author jwcg2
 *
 */
@Entity
public class Hotel {
	
	/**
	 * The primary key of the hotel is the name
	 */
	@Id
	private String name;
	
	/**
	 * The description of the corresponding hotel
	 */
	private String description;

	/**
	 * A list of rooms that the hotel has, 
	 * and allows the room to be removed without the hotel itself being removed
	 */
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn
	private List<Room> rooms = new ArrayList<>();

	/**
	 * A list of staff
	 */
	@OneToMany
	@JoinColumn
	private List<Person> staff = new ArrayList<>();
	
	/**
	 * A list of bookings with a bi-directional relationship between "hotel" and "booking"
	 */
	@OneToMany(cascade = CascadeType.ALL , mappedBy="hotel", orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Booking> bookings = new ArrayList<>();
	
	
	public Hotel() {
	}


	public Hotel(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public List<Person> getStaff() {
		return staff;
	}

	public void setStaff(List<Person> staff) {
		this.staff = staff;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

}
