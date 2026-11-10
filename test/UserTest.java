import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    User u;
    Movie m;
    @BeforeEach
    void BeforeEach(){
        u = new User("TestUser", "TestPassword");
        m = new Movie("name", "2000", new String[]{"comedy"}, 8.7 );
        u.addHistory(m);
    }

    @Test
    void deleteFromHistory(){
        u.deleteFromHistory(m);
    }

    @Test
    void deleteFromSaved(){
        u.deleteFromSaved(new Movie("name", "2020", new String[] {"Romance"}, 8.9 ));
    }

    @Test
    void clearHistory(){
        u.clearHistory();
    }

}