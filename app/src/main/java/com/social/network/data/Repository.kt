package com.social.network.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.social.network.api.Api
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(private val api: Api) {
    fun getPostsResults() = Pager(
        config = PagingConfig(
            pageSize = 20,
            maxSize = 100,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { PagingSource(api) }
    ).liveData
}