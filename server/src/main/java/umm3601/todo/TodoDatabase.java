package umm3601.todo;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class TodoDatabase {

  private Todo[] allTodos;

  public TodoDatabase(String todoDataFile) throws IOException {
    Gson gson = new Gson();
    InputStreamReader reader = new InputStreamReader(getClass().getResourceAsStream(todoDataFile));
    allTodos = gson.fromJson(reader, Todo[].class);
  }

  public int size() {
    return allTodos.length;
  }

  public Todo getTodo(String id) {
    return Arrays.stream(allTodos).filter(x -> x._id.equals(id)).findFirst().orElse(null);
  }

  public Todo[] listTodos(Map<String, List<String>> queryParams) {
    Todo[] filteredTodos = allTodos;

    // Filter status if defined
    if (queryParams.containsKey("status")) {
      Boolean targetStatus;
      System.out.println(queryParams.get("status").get(0));
      if (queryParams.get("status").get(0).equals("complete"))
        targetStatus = true;
      else
        targetStatus = false;
      filteredTodos = filterTodosByStatus(filteredTodos, targetStatus);
    }
    // Filter body if defined
    if (queryParams.containsKey("contains")) {
      String targetBody = queryParams.get("contains").get(0);
      filteredTodos = filterTodosByBody(filteredTodos, targetBody);
    }
    // Filter owner if defined
    if (queryParams.containsKey("owner")) {
      String targetOwner = queryParams.get("owner").get(0);
      filteredTodos = filterTodosByOwner(filteredTodos, targetOwner);
    }
    // Filter category if defined
    if (queryParams.containsKey("category")) {
      String targetCategory = queryParams.get("category").get(0);
      filteredTodos = filterTodosByCategory(filteredTodos, targetCategory);
    }
    // Filters number of todos if defined
    if (queryParams.containsKey("limit")) {
      int targetLimit = Integer.parseInt(queryParams.get("limit").get(0));
      filteredTodos = filterTodosByLimit(filteredTodos, targetLimit);
    }
    // Filters by order
    if (queryParams.containsKey("orderBy")) {
      String targetOrder = queryParams.get("orderBy").get(0);
      filteredTodos = filterTodosByOrder(filteredTodos, targetOrder);
    }
    return filteredTodos;
  }

  public Todo[] filterTodosByStatus(Todo[] todos, Boolean targetStatus) {
    return Arrays.stream(todos).filter(x -> x.status == targetStatus).toArray(Todo[]::new);
  }

  public Todo[] filterTodosByBody(Todo[] todos, String targetBody) {
    return Arrays.stream(todos).filter(x -> x.body.equals(targetBody)).toArray(Todo[]::new);
  }

  public Todo[] filterTodosByOwner(Todo[] todos, String targetOwner) {
    return Arrays.stream(todos).filter(x -> x.owner.equals(targetOwner)).toArray(Todo[]::new);
  }

  public Todo[] filterTodosByCategory(Todo[] todos, String targetCategory) {
    return Arrays.stream(todos).filter(x -> x.category.equals(targetCategory)).toArray(Todo[]::new);
  }

  public Todo[] filterTodosByLimit(Todo[] todos, int targetLimit) {
    return Arrays.copyOfRange(Arrays.stream(todos).toArray(Todo[]::new), 0, targetLimit);
  }

  public Todo[] filterTodosByOrder(Todo[] todos, String targetOrder) {
    if(targetOrder.equals("owner")){
      return Arrays.stream(todos).sorted((x1, x2) -> x1.owner.compareTo(x2.owner)).toArray(Todo[]::new);
    }
    else if(targetOrder.equals("category")){
      return Arrays.stream(todos).sorted((x1, x2) -> x1.category.compareTo(x2.category)).toArray(Todo[]::new);
    }
    else if(targetOrder.equals("body")){
      return Arrays.stream(todos).sorted((x1, x2) -> x1.body.compareTo(x2.body)).toArray(Todo[]::new);
    }
    else{
      return Arrays.stream(todos).sorted((x1, x2) -> String.valueOf(x1.status).compareTo(String.valueOf(x2.status))).toArray(Todo[]::new);
    }
  }
}