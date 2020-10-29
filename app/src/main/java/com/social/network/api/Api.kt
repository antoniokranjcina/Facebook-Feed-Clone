package com.social.network.api

import com.social.network.data.SocialNetworkPost
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    companion object {
        const val BASE_URL = "http://06e719b99bd3.ngrok.io"
    }

    @GET("/posts")
    suspend fun searchPostsByName(
        @Query("accountName") query: String,
        @Query("_page") page: Int,
        @Query("_limit") perPage: Int
    ): List<SocialNetworkPost>

    @GET("/posts")
    suspend fun searchPosts(
        @Query("_page") page: Int,
        @Query("_limit") perPage: Int
    ): List<SocialNetworkPost>
}