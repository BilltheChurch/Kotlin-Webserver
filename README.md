# Kotlin-Webserver
Whenever we use a web application, when we browse to a new page, our browser sends an HTTP request to a server. The server processes the request and returns an HTTP response back to the browser containing the data it needs to render the page. We can consider that at a basic level, the server is a function from HTTP request to HTTP response. We say that the server handles the request, and so such a function is an HTTP handler.
A request can contain many different pieces of information, but one of the most important is the URL. The URL specifies which “page” we are requesting. One server may serve many different pages, with different code for each one, and so the server uses the URL to identify which HTTP handler to invoke, passes the request to it for processing, and then returns the response back to the client. The response has a body containing the main content of the page, and also a status code, for example 200 (OK) or 404 (not found)1.
The request may also include various parameters, for example if you are performing a Google search, then the request will include the text that you typed in to the search box in a parameter, q. These parameters are also passed to the handler function so that it can use them in processing the request (e.g. to find the relevant search results for your query).
In this lab exercise you will build a Kotlin implementation of a simple web framework to process requests following this pattern.
Although what we will do here is a lot simpler than a real web application, the ideas that we explore were inspired by the http4k web framework2, and the paper “Your Server as a Function”3 by Marius Eriksen from Twitter. Have a look into those if you are interested.
