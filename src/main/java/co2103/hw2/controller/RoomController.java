package co2103.hw2.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co2103.hw2.domain.Hotel;
import co2103.hw2.domain.Room;
import co2103.hw2.repository.HotelRepository;

@Controller
@RequestMapping("/r/")
public class RoomController {

	@Autowired
	private HotelRepository hotelrepo;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(new RoomValidator());
	}

	@GetMapping("/rooms")
	public String showHotels(@RequestParam String hotel, Model model) {
		
		/*
		Hotel h = new Hotel();
		
		
		
		if(hotel.equals(hotelrepo.findByName(hotel))) {
			model.addAttribute("rooms", h.getRooms());
			model.addAttribute("hotel", hotel);
		}
		*/
		
		//List<Hotel> results = (List<Hotel>) hotelrepo.findAll();
	
		
		
		
		for (Hotel h : hotelrepo.findAll()) {
			if (hotel.equals(h.getName())) {
				model.addAttribute("rooms", h.getRooms());
				model.addAttribute("hotel", hotel);
				break;
			}
		}
		
		
		return "rooms/list";
	}

	@RequestMapping("/newRoom")
	public String newHotel(@RequestParam String hotel, Model model) {
		model.addAttribute("room", new Room());
		model.addAttribute("hotel", hotel);
		return "rooms/form";
	}

	@PostMapping("/addRoom")
	public String newHotel(@RequestParam String hotel, @Valid @ModelAttribute Room room, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			model.addAttribute("hotel", hotel);
			return "rooms/form";
		}

		
		for (Hotel h : hotelrepo.findAll()) {
			if (hotel.equals(h.getName())) {
				h.getRooms().add(room);
				hotelrepo.save(h);
				break;
			}	
		}
		
		return "redirect:/h/hotels";
	}
}
