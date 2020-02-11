package umm3601.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Tests umm3601.user.Database listUsers
 * with _age_ and _company_ query parameters
 */
public class FilterUsersByCombinedFiltersFromDB {

  @Test
  public void listUsersWithCombinedFilters() throws IOException {
    Database db = new Database("src/main/data/users.json");
    Map<String, String[]> queryParams = new HashMap<>();

    queryParams.put("age", new String[]{"25"});
    User[] age25Users = db.listUsers(queryParams);
    assertEquals(2, age25Users.length, "Incorrect number of users with age 25");

    queryParams.clear();
    queryParams.put("company", new String[]{"OHMNET"});
    User[] ohmnetUsers = db.listUsers(queryParams);
    assertEquals(2, ohmnetUsers.length, "Incorrect number of users with company OHMNET");

    queryParams.clear();
    queryParams.put("age", new String[]{"25"});
    queryParams.put("company", new String[]{"OHMNET"});
    User[] ohmnetAge25Users = db.listUsers(queryParams);
    assertEquals(1, ohmnetAge25Users.length, "Incorrect number of users with company OHMNET and age 25");
  }
}
