package com.social.network.ui.home

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.social.network.R
import com.social.network.data.SocialNetworkPost
import com.social.network.databinding.ItemPostBinding

class PostsAdapter(private val listener: OnItemClickListener) : PagingDataAdapter<SocialNetworkPost, PostsAdapter.PostViewHolder>(POSTS_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    inner class PostViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        init {
            binding.imageViewDescription.setOnClickListener(this)
            binding.buttonLike.setOnClickListener(this)
            binding.buttonComment.setOnClickListener(this)
            binding.textViewDescription.setOnClickListener(this)
        }

        @SuppressLint("SetTextI18n")
        fun bind(post: SocialNetworkPost) {
            Log.d("PostAdapter", "bind: $post")

            binding.apply {

                imageViewProfilePicture.load(post.accountIcon) {
                    placeholder(R.drawable.ic_account)
                    transformations(CircleCropTransformation())
                }

                textViewName.text = post.accountName
//                if (post.description.isEmpty()) textViewDescription.isVisible = false
//                else
                textViewDescription.text = post.description

                imageViewDescription.load(post.descriptionImage)

                textViewLikesAmount.text = "Likes: " + "%,d".format(post.likesAmount.toInt())
                textViewCommentsAmount.text = "Comments: " + "%,d".format(post.commentsAmount.toInt())
            }

            fun makeInvisible(textView: TextView, shouldBeInvisible: Boolean) {
                textView.isVisible = shouldBeInvisible
            }
        }

        override fun onClick(v: View?) {
            if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                val item = getItem(bindingAdapterPosition)
                if (item != null) {

                    when (v!!) {
                        binding.imageViewDescription -> {
                            listener.onDescriptionImageClicked(item)
                        }

                        binding.buttonLike -> {
                            listener.onLikeButtonClicked(item)
                        }

                        binding.buttonComment -> {
                            listener.onCommentButtonClicked(item)

                        }

                        binding.textViewDescription -> {
                            listener.onCommentButtonClicked(item)
                        }
                    }
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onDescriptionImageClicked(post: SocialNetworkPost)
        fun onLikeButtonClicked(post: SocialNetworkPost)
        fun onCommentButtonClicked(post: SocialNetworkPost)
    }

    companion object {
        private val POSTS_COMPARATOR = object : DiffUtil.ItemCallback<SocialNetworkPost>() {
            override fun areItemsTheSame(oldItem: SocialNetworkPost, newItem: SocialNetworkPost) = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: SocialNetworkPost, newItem: SocialNetworkPost) = oldItem == newItem
        }
    }
}