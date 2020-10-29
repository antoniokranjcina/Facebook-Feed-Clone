package com.social.network.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "home_feed")
@Parcelize
data class SocialNetworkPost(
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "comment_id")
    val commentId: String,
    @ColumnInfo(name = "account_name")
    val accountName: String,
    @ColumnInfo(name = "account_icon")
    val accountIcon: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "description_image")
    val descriptionImage: String?,
    @ColumnInfo(name = "likes_amount")
    val likesAmount: String,
    @ColumnInfo(name = "comments_amount")
    val commentsAmount: String,
    @ColumnInfo(name = "category")
    val category: String,
) : Parcelable {

    @Entity(tableName = "comments")
    @Parcelize
    data class Comment(
        @ColumnInfo(name = "id")
        val id: String,
        @ColumnInfo(name = "account_name")
        val accountName: String,
        @ColumnInfo(name = "account_icon")
        val accountIcon: String,
        @ColumnInfo(name = "text")
        val text: String
    ) : Parcelable
}