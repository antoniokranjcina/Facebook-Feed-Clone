package com.social.network.api

import com.social.network.data.SocialNetworkPost
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    companion object {
        const val BASE_URL = "http://c0d087b7d001.ngrok.io"
    }

    @GET("/posts")
    suspend fun searchPosts(
        @Query("_page") page: Int,
        @Query("_limit") perPage: Int
    ): List<SocialNetworkPost>
}