
import com.hashedin.huSpark.dao.LoginUserDto;
import com.hashedin.huSpark.dao.RegisterUserDto;
import com.hashedin.huSpark.model.Role;
import com.hashedin.huSpark.model.RoleEnum;
import com.hashedin.huSpark.model.User;
import com.hashedin.huSpark.repository.RoleRepository;
import com.hashedin.huSpark.repository.UserRepository;
import com.hashedin.huSpark.service.AuthenticationService;
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

public class AuthenticationServiceTest {

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

    @Test
    public void testSignup() {
        RegisterUserDto registerUserDto = new RegisterUserDto();
        registerUserDto.setFullName("Vikram Patel");
        registerUserDto.setEmail("Vikram.patel@gmail.com");
        registerUserDto.setMobile("6361921473");
        registerUserDto.setPassword("india");

        Role role = new Role();
        role.setName(RoleEnum.CUSTOMER);

        Mockito.when(roleRepository.findByName(RoleEnum.CUSTOMER)).thenReturn(Optional.of(role));

        User expectedUser = new User().setFullName("Vikram Patel")
                .setEmail("Vikram.patel@gmail.com")
                .setMobile("6361921473")
                .setPassword("encodedPassword")
                .setRole(role);

        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(expectedUser);

        User savedUser = authenticationService.signup(registerUserDto);

        assertEquals(expectedUser, savedUser);
    }

    @Test
    public void testAuthenticate() {
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
