package com.example.demo;

import com.example.demo.Model.User;
import com.example.demo.Repo.UserRepository;
import com.example.demo.Service.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;
    @Captor
    private ArgumentCaptor<User> userCaptor;

    @BeforeAll
    static void initAll() {
        System.out.println("BeforeAll: Runs once before all tests");
    }

    @BeforeEach
    void init() {
        System.out.println("BeforeEach: Runs before each test");
    }

    @AfterEach
    void tearDown() {
        System.out.println("AfterEach: Runs after each test");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("AfterAll: Runs once after all tests");
    }

    @Test
    void testCreateUser_withCaptorAndVerify() {
        String name = "Alice";
        String email = "a@a.com";
        User savedUser = new User(1L,name,email);

        when(userRepository.save(any(User.class))).thenReturn(savedUser);
        //action
        User result = userService.createUser(name,email);

        //assert using captor
        verify(userRepository).save(userCaptor.capture());
        User captureduser = userCaptor.getValue();
        assertEquals(name,captureduser.getName());
        assertEquals(email,captureduser.getEmail());

        //more
        assertNotNull(result);
        assertEquals(1L,result.getId());
        assertEquals(name, result.getName());
        assertEquals(email, result.getEmail());

    }
    @Test
    void testGetUserById_notFound() {
        when(userRepository.findById(999L)).thenReturn(Optional.empty());

        Optional<User> result = userService.getUserById(999L);

        assertFalse(result.isPresent());
        verify(userRepository).findById(999L);
    }

    @Test
    void testGetAllUsers_assertIterableAndSize() {
        User user1 = new User(1L , "User1", "user1@example.com");
        User user2 = new User(2L , "u2" , "u2@a.com");

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1,user2));
        List<User> users =userService.getAllUsers();
        assertAll("users",
                ()->assertEquals(2,users.size()),
                ()->assertTrue(users.contains(user1)),
                ()->assertTrue(users.contains(user2))

                );
        verify(userRepository,times(1)).findAll();
    }

    @Test
    void testDeleteUser_verifyInteraction() {
        User user =new User(3L,"ToDELETE","del@example.com");
        userService.deleteUser(user);
        verify(userRepository).delete(user);
        verifyNoMoreInteractions(userRepository);
    }
    @Test
    void testAssertThrowsOnNull() {
        assertThrows(NullPointerException.class, () -> {
            String str = null;
            str.length(); // NPE
        });
    }
    // Timeout example
    @Test
    void testAssertTimeoutExample() {
        assertTimeout(Duration.ofMillis(300), () -> {
            Thread.sleep(100);
        });
    }
    @Test
    void testFailsIfUserNameIsEmpty() {
        User user = new User(1L, "", "valid@mail.com");

        assertThrows(IllegalArgumentException.class, () -> {
            userService.validateUser(user);
        });
    }
    // Grouped assertions
    @Test
    void testGroupedAssertions() {
        User u = new User(99L, "Foo", "foo@mail.com");

        assertAll("User Properties",
                () -> assertEquals(99L, u.getId()),
                () -> assertTrue(u.getName().startsWith("F")),
                () -> assertNotNull(u.getEmail())
        );
    }

}