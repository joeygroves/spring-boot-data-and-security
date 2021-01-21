package co2103.hw2.controller;

import java.security.Principal;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co2103.hw2.domain.Booking;
import co2103.hw2.domain.Hotel;
import co2103.hw2.domain.Person;
import co2103.hw2.repository.BookingRepository;
import co2103.hw2.repository.HotelRepository;
import co2103.hw2.repository.PersonRepository;

/**
 * A Controller which deals with the Booking domain.
 * It redirects HTTP requests to various .jsp files, and allows the authorised user to add, delete and view a list of bookings. 
 * @author jwcg2
 *
 */
@Controller
@RequestMapping("/b/")
public class BookingController {
	
	/**
	 * Autowires the BookingRepository so that it can save bookings to the system
	 */
	@Autowired
	private BookingRepository bookingrepo;
	
	/**
	 * Autowires the HotelRepository so that it can save the changes to the hotel to the system
	 */
	@Autowired
	private HotelRepository hotelrepo;
	
	/**
	 * Autowires the PersonRepository which is used to get the name of the person who booked the hotel 
	 */
	@Autowired
	private PersonRepository personrepo;
	
	
	/**
	 * Shows a list of bookings made
	 * @param model adds the list of bookings to the model
	 * @return the jsp file 'list'
	 */
	@GetMapping("/bookings")
	public String showBookings(Model model) {
		model.addAttribute("bookings", bookingrepo.findAll());
		return "bookings/list";
	}

	/**
	 * Allows the authorised user to add a new booking through a form
	 * @param model add the new booking to the model
	 * @return the jsp file 'form'
	 */
	@RequestMapping("/newBooking")
	public String newBooking(Model model) {
		
		//This line uses Stream map(Function mapper) to map x with the name (P.K) of the hotels from Hw2Application and collect's it in the list?
		//model.addAttribute("hotels", Hw2Application.hotels.stream().map(x -> x.getName()).collect(Collectors.toList()));
		
		//model.addAttribute("hotels", hotelrepo.findAll());
		model.addAttribute("hotels", ((Collection<Hotel>) hotelrepo.findAll()).stream().map(x -> x.getName()).collect(Collectors.toList()));
		//System.out.println("HOTEL");
		model.addAttribute("booking", new Booking());
		return "bookings/form";
	}

	/**
	 * The action of the submit button. 
	 * Which adds the new booking into the system and returns back to the list of bookings made
	 * @param booking  the new booking
	 * @param hotelName  which hotel the booking is being made to
	 * @param principal  used to retrieve secure user details in Spring Security
	 * @return the jsp file 'list'
	 */
	@PostMapping("/addBooking")
	public String addBooking(@ModelAttribute Booking booking, @RequestParam String hotelName, Principal principal) {
		
		//Optional<Hotel> h = hotelrepo.findById(hotelName);
		
		Person p = personrepo.findByUsername(principal.getName());
		
		
		for (Hotel h : hotelrepo.findAll()) {
			//System.out.println(h);
			if (hotelName.equals(h.getName())) {
				bookingrepo.save(booking);
				booking.setHotel(h);
				bookingrepo.save(booking);
				break;
			}
		}
	
		
		//booking.getGuests().add(Hw2Application.user);
		//Hw2Application.bookings.add(booking);	
		booking.getGuests().add(p);
		bookingrepo.save(booking);
		//booking.setId((int) System.currentTimeMillis());
		bookingrepo.save(booking);
		
		
		return "redirect:/b/bookings";
	}
	
	/**
	 * Deletes a booking from the system without deleting the hotel associated with it
	 * @param id  gets the primary key of the booking
	 * @return the jsp file 'list' and shows the list of bookings left.
	 */
	@GetMapping("/deleteBooking")
	public String deleteBooking(@RequestParam int id) {
		
		
		if (bookingrepo.findById(id).isPresent()) {
			bookingrepo.deleteById(id);
		}
		
		
		/*
		for (Booking b : bookingrepo.findAll()) {
			if (b.getId() == id) {
				bookingrepo.delete(b);;
				break;
			}
		}
		*/
		
		
		/*
		for (Booking b : Hw2Application.bookings) {
			if (b.getId() == id) {
				Hw2Application.bookings.remove(b);
				break;
			}
		}
		*/
		
		return "redirect:/b/bookings";
	}
}
