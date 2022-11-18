@file:Suppress("UNUSED_EXPRESSION")

package webserver

// write your web framework code here:

fun scheme(url: String): String =
    url.substringBefore("://")

fun host(url: String): String {
    return url.substringAfter("://").substringBefore("/")
}

fun path(url: String): String {
    return url.substringAfter(host(url)).substringBefore("?")
}

fun queryParams(url: String): List<Pair<String, String>> {
    return if (url.contains("?")) {
        val query = url.substringAfter("?").substringBefore(".")
        val listOfQuery = query.split("&", "=").chunked(2)
        listOfQuery.map { x -> Pair(x[0], x[1]) }
    } else {
        listOf()
    }
}

fun route(request: Request): Response {
    val url = request.url
    return when (path(url)) {
        "/" -> homePageHandler()
        "/say-hello" -> helloHandler(request)
        "/computing" -> computingHandler()
        else -> errorHandler()
    }
}

// http handlers for a particular website...
fun homePageHandler(): Response =
    Response(Status.OK, "This is Imperial.")

fun computingHandler(): Response =
    Response(Status.OK, "This is DoC.")

fun errorHandler(): Response =
    Response(Status.NOT_FOUND)

fun nameHandler(name: String, param: String): String =
    "Hello, $param!"

fun styleHandler(sentence: String, param: String): String {
    return when (param) {
        "shouting" -> sentence.uppercase()
        "whispering" -> sentence.lowercase()
        else -> sentence
    }
}

fun helloHandler(request: Request): Response {
    val queryParam = queryParams(request.url)
    val paramHandler = mapOf(
        Pair("name", ::nameHandler),
        Pair("style", ::styleHandler)
    )
    var hello = "Hello, World!"
    for (param in queryParam) {
        paramHandler[param.first]!!.invoke(hello, param.second).also { hello = it }
    }
    return Response(
        Status.OK,
        hello
    )
}
