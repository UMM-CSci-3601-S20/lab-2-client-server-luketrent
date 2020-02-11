package umm3601.user;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

/**
 * Tests umm3601.user.Database filterUsersByAge
 * and listUsers with _age_ query parameters
 */
public class FilterUsersByAgeFromDB {

  @Test
  public void filterUsersByAge() throws IOException {
    Database db = new Database("src/main/data/users.json");
    User[] allUsers = db.listUsers(new HashMap<>());

    User[] age27Users = db.filterUsersByAge(allUsers, 27);
    assertEquals("Incorrect number of users with age 27", 3, age27Users.length);

    User[] age33Users = db.filterUsersByAge(allUsers, 33);
    assertEquals("Incorrect number of users with age 33", 1, age33Users.length);
  }

  @Test
  public void listUsersWithAgeFilter() throws IOException {
    Database db = new Database("src/main/data/users.json");
    Map<String, String[]> queryParams = new HashMap<>();

    queryParams.put("age", new String[]{"27"});
    User[] age27Users = db.listUsers(queryParams);
    assertEquals("Incorrect number of users with age 27", 3, age27Users.length);

    queryParams.put("age", new String[]{"33"});
    User[] age33Users = db.listUsers(queryParams);
    assertEquals("Incorrect number of users with age 33", 1, age33Users.length);
  }
}
