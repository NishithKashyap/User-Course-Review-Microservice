import com.example.demodocker.entity.Courses;
import com.example.demodocker.entity.UserProfile;
import com.example.demodocker.repository.UserRepository;
import com.example.demodocker.request.UserRequest;
import com.example.demodocker.response.UserResponse;
import com.example.demodocker.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;




public class UserServiceTest {
    private UserService userService;
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userRepository);
    }

    @Test
    public void getAllUsersTest(){
        List<UserProfile> users = new ArrayList<>();

        UserProfile x = new UserProfile(1, "Rahul Dravid", "Rahul", "Dravid",
                                    "criketer", "cricket","10",
                                    "professional", "prof",
                                     "captain", "...");
        UserProfile y = new UserProfile(2, "Rahul Draaavid", "Rahul", "Dravid",
                "criketer", "cricket","100",
                "professional", "prof",
                "captain", "...");
        users.add(x); users.add(y);

        Mockito.when(userRepository.findAll()).thenReturn(users);
        Iterable<UserProfile> ans = userService.getAllUser();
        assertNotNull(ans);
//        assertEquals(x, x);
    }

    @Test
    public void getUserByIdTest(){
        UserProfile user = new UserProfile(2, "Rahul Dravid", "Rahul", "Dravid",
                "criketer", "cricket","100",
                "professional", "prof",
                "captain", "...");
        Mockito.when(userRepository.findById(2)).thenReturn(Optional.of(user));
        UserProfile ans = userService.getUserById(2);

        assertThat(ans.getId(), equalTo(2));
        assertThat(ans.getDisplayName(), equalTo("Rahul Dravid"));
        assertThat(ans.getFirstName(), equalTo("Rahul"));
        assertThat(ans.getLastName(), equalTo("Dravid"));
        assertThat(ans.getAboutYourself(), equalTo("criketer"));
        assertThat(ans.getAreasOfInterest(), equalTo("cricket"));
        assertThat(ans.getExperience(), equalTo("professional"));
        assertThat(ans.getUserType(), equalTo("100"));
        assertThat(ans.getExpertise(), equalTo("prof"));
        assertThat(ans.getPicture(), equalTo("..."));
        assertThat(ans.getRole(), equalTo("captain"));
    }

    @Test
    public void addUserTest(){
        UserRequest request = new UserRequest("Rahul Dravid", "Rahul", "Dravid",
                "criketer", "cricket","100",
                "professional", "prof",
                "captain", "...");;
        UserProfile mockedUser = new UserProfile(2, "Rahul Dravid", "Rahul", "Dravid",
                "criketer", "cricket","100",
                "professional", "prof",
                "captain", "...");
        Mockito.when(userRepository.save(any(UserProfile.class))).thenReturn(mockedUser);
        UserResponse result = userService.addUser(request);
        assertThat(result.getId(), equalTo(2));
        }

        @Test
        public void updateUserTest(){
            UserProfile prevUser = new UserProfile(2, "Rahul Dravid", "Rahul", "Dravid",
                    "criketer", "cricket","100",
                    "professional", "prof",
                    "captain", "...");
            UserProfile mockUser = new UserProfile(2, "Rahul Dravid", "Rahul", "Dravid",
                    "criketer", "cricket","100",
                    "professional", "prof",
                    "captain", "...");
            UserProfile newUser = new UserProfile(2, "Rahul Dravid", "Rahul", "Dravid",
                    "criketer", "sports","100",
                    "professional", "prof",
                    "captain", "...");

            Mockito.when(userRepository.findById(2)).thenReturn(Optional.of(mockUser));
            Mockito.when(userRepository.save(any(UserProfile.class))).thenReturn(newUser);

            UserProfile ans = userService.updateUser(newUser,2);
            assertThat(ans.getId(), equalTo(2));
            assertThat(ans.getDisplayName(), equalTo("Rahul Dravid"));
            assertThat(ans.getFirstName(), equalTo("Rahul"));
            assertThat(ans.getLastName(), equalTo("Dravid"));
            assertThat(ans.getAboutYourself(), equalTo("criketer"));
            assertThat(ans.getAreasOfInterest(), equalTo("sports"));
            assertThat(ans.getExperience(), equalTo("professional"));
            assertThat(ans.getUserType(), equalTo("100"));
            assertThat(ans.getExpertise(), equalTo("prof"));
            assertThat(ans.getPicture(), equalTo("..."));
            assertThat(ans.getRole(), equalTo("captain"));
        }

    @Test
    void courseAllocatetoUserTest() {
        UserProfile user = new UserProfile (2, "Rahul Dravid", "Rahul", "Dravid",
                "criketer", "sports","100",
                "professional", "prof",
                "captain", "...");
        List<Courses> courseList = new ArrayList<>();
        Courses course1 = new Courses(1, "java", "authorX", "test", "test", 100.00, 2, "test");
        Courses course2 = new Courses(2, "english", "authorY", "test", "test", 100.00, 2, "test");
        courseList.add(course1);
        courseList.add(course2);
        Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user));
        user.getAllocatedCourse().addAll(courseList);
        Mockito.when(userRepository.save(user)).thenReturn(user);

        UserProfile ans = userService.assignCourseToUser(courseList,1);
        assertNotNull(ans.getAllocatedCourse());
        assertEquals(ans.getAllocatedCourse().get(0),courseList.get(0));
    }

    @Test
    public void addToCartTest(){
        UserProfile user = new UserProfile(2, "Rahul Dravid", "Rahul", "Dravid",
                "criketer", "sports","100",
                "professional", "prof",
                "captain", "...");
        Courses c = new Courses(1, "java", "authorX", "test",
                "test", 100.00, 2, "test");
        Mockito.when(userRepository.findById(2)).thenReturn(Optional.of(user));
        user.getCart().add(c);
        Mockito.when(userRepository.save(user)).thenReturn(user);
        UserProfile result = userService.addToCart(c,2);

        assertNotNull(result.getCart());
//        assertEquals(result.getCart().get(0),c);
    }

    @Test
    public void getAllCoursesTest(){
        UserProfile user = new UserProfile (1, "Rahul Dravid", "Rahul", "Dravid",
                "criketer", "sports","100",
                "professional", "prof",
                "captain", "...");
        Courses c = new Courses(1, "java", "authorX", "test",
                "test", 100.00, 2, "test");
        Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user));
        user.getCart().add(c);
        Mockito.when(userRepository.save(user)).thenReturn(user);
        userService.addToCart(c,1);
        List<Courses> result = userService.getCartDetails(1);
        assertNotNull(result);
        assertEquals(result.get(0),c);
    }

}
