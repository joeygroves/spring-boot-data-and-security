package co2103.hw2.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

//import co2103.hw2.Hw2Application;
import co2103.hw2.domain.Hotel;
import co2103.hw2.repository.HotelRepository;

public class HotelValidator implements Validator {

	private HotelRepository hotelrepo;
	
	public HotelValidator(HotelRepository hotelrepo) {
		this.hotelrepo = hotelrepo;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Hotel.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "", "required");

		Hotel hotel = (Hotel) target;
		
		if(hotelrepo.existsById(hotel.getName())) {
			errors.rejectValue("name", "", "taken");
		}
		
		/*
		if (hotelrepo.findById(hotel.getName()).isPresent()) {
			errors.rejectValue("name", "", "taken");
		}
		*/
		
		
		/*
		for(Hotel h : hotelrepo.findAll()) {
			if (hotel.getName().equals(h.getName())) {
				errors.rejectValue("name", "", "taken");
				break;
			}
		}
		*/
	}

}
