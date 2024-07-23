
import com.hashedin.huSpark.dao.LoginUserDto;
import com.hashedin.huSpark.dao.RegisterUserDto;
import com.hashedin.huSpark.model.Role;
import com.hashedin.huSpark.model.RoleEnum;
import com.hashedin.huSpark.model.User;
import com.hashedin.huSpark.repository.RoleRepository;
import com.hashedin.huSpark.repository.UserRepository;
import com.hashedin.huSpark.service.AuthenticationService;
import com.hashedin.huSpark.service.RestaurantService;
import com.hashedin.huSpark.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class RestaurantServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private AuthenticationService authenticationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @InjectMocks
    private UserService userService;

    @Test
    public void testGetCapacity() {
        // Arrange
        RestaurantService service = new RestaurantService();
        HashMap<Integer, Integer> typeOfTables = new HashMap<>();
        typeOfTables.put(4, 5);
        typeOfTables.put(2, 3);

        long expectedCapacity = 26;

        long actualCapacity = service.getCapcity(typeOfTables);

        assertEquals(expectedCapacity, actualCapacity);
    }
}
