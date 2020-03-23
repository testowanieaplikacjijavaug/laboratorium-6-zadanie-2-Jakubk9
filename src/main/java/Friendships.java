import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Friendships {
    public Map<String, List<String>> friendships = new HashMap<>();

    //Dodanie przyjaciół - wykorzystuje funkcje addFriend!
    public void makeFriends(String person1, String person2) {
        if(person1 == null || person2 == null)
            throw new IllegalArgumentException();
        if(person1.length() == 0 || person2.length() == 0)
            throw new IllegalArgumentException();
        addFriend(person1, person2);
        addFriend(person2, person1);
    }

    //Pobranie listy przyjaciol
    public List<String> getFriendsList(String person) {
        if(person == null)
            throw new IllegalArgumentException();
        if(person.length() == 0)
            throw new IllegalArgumentException();
        return friendships.get(person);
    }

    //Sprawdzenie czy przyjaciele
    public boolean areFriends(String person1, String person2) {
        if(person1 == null || person2 == null)
            throw new IllegalArgumentException();
        if(person1.length() == 0 || person2.length() == 0)
            throw new IllegalArgumentException();
        if(!friendships.containsKey(person1))
            throw new IllegalArgumentException();
        List<String> friends = friendships.get(person1);
        if(friends.contains(person2))
            return true;
        return false;
    }
    //Dodanie do listy przyjaciol do danej osoby
    private void addFriend(String person, String friend) {
        if(friendships.containsKey(person)) {
            List<String> friends = friendships.get(person);
            friends.add(friend);
            friendships.put(person, friends);
        } else {
            List<String> list = new ArrayList<>();
            list.add(friend);
            friendships.put(person, list);
        }
    }
}