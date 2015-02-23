/**
 * Created by BIGVAL on 21/02/2015.
 */

import org.junit.Test;
import play.mvc.Result;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;

public class ServeurVoeuxTest {
    @Test
    public void testServeurVoeuxRoute() {
        Result result = route(fakeRequest(GET, "/ServeurDeVoeux"));
        assertThat(result).isNotNull();
    }
}
