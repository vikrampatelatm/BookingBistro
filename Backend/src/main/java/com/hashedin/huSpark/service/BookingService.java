package com.hashedin.huSpark.service;

import com.hashedin.huSpark.congfigration.ResourceNotFoundException;
import com.hashedin.huSpark.dao.BookingDTO;
import com.hashedin.huSpark.dao.BookingResponseDTO;
import com.hashedin.huSpark.model.Booking;
import com.hashedin.huSpark.model.Restaurant;
import com.hashedin.huSpark.model.User;
import com.hashedin.huSpark.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    UserService userService;


    public void cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
        if ("pending".equals(booking.getStatus())) {
            booking.setStatus("cancelled");
            bookingRepository.save(booking);
        } else {
            throw new IllegalStateException("Only pending bookings can be cancelled");
        }
    }



    public List<BookingResponseDTO> getAllBooking() {
        List bookings = new ArrayList<Booking>();
        List DTO = new ArrayList<BookingResponseDTO>();
        for(Booking booking : bookingRepository.findAll()) {
                // System.out.println(booking.getUser().getId()+" "+id);
                BookingResponseDTO dto=new BookingResponseDTO();

                dto.setId(booking.getId());
                dto.setUserName(booking.getUser().getFullName());
                dto.setEmailId(booking.getUser().getEmail());
                dto.setMobileNumber(booking.getUser().getMobile());
                dto.setBookingStatus(booking.getStatus());
                dto.setDate(booking.getBookingDate());
                dto.setTime(booking.getTimeSlot());
                dto.setGuestCount(booking.getNumber_of_Guest().toString());
                dto.setRestaurantName(booking.getRestaurant().getName());
                dto.setRestaurantLocation(booking.getRestaurant().getLocation());

                DTO.add(dto);
                bookings.add(booking);

        }
        //System.out.println(DTO);
        return DTO;
    }

    public List<BookingResponseDTO> getBookingByUserId(Long id) {
        List bookings = new ArrayList<Booking>();
        List DTO = new ArrayList<BookingResponseDTO>();
        for(Booking booking : bookingRepository.findAll()) {
           // System.out.println(booking.getUser().getId()+" "+id);
            if (Objects.equals(booking.getUser().getId(), id)) {
                BookingResponseDTO dto=new BookingResponseDTO();
                dto.setId(booking.getId());
                dto.setUserName(booking.getUser().getFullName());
                dto.setEmailId(booking.getUser().getEmail());
                dto.setMobileNumber(booking.getUser().getMobile());
                dto.setBookingStatus(booking.getStatus());
                dto.setDate(booking.getBookingDate());
                dto.setTime(booking.getTimeSlot());
                dto.setGuestCount(booking.getNumber_of_Guest().toString());
                dto.setRestaurantName(booking.getRestaurant().getName());
                dto.setRestaurantLocation(booking.getRestaurant().getLocation());

                DTO.add(dto);
                bookings.add(booking);
            }
        }
        System.out.println(DTO);
        return DTO;
    }

    public Booking createBooking(BookingDTO bookingDTO){
        Booking booking =new Booking();
        User user = userService.getUserById(bookingDTO.getUserId());
        booking.setUser(user);
        Restaurant restaurant = restaurantService.getRestaurantById(bookingDTO.getRestaurantId());
        booking.setRestaurant(restaurant);
        booking.setNumber_of_Guest(bookingDTO.getNumberOfGuests());
        booking.setBookingDate(bookingDTO.getBookingDate());
       // System.out.println(date);

        booking.setTimeSlot(bookingDTO.getTimeSlot());
        booking.setStatus("pending");

        return bookingRepository.save(booking);
    }
}
