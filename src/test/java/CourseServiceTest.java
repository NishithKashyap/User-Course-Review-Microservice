import com.example.demodocker.entity.Courses;
import com.example.demodocker.entity.Reviews;
import com.example.demodocker.repository.CourseRepository;
import com.example.demodocker.request.CourseRequest;
import com.example.demodocker.response.CourseResponse;
import com.example.demodocker.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;

public class CourseServiceTest {

    private CourseService courseService;
    @Mock
    private CourseRepository courseRepository;

    List<Courses> courses = new ArrayList<>();

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        courseService = new CourseService(courseRepository);
    }

    @Test
    public void getAllCoursesTest(){
        Courses x = new Courses(1, "java", "authorX", "test", "test", 100.00, 2, "test");
        Courses y = new Courses(2, "java", "authorX", "test", "test", 100.00, 2, "test");
        courses.add(x);
        courses.add(y);

        Mockito.when(courseRepository.findAll()).thenReturn(courses);
        Iterable<Courses> ans = courseService.getAllCourse();
        assertNotNull(ans);
//        assertEquals(x, x);
    }

    @Test
    void getCourseByIdTest() {

        Courses course = new Courses(1, "java", "blah", "test", "test", 100.00, 10, "test");
        Mockito.when(courseRepository.findById(1)).thenReturn(Optional.of(course));

        Courses ans = courseService.getCourseById(1);

        assertThat(ans.getCourseId(), equalTo(1));
        assertThat(ans.getName(), equalTo("java"));
        assertThat(ans.getAuthor(), equalTo("blah"));
        assertThat(ans.getPrice(), equalTo(100.00));
        assertThat(ans.getDomain(), equalTo("test"));
        assertThat(ans.getContent(), equalTo("test"));
        assertThat(ans.getDescription(), equalTo("test"));
        assertThat(ans.getDuration(), equalTo(10));
    }

    @Test
    public void addUserTest(){
        CourseRequest request = new CourseRequest("java", "blah", "test", "test", 100.00, 10, "test");;
        Courses mockedUser = new Courses(2, "java", "blah", "test", "test", 100.00, 10, "test");

        Mockito.when(courseRepository.save(any(Courses.class))).thenReturn(mockedUser);

        CourseResponse ans = courseService.addCourse(request);
        assertThat(ans.getId(), equalTo(2));
    }

    @Test
    public void addReviewTest(){
        Courses c = new Courses(2, "java", "blah", "test", "test", 100.00, 10, "test");
        Mockito.when(courseRepository.findById(2)).thenReturn(Optional.of(c));
        Reviews review = new Reviews("good", 4);
        Courses ans = courseService.addReview(review, 2);
        assertNotNull(ans.getCourseFeedback() );
    }

    @Test
    public void sortDescendingTest(){
        Courses c1 = new Courses(1, "java", "blah", "test", "test", 100.00, 10, "test");
        Courses c2 = new Courses(2, "python", "blah", "test", "test", 100.00, 10, "test");
        courses.add(c1);
        courses.add(c2);
        Mockito.when(courseRepository.findAll()).thenReturn(courses);
        Iterable<Courses> result = courseService.sortCourseInDesc();
        assertNotNull(result);
    }

    @Test
    public void sortAscendingTest(){
        Courses c1 = new Courses(1, "java", "blah", "test", "test", 100.00, 10, "test");
        Courses c2 = new Courses(2, "python", "blah", "test", "test", 100.00, 10, "test");
        courses.add(c1);
        courses.add(c2);
        Mockito.when(courseRepository.findAll()).thenReturn(courses);
        Iterable<Courses> result = courseService.sortCourseInAsc();
        assertNotNull(result);
    }

    @Test
    public void deleteByIdTest(){
        Courses course = new Courses(1, "java", "blah", "test", "test", 100.00, 10, "test");
        Mockito.when(courseRepository.findById(1)).thenReturn(Optional.of(course));
        String ans = courseService.deleteCourseById(1);
        assertThat(ans, equalTo("Course 1 deleted"));
    }
}
//deleteCourse