package app.lystproject.mobile.cat_search.remote.utils

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.net.HttpURLConnection

class RequestDispatcher : Dispatcher() {

    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.path) {
            "$REQUEST_PATH?q=$SEARCH_QUERY" -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(getJson(SEARCH_RESPONSE_PATH))
            }
            "$REQUEST_PATH?q=$NO_MATCH_SEARCH_QUERY" -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(
                        getJson(NO_MATCH_RESPONSE_PATH)
                    )
            }
            NO_MATCH_CAT_ID -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_NOT_FOUND)
                    .setBody(getJson(NOT_FOUND_RESPONSE_PATH))
            }
            "$IMAGE_URL$REFERENCE_IMAGE_ID" -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(getJson(IMAGE_RESPONSE_PATH))
            }
            CAT_ID -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(getJson(IMAGE_RESPONSE_PATH))
            }
            else -> throw IllegalArgumentException("Unknown Request Path ${request.path}")
        }
    }
}
