package school.demo.service;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.MockitoAnnotations;

public class BaseServiceTest {
    @BeforeAll
    public static void setup() {
        MockitoAnnotations.initMocks(BaseServiceTest.class);
    }
}
