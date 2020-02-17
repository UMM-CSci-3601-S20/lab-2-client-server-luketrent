package umm3601.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import io.javalin.core.validation.Validator;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

import umm3601.Server;

/**
 * Tests the logic of the TodoController
 *
 * @throws IOException
 */
public class TodoControllerSpec {

  private Context ctx = mock(Context.class);

  private TodoController todoController;
  private static TodoDatabase db;

  @BeforeEach
  public void setUp() throws IOException {
    ctx.clearCookieStore();

    db = new TodoDatabase(Server.TODO_DATA_FILE);
    todoController = new TodoController(db);
  }

  //Tests requesting all todos
  @Test
  public void GET_to_request_all_todos() throws IOException {
    // Call the method on the mock controller
    todoController.getTodos(ctx);

    // Confirm that `json` was called with all the todos.
    ArgumentCaptor<Todo[]> argument = ArgumentCaptor.forClass(Todo[].class);
    verify(ctx).json(argument.capture());
    assertEquals(db.size(), argument.getValue().length);
  }

  //Tests requesting all todos with Blanche as owner
  @Test
  public void GET_to_request_owner_Blanche_todos() throws IOException {

    Map<String, List<String>> queryParams = new HashMap<>();
    queryParams.put("owner", Arrays.asList(new String[] { "blanche" }));

    when(ctx.queryParamMap()).thenReturn(queryParams);
    todoController.getTodos(ctx);

    // Confirm that all the todos passed to `json` have blanche as the owner.
    ArgumentCaptor<Todo[]> argument = ArgumentCaptor.forClass(Todo[].class);
    verify(ctx).json(argument.capture());
    for (Todo todo : argument.getValue()) {
      assertEquals("blanche", todo.owner);
    }
  }

  //Tests requesting all todos with category as homework
  @Test
  public void GET_to_request_category_homework_todos() throws IOException {

    Map<String, List<String>> queryParams = new HashMap<>();
    queryParams.put("category", Arrays.asList(new String[] { "homework" }));

    when(ctx.queryParamMap()).thenReturn(queryParams);
    todoController.getTodos(ctx);

    // Confirm that all the users passed to `json` have homework as the category.
    ArgumentCaptor<Todo[]> argument = ArgumentCaptor.forClass(Todo[].class);
    verify(ctx).json(argument.capture());
    for (Todo todo : argument.getValue()) {
      assertEquals("homework", todo.category);
    }
  }

  //Tests requesting all todos with status complete
  @Test
  public void GET_to_request_status_complete_todos() throws IOException {

    Map<String, List<String>> queryParams = new HashMap<>();
    queryParams.put("status", Arrays.asList(new String[] { "complete" }));

    when(ctx.queryParamMap()).thenReturn(queryParams);
    todoController.getTodos(ctx);

    // Confirm that all the todos passed to `json` have true as the status.
    ArgumentCaptor<Todo[]> argument = ArgumentCaptor.forClass(Todo[].class);
    verify(ctx).json(argument.capture());
    for (Todo todo : argument.getValue()) {
      assertEquals(true, todo.status);
    }
  }

  //Tests requesting all todos with status incomplete
  @Test
  public void GET_to_request_status_incomplete_todos() throws IOException {

    Map<String, List<String>> queryParams = new HashMap<>();
    queryParams.put("status", Arrays.asList(new String[] { "incomplete" }));

    when(ctx.queryParamMap()).thenReturn(queryParams);
    todoController.getTodos(ctx);

    // Confirm that all the todos passed to `json` have true as the status.
    ArgumentCaptor<Todo[]> argument = ArgumentCaptor.forClass(Todo[].class);
    verify(ctx).json(argument.capture());
    for (Todo todo : argument.getValue()) {
      assertEquals(false, todo.status);
    }
  }

  //Tests ordering all todos by category
  @Test
  public void GET_to_request_order_category_todos() throws IOException {
  
    Map<String, List<String>> queryParams = new HashMap<>();
    queryParams.put("orderBy", Arrays.asList(new String[] { "category" }));
  
    when(ctx.queryParamMap()).thenReturn(queryParams);
    todoController.getTodos(ctx);

    // Confirm that all the todos passed to `json` are sorted.
    ArgumentCaptor<Todo[]> argument = ArgumentCaptor.forClass(Todo[].class);
    verify(ctx).json(argument.capture());
    String s = "";
    for (Todo todo : argument.getValue()) {
      assertTrue(todo.category.compareTo(s) >= 0);
      s = todo.category;
    }
  }

  //Tests ordering all todos by owner
  @Test
  public void GET_to_request_order_owner_todos() throws IOException {
  
    Map<String, List<String>> queryParams = new HashMap<>();
    queryParams.put("orderBy", Arrays.asList(new String[] { "owner" }));
  
    when(ctx.queryParamMap()).thenReturn(queryParams);
    todoController.getTodos(ctx);

    // Confirm that all the todos passed to `json` are sorted.
    ArgumentCaptor<Todo[]> argument = ArgumentCaptor.forClass(Todo[].class);
    verify(ctx).json(argument.capture());
    String s = "";
    for (Todo todo : argument.getValue()) {
      assertTrue(todo.owner.compareTo(s) >= 0);
      s = todo.owner;
    }
  }

  //Tests ordering all todos by body
  @Test
  public void GET_to_request_order_body_todos() throws IOException {
  
    Map<String, List<String>> queryParams = new HashMap<>();
    queryParams.put("orderBy", Arrays.asList(new String[] { "body" }));
  
    when(ctx.queryParamMap()).thenReturn(queryParams);
    todoController.getTodos(ctx);

    // Confirm that all the todos passed to `json` are sorted.
    ArgumentCaptor<Todo[]> argument = ArgumentCaptor.forClass(Todo[].class);
    verify(ctx).json(argument.capture());
    String s = "";
    for (Todo todo : argument.getValue()) {
      assertTrue(todo.body.compareTo(s) >= 0);
      s = todo.body;
    }
  }

  //Tests ordering all todos by status
  @Test
  public void GET_to_request_order_status_todos() throws IOException {
  
    Map<String, List<String>> queryParams = new HashMap<>();
    queryParams.put("orderBy", Arrays.asList(new String[] { "status" }));
  
    when(ctx.queryParamMap()).thenReturn(queryParams);
    todoController.getTodos(ctx);

    // Confirm that all the todos passed to `json` are sorted.
    ArgumentCaptor<Todo[]> argument = ArgumentCaptor.forClass(Todo[].class);
    verify(ctx).json(argument.capture());
    String s = "";
    for (Todo todo : argument.getValue()) {
      assertTrue(Boolean.toString(todo.status).compareTo(s) >= 0);
      s = Boolean.toString(todo.status);
    }
  }

  //Tests filtering todos by limit
  @Test
  public void GET_to_request_limit_todos() throws IOException {
  
    Map<String, List<String>> queryParams = new HashMap<>();
    queryParams.put("limit", Arrays.asList(new String[] { "5" }));
  
    when(ctx.queryParamMap()).thenReturn(queryParams);
    todoController.getTodos(ctx);

    // Confirm that all the todos passed to `json` are within the limit.
    ArgumentCaptor<Todo[]> argument = ArgumentCaptor.forClass(Todo[].class);
    verify(ctx).json(argument.capture());
    int x = 0;
    for (Todo todo : argument.getValue()) {
      x++;
    }
    assertEquals(x, 5);
  }

  //Tests all todos that contain sunt
  @Test
  public void GET_to_request_body_sunt_todos() throws IOException {
  
    Map<String, List<String>> queryParams = new HashMap<>();
    queryParams.put("contains", Arrays.asList(new String[] { "sunt" }));
  
    when(ctx.queryParamMap()).thenReturn(queryParams);
    todoController.getTodos(ctx);

    // Confirm that all the todos passed to `json` are sorted.
    ArgumentCaptor<Todo[]> argument = ArgumentCaptor.forClass(Todo[].class);
    verify(ctx).json(argument.capture());
    for (Todo todo : argument.getValue()) {
      assertFalse(todo.body.toLowerCase().indexOf("sunt") == -1);
    }
  }
  //Tests todos that have blanche as owner and category as homework
  @Test
  public void GET_to_request_owner_blanche_category_homework_todos() throws IOException {

    Map<String, List<String>> queryParams = new HashMap<>();
    queryParams.put("owner", Arrays.asList(new String[] { "blanche" }));

    queryParams.put("category", Arrays.asList(new String[] { "homework" }));

    when(ctx.queryParamMap()).thenReturn(queryParams);
    todoController.getTodos(ctx);

    // Confirm that all the todos passed to `json` have Blanche as owner
    // and homework as category.
    ArgumentCaptor<Todo[]> argument = ArgumentCaptor.forClass(Todo[].class);
    verify(ctx).json(argument.capture());
    for (Todo todo : argument.getValue()) {
      assertEquals("blanche", todo.owner);
      assertEquals("homework", todo.category);
      }
    }
    
  //Tests finding single todo with id
  @Test
  public void GET_to_request_todo_with_existent_id() throws IOException {
    when(ctx.pathParam("id", String.class)).thenReturn(new Validator<String>("58895985a22c04e761776d54", ""));
    todoController.getTodo(ctx);
    verify(ctx).status(201);
  }

  //Tests finding single todo without id
  @Test
  public void GET_to_request_user_with_nonexistent_id() throws IOException {
    when(ctx.pathParam("id", String.class)).thenReturn(new Validator<String>("nonexistent", ""));
    Assertions.assertThrows(NotFoundResponse.class, () -> {
      todoController.getTodo(ctx);
    });
  }
}