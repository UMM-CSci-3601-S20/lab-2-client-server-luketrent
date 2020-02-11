/**
 * Utility function to make generating http requests easier.
 * Sends a GET request to the URL described by 'aUrl' with
 * our 'aCallback' function to be executed when the server
 * sends a response.
 *
 * Based on: http://stackoverflow.com/a/22076667
 */
function get(aUrl, aCallback) {
  var anHttpRequest = new XMLHttpRequest();

  // Set a callback to be called when the ready state of our request changes.
  anHttpRequest.onreadystatechange = function () {
    /**
     * Only call our 'aCallback' function if the ready state is 'DONE' and
     * the request status is 200 ('OK')
     *
     * See https://httpstatuses.com/ for HTTP status codes
     * See https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest/readyState
     *  for XMLHttpRequest ready state documentation.
     *
     */
    if (anHttpRequest.readyState === 4 && anHttpRequest.status === 200)
      aCallback(anHttpRequest.responseText);
  };

  anHttpRequest.open("GET", aUrl, true);
  anHttpRequest.send(null);
}
