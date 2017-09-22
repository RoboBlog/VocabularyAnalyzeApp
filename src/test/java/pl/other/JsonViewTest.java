package pl.other;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import pl.user.User;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class JsonViewTest {

    @Test
    public void whenUsePublicView() throws JsonProcessingException {
        User user = new User("maciek", "password", "test@gmail.com");//TODO remove redundancy

        ObjectMapper mapper = new ObjectMapper();
        String result = mapper
                .writerWithView(Views.Public.class)
                .writeValueAsString(user);
        assertThat(result, not(containsString("password")));
        assertThat(result, containsString("maciek"));
        assertThat(result, containsString("test@gmail.com"));
    }

    public void whenUseInternalView() throws JsonProcessingException{
        User user = new User("maciek", "password", "test@gmail.com");

        ObjectMapper mapper = new ObjectMapper();
        String result = mapper
                .writerWithView(Views.Internal.class)
                .writeValueAsString(user);

        assertThat(result, containsString("password"));
        assertThat(result, containsString("maciek"));
        assertThat(result, containsString("test@gmail.com"));
    }

}
