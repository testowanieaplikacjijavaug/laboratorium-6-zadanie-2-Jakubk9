import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.nullValue;
import static org.hamcrest.Matchers.*;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.*;


public class FriendShipsAssertJTest {
    private static Friendships friendships;

    @BeforeEach
    public void setup() {
        friendships = new Friendships();
    }

    @AfterEach
    public void tearDown() {
        friendships = null;
    }

    @Test
    public void makeFriendsTest() {
        Assertions.assertThat(friendships.getClass().isInstance(Friendships.class));
    }

    @Test
    public void makeNullFriends() {
        Assertions.assertThatThrownBy(() -> {friendships.makeFriends(null,null);}).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void givenNullFriendList() {
        Assertions.assertThatThrownBy(() -> {friendships.getFriendsList(null);}).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void givenNullFriends() {
        Assertions.assertThatThrownBy(() -> {friendships.areFriends(null,null);}).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void makeEmptyFriends() {
        Assertions.assertThatThrownBy(() -> {friendships.makeFriends("","");}).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void givenEmptyFriendList() {
        Assertions.assertThatThrownBy(() -> {friendships.getFriendsList("");}).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void givenEmptyFriends() {
        Assertions.assertThatThrownBy(() -> {friendships.areFriends("","");}).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void givenNotAFriends() {
        friendships.makeFriends("Mariusz", "Marcin");
        Assertions.assertThatThrownBy(() -> {friendships.areFriends("Kuba","Mariusz");}).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void givenTwoFriends() {
        friendships.makeFriends("Mariusz", "Marcin");
        Assertions.assertThat(friendships.getFriendsList("Mariusz")).contains("Marcin").hasSize(1);
        Assertions.assertThat(friendships.getFriendsList("Marcin")).contains("Mariusz").hasSize(1);
    }

    @Test
    public void givenFourFriends() {
        friendships.makeFriends("Mariusz", "Marcin");
        friendships.makeFriends("Mariusz", "Kuba");
        friendships.makeFriends("Mariusz", "Pawel");
        friendships.makeFriends("Mariusz", "Darek");
        Assertions.assertThat(friendships.getFriendsList("Mariusz")).contains("Marcin","Kuba", "Pawel","Darek").doesNotContain("Patryk");
    }

    @Test
    public void checkSizeTest() {
        friendships.makeFriends("Mariusz", "Marcin");
        friendships.makeFriends("Mariusz", "Kuba");
        friendships.makeFriends("Mariusz", "Pawel");
        Assertions.assertThat(friendships.getFriendsList("Mariusz")).hasSize(3);
        Assertions.assertThat(friendships.getFriendsList("Kuba")).hasSize(1);
        Assertions.assertThat(friendships.getFriendsList("Pawel")).hasSize(1);
    }

    @Test
    public void theyAreFriendsTest() {
        friendships.makeFriends("Mariusz", "Marcin");
        Assertions.assertThat(friendships.areFriends("Mariusz", "Marcin")).isTrue();
        Assertions.assertThat(friendships.areFriends("Marcin", "Mariusz")).isTrue();
    }

    @Test
    public void theyAreNotFriendsTest(){
        friendships.makeFriends("Pawel", "Marcin");
        friendships.makeFriends("Mariusz", "Kuba");
        Assertions.assertThat(friendships.areFriends("Mariusz", "Marcin")).isFalse();
        Assertions.assertThat(friendships.areFriends("Kuba", "Pawel")).isFalse();
    }
}
