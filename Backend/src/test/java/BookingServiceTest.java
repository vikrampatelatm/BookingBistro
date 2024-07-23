import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.hashedin.huSpark.congfigration.ResourceNotFoundException;
import com.hashedin.huSpark.dao.BookingDTO;
import com.hashedin.huSpark.dao.BookingResponseDTO;
import com.hashedin.huSpark.model.Booking;
import com.hashedin.huSpark.model.Restaurant;
import com.hashedin.huSpark.model.User;
import com.hashedin.huSpark.repository.BookingRepository;
import com.hashedin.huSpark.service.BookingService;
import com.hashedin.huSpark.service.RestaurantService;
import com.hashedin.huSpark.service.UserService;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private RestaurantService restaurantService;

    @Mock
    private UserService userService;

    @InjectMocks
    private BookingService bookingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void cancelBooking_ShouldCancelPendingBooking() {
        Booking booking = new Booking();
        booking.setStatus("pending");

        when(bookingRepository.findById(1L)).thenReturn(Optional.of(booking));

        bookingService.cancelBooking(1L);

        assertEquals("cancelled", booking.getStatus());
        verify(bookingRepository).save(booking);
    }

    @Test
    void cancelBooking_ShouldThrowExceptionForNonPendingBooking() {
        Booking booking = new Booking();
        booking.setStatus("confirmed");

        when(bookingRepository.findById(1L)).thenReturn(Optional.of(booking));

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            bookingService.cancelBooking(1L);
        });

        assertEquals("Only pending bookings can be cancelled", exception.getMessage());
    }



    @Test
    void createBooking_ShouldCreateNewBooking() {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setUserId(1L);
        bookingDTO.setRestaurantId(1L);
        bookingDTO.setNumberOfGuests(4);
        bookingDTO.setBookingDate("2023-07-04");
        bookingDTO.setTimeSlot("12:00 PM");

        User user = new User();
        user.setId(1L);
        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);

        when(userService.getUserById(1L)).thenReturn(user);
        when(restaurantService.getRestaurantById(1L)).thenReturn(restaurant);
        when(bookingRepository.save(any(Booking.class))).thenAnswer(i -> i.getArguments()[0]);

        Booking result = bookingService.createBooking(bookingDTO);

        assertNotNull(result);
        assertEquals(1L, result.getUser().getId());
        assertEquals(1L, result.getRestaurant().getId());
        assertEquals("pending", result.getStatus());
    }
}

