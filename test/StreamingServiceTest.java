import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StreamingServiceTest {
    StreamingService s;

    @BeforeEach
    void beforeEach() {
        s = new StreamingService("Test Service");
    }


    @Test
    void registerUser() {
        s.createUser("TestUser", "TestPassword");
    }

    @Test
    void checkCredentials() {
        assertTrue(s.checkCredentials("TestUser", "TestPassword"));

    }
}