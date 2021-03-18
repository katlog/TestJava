package name.katlog.java8;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Created by fw on 2019/7/2
 */
public class TestOptional {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class User{
        private String email;
        private String phone;
    }

    @Test(expected = NoSuchElementException.class)
    public void whenCreateEmptyOptional_thenNull() {
        Optional<User> emptyOpt = Optional.empty();
        emptyOpt.get();
    }

    @Test(expected = NullPointerException.class)
    public void whenCreateOfEmptyOptional_thenNullPointerException() {
        Optional<User> opt = Optional.of(null);
    }

    @Test
    public void whenCreateOfEmptyOptional_success() {
        Optional<User> opt = Optional.ofNullable(null);
    }


    @Test
    public void whenCreateOfNullableOptional_thenOk() {
        String name = "John";
        Optional<String> opt = Optional.ofNullable(name);

        assertEquals("John", opt.get());
    }

    @Test
    public void whenCheckIfPresent_thenOk() {
        User user = new User("john@gmail.com", "1234");
        Optional<User> opt = Optional.ofNullable(user);
        assertTrue(opt.isPresent());

        assertEquals(user.getEmail(), opt.get().getEmail());
    }

    @Test
    public void whenEmptyValue_thenReturnDefault() {
        User user = null;
        User user2 = new User("anna@gmail.com", "1234");
        User result = Optional.ofNullable(user).orElse(user2);

        assertEquals(user2.getEmail(), result.getEmail());
    }

    @Test
    public void whenValueNotNull_thenIgnoreDefault() {
        User user = new User("john@gmail.com","1234");
        User user2 = new User("anna@gmail.com", "1234");
        User result = Optional.ofNullable(user).orElse(user2);

        assertEquals("john@gmail.com", result.getEmail());
    }

    @Test
    public void givenEmptyValue_whenCompare_thenOk() {
        User user = null;
        User result = Optional.ofNullable(user).orElse(createNewUser());
        User result2 = Optional.ofNullable(user).orElseGet(() -> createNewUser());
    }

    private User createNewUser() {
        System.out.println("Creating New User");
        return new User("extra@gmail.com", "1234");
    }

    /** orElse() 方法仍然创建了 User 对象。与之相反，orElseGet() 方法不创建 User 对象 */
    @Test
    public void givenPresentValue_whenCompare_thenOk() {
        User user = new User("john@gmail.com", "1234");
        User result = Optional.ofNullable(user).orElse(createNewUser());
        User result2 = Optional.ofNullable(user).orElseGet(() -> createNewUser());
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenThrowException_thenOk() {
        User result = (User) Optional.ofNullable(null)
                .orElseThrow( () -> new IllegalArgumentException());
    }

    @Test
    public void whenMap_thenOk() {
        User user = new User("anna@gmail.com", "1234");
        String email = Optional.ofNullable(user)
                .map(u -> u.getEmail()).orElse("default@gmail.com");

        assertEquals(email, user.getEmail());
    }

    /** filter() 接受一个 Predicate 参数` */
    @Test
    public void whenFilter_thenOk() {
        User user = new User("anna@gmail.com", "1234");
        Optional<User> result = Optional.ofNullable(user)
                .filter(u -> u.getEmail() != null && u.getEmail().contains("@"));

        assertTrue(result.isPresent());
    }

    @Test
    public void flatMap(){
    }

}
