package com.social.network.ui.comments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.social.network.data.SocialNetworkPost
import com.social.network.databinding.FragmentCommentsBinding

class CommentsFragment : Fragment() {

    private val args by navArgs<CommentsFragmentArgs>()

    private var _binding: FragmentCommentsBinding? = null
    private val binding get() = _binding!!

    private lateinit var post: SocialNetworkPost

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        post = args.post
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCommentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val commentAdapter = CommentAdapter()
//        commentAdapter.submitList(post.comments)
//
//        binding.apply {
//            recyclerView.apply {
//                setHasFixedSize(true)
//                itemAnimator = null
//                adapter = commentAdapter
//            }
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}