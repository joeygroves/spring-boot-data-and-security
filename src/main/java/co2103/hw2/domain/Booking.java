package co2103.hw2.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Booking is an entity which deals with the bookings of the hotel.
 * It adds bookings to the hotel entity as it has a bi-directional relationship with Hotel and deals with the list of guests.
 * @author jwcg2
 *
 */
@Entity
public class Booking {
	
	/**
	 * The primary key of Booking is an integer which is automatically generated
	 */
	@Id
	@GeneratedValue
	int id;
	
	/**
	 * This is for the start date of the guest's stay
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date start = Calendar.getInstance().getTime();
	
	/**
	 * This is for the end date of the guest's stay
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date end = Calendar.getInstance().getTime();;

	
	/**
	 * This is the list of guests which is associated with the booking
	 */
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinColumn
	private List<Person> guests = new ArrayList<>();

	
	/**
	 * This uses the entity hotel, so that when the booking is updated so should the hotel.
	 */
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Hotel hotel;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Person> getGuests() {
		return guests;
	}

	public void setGuests(List<Person> guests) {
		this.guests = guests;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}
	
}
