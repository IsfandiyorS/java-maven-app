import com.example.Application;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AppTest {


    @Test
    public void testApplication(){
        Application app = new Application();
        String status = app.getStatus();

        assertEquals(status, "OK");
    }

}
