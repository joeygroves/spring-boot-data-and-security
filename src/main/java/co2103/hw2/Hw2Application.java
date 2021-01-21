
package co2103.hw2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import co2103.hw2.domain.Booking;
import co2103.hw2.domain.Hotel;
import co2103.hw2.domain.Person;
import co2103.hw2.domain.UserKind;
import co2103.hw2.repository.BookingRepository;
import co2103.hw2.repository.HotelRepository;
import co2103.hw2.repository.PersonRepository;

@SpringBootApplication
public class Hw2Application implements ApplicationRunner {

	@Autowired
	private HotelRepository hotelrepo;
	
	@Autowired
	private BookingRepository bookingrepo;
	
	@Autowired
	private PersonRepository personrepo;
	
	@Autowired
	private PasswordEncoder pe;
	
	//public static List<Hotel> hotels = new ArrayList<>();
	//public static List<Booking> bookings = new ArrayList<>();

	public static Person user;

	public static void main(String[] args) {
		SpringApplication.run(Hw2Application.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Hotel h1 = new Hotel("Hilton", "by the lake");
		Hotel h2 = new Hotel("Ibis", "behind the station");
		hotelrepo.save(h1);
		hotelrepo.save(h2);
		
		//hotels.add(h1);
		//hotels.add(h2);

		//user = new Person("Manfred", "manager", "password", UserKind.Guest);
		user = new Person("Manfred", "manager", pe.encode("password"), UserKind.Manager);
		personrepo.save(user);

		//Person p1 = new Person("Steven", "staff1", "password", UserKind.Staff);
		Person p1 = new Person("Steven", "staff1", pe.encode("password"), UserKind.Staff);
		personrepo.save(p1);
		h1.getStaff().add(p1);
		hotelrepo.save(h1);

		//Person p2 = new Person("Stas", "staff2", "password", UserKind.Staff);
		Person p2 = new Person("Stas", "staff2", pe.encode("password"), UserKind.Staff);
		personrepo.save(p2);
		h2.getStaff().add(p2);
		hotelrepo.save(h2);
		
		//Person p3 = new Person("Gavin", "guest", "password", UserKind.Guest);
		Person p3 = new Person("Gavin", "guest", pe.encode("password"), UserKind.Guest);
		personrepo.save(p3);
		Booking b = new Booking();
		bookingrepo.save(b);
		b.setHotel(h1);
		bookingrepo.save(b);
		h1.getBookings().add(b);
		hotelrepo.save(h1);
		b.getGuests().add(p3);
		bookingrepo.save(b);
		
		//bookings.add(b);

	}

}
