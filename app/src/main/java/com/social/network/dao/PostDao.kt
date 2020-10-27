package com.social.network.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.social.network.data.PagingSource
import com.social.network.data.SocialNetworkPost

@Dao
interface PostDao {

    @Insert(onConflict = REPLACE)
    suspend fun savePost(post: List<SocialNetworkPost>)

    @Query("SELECT * FROM home_feed")
    fun getPosts(): PagingSource<Int, SocialNetworkPost>
}