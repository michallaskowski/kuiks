package dev.michallaskowski.kuiks.sample.android.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

data class Contributor(
    val login: String,
    val contributions: Int
)

interface GitHubService {
    @GET("/repos/{owner}/{repo}/contributors")
    fun contributors(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): Single<List<Contributor>>
}