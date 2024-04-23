package com.mgtvapi.api.service

import com.mgtvapi.api.model.MainFeedResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.forms.submitForm
import io.ktor.client.request.get
import io.ktor.http.parameters

const val baseUrl: String = "https://massengeschmack.tv"


class MGTVApiRemoteService(
    private val client: HttpClient
) {
    suspend fun login(email: String, password: String): Boolean {
        val data = client.submitForm("$baseUrl/index_login.php", formParameters = parameters {
            append("email", email)
            append("password", password)
        })
        // Believe it or not:
        // If you're credentials are wrong you're still getting status code 200
        // except of the set-cookie header. That's why I have to check the header
        return data.headers.contains("set-cookie")
    }

    suspend fun getMainFeed(offset: Int, count: Int): MainFeedResponse {
        val data = client.get("$baseUrl/api/v2/feed/main") {
            url {
                parameters.append("offset", "$offset")
                parameters.append("count", "$count")
            }
        }
        return data.body<MainFeedResponse>()
    }
}