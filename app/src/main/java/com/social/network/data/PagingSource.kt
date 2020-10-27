package com.social.network.data

import android.util.Log
import androidx.paging.PagingSource
import com.social.network.api.Api
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class PagingSource<T, U>(private val api: Api) : PagingSource<Int, SocialNetworkPost>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SocialNetworkPost> {
        val position = params.key ?: STARTING_PAGE_INDEX

        return try {
            val response = api.searchPosts(position, params.loadSize)
            Log.d("PagingSource", "load: $response")
            LoadResult.Page(
                data = response,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (response.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}