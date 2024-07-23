
import com.hashedin.huSpark.dao.LoginUserDto;
import com.hashedin.huSpark.dao.RegisterUserDto;
import com.hashedin.huSpark.model.Role;
import com.hashedin.huSpark.model.RoleEnum;
import com.hashedin.huSpark.model.User;
import com.hashedin.huSpark.repository.RoleRepository;
import com.hashedin.huSpark.repository.UserRepository;
import com.hashedin.huSpark.service.AuthenticationService;
import com.hashedin.huSpark.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserServiceTest {

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
    public void testGetUserByIdNotFound() {

        LoginUserDto loginUserDto = new LoginUserDto();
        loginUserDto.setEmail("Vikram.patel@gmail.com");
        loginUserDto.setPassword("india");

        User user = new User()
                .setEmail("Vikram.patel@gmail.com")
                .setPassword("india");

        Mockito.when(userRepository.findByEmail("Vikram.patel@gmail.com")).thenReturn(Optional.of(user));

        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        assertEquals(user, authenticatedUser);
    }
}

