package com.ahmedalaa.recipes

import com.ahmedalaa.recipes.utils.Utils
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer

fun MockWebServer.enqueueRequest(fileName: String, code: Int) {
    val responseBody = Utils.readFileResponse(fileName)
    val mockResponse = MockResponse()
    mockResponse.setResponseCode(code)
    mockResponse.setBody(responseBody)
    this.enqueue(mockResponse)
}
