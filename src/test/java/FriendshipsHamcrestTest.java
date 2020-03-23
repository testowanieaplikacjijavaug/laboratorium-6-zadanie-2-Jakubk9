import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.nullValue;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class FriendshipsHamcrestTest {
    private static Friendships friendships;

    @BeforeAll
    public static void setup() {
        friendships = new Friendships();
    }

    @AfterAll
    public static void tearDown() {
        friendships = null;
    }

    @Test
    public void makeFriendsTest() {
        assertThat(friendships.getClass(), is(Friendships.class));
    }

    @Test
    public void makeNullFriends() {
        assertThrows(IllegalArgumentException.class, () -> friendships.makeFriends(null,null));
    }

    @Test
    public void givenEmptyFriendList() {
        assertThrows(IllegalArgumentException.class, () -> friendships.getFriendsList(null));
    }

    @Test
    public void givenNullFriends() {
        assertThrows(IllegalArgumentException.class, () -> friendships.areFriends(null, null));
    }

    @Test
    public void givenTwoFriends() {
        friendships.makeFriends("Mariusz", "Marcin");
        assertThat(friendships.getFriendsList("Mariusz"), contains("Marcin"));
        assertThat(friendships.getFriendsList("Marcin"), contains("Mariusz"));
    }

    @Test
    public void givenFourFriends() {
        friendships.makeFriends("Mariusz", "Marcin");
        friendships.makeFriends("Mariusz", "Kuba");
        friendships.makeFriends("Mariusz", "Pawel");
        assertThat(friendships.getFriendsList("Mariusz"), contains("Marcin", "Kuba", "Pawel"));
    }

    @Test
    public void checkSizeTest() {
        friendships.makeFriends("Mariusz", "Marcin");
        friendships.makeFriends("Mariusz", "Kuba");
        friendships.makeFriends("Mariusz", "Pawel");
        assertThat(friendships.getFriendsList("Mariusz"), hasSize(3));
        assertThat(friendships.getFriendsList("Kuba"), hasSize(1));
        assertThat(friendships.getFriendsList("Pawel"), hasSize(1));
    }

    @Test
    public void theyAreFriendsTest() {
        friendships.makeFriends("Mariusz", "Marcin");
        assertThat(friendships.areFriends("Mariusz", "Marcin"), is(true));
        assertThat(friendships.areFriends("Marcin", "Mariusz"), is(true));
    }

    @Test
    public void theyAreNotFriendsTest(){
        friendships.makeFriends("Pawel", "Marcin");
        friendships.makeFriends("Mariusz", "Kuba");
        assertThat(friendships.areFriends("Mariusz", "Marcin"), is(false));
        assertThat(friendships.areFriends("Kuba", "Pawel"), is(false));
    }
}
