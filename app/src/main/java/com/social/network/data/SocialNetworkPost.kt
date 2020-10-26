package com.social.network.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SocialNetworkPost(
    val id: String,
    val accountName: String,
    val accountIcon: String,
    val description: String,
    val descriptionImage: String?,
    val likesAmount: String,
    val commentsAmount: String,
    val comments: List<Comment>
) : Parcelable {

    @Parcelize
    data class Comment(
        val id: String,
        val accountName: String,
        val accountIcon: String,
        val text: String
    ) : Parcelable {
        override fun toString(): String {
            return "\nComment(id='$id', accountName='$accountName', accountIcon='$accountIcon', text='$text')"
        }
    }

    override fun toString(): String {
        return "\nSocialNetworkPost(id='$id', accountName='$accountName', accountIcon='$accountIcon', description='$description', descriptionImage=$descriptionImage, likesAmount='$likesAmount', commentsAmount='$commentsAmount', comments=$comments)"
    }
}