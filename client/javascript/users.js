/**
 * Function to get all the users!
 */
function getAllUsers() {
  console.log("Getting all the users.");

  get("/api/users", function (returned_json) {
    document.getElementById('jsonDump').innerHTML = returned_json;
  });
}

function getAllUsersByAge() {
  console.log("Getting all the users.");

  get("/api/users?age=" + document.getElementById("age").value, function (returned_json) {
    document.getElementById('jsonDump').innerHTML = returned_json;
  });
}
