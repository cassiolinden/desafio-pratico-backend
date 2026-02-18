import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        LoginTest.class,
        ValidateTest.class,
        LogoutTest.class,
        RoomTest.class
})
public class TestSuite extends BaseTest {
}
