package com.social.network.ui.comments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.social.network.R
import com.social.network.data.SocialNetworkPost
import com.social.network.databinding.ItemCommentBinding

class CommentAdapter : ListAdapter<SocialNetworkPost.Comment, CommentAdapter.CommentViewHolder>(COMMENTS_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val binding = ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val currentItem = currentList[position]

        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    inner class CommentViewHolder(private val binding: ItemCommentBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(comment: SocialNetworkPost.Comment) {
            binding.apply {
                imageViewAccountIcon.load(comment.accountIcon) {
                    placeholder(R.drawable.ic_account)
                    transformations(CircleCropTransformation())
                }
                textViewName.text = comment.accountName
                textViewComment.text = comment.text
            }
        }
    }

    companion object {
        private val COMMENTS_COMPARATOR = object : DiffUtil.ItemCallback<SocialNetworkPost.Comment>() {
            override fun areItemsTheSame(oldItem: SocialNetworkPost.Comment, newItem: SocialNetworkPost.Comment) = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: SocialNetworkPost.Comment, newItem: SocialNetworkPost.Comment) = oldItem == newItem
        }
    }
}