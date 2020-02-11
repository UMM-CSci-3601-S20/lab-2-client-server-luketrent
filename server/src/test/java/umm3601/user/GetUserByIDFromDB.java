package umm3601.user;

import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

/**
 * Tests umm3601.user.Database getUser functionality
 */
public class GetUserByIDFromDB {

  @Test
  public void getStokesClayton() throws IOException {
    Database db = new Database("src/main/data/users.json");
    User user = db.getUser("588935f52787254123f71fed");
    assertEquals("Incorrect name", "Stokes Clayton", user.name);
  }

  @Test
  public void getBoltonMonroe() throws IOException {
    Database db = new Database("src/main/data/users.json");
    User user = db.getUser("588935f5556f992bf8f37c01");
    assertEquals("Incorrect name", "Bolton Monroe", user.name);
  }
}
