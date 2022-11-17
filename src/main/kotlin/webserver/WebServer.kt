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

// http handlers for a particular website...

fun homePageHandler(request: Request): Response = Response(Status.OK, "This is Imperial.")



