package com.social.network.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.social.network.R
import com.social.network.data.SocialNetworkPost
import com.social.network.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment(), PostsAdapter.OnItemClickListener, View.OnClickListener {

    private val viewModel by viewModels<HomeViewModel>()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnClickListeners()

        val postAdapter = PostsAdapter(this)
        postAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

        binding.apply {
            myProfilePicture.load("https://previews.123rf.com/images/metelsky/metelsky1809/metelsky180900233/109815470-man-avatar-profile-male-face-icon-vector-illustration-.jpg") {
                placeholder(R.drawable.ic_account)
                transformations(CircleCropTransformation())
            }
            recyclerView.apply {
                isNestedScrollingEnabled = false
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
                itemAnimator = null
                adapter = postAdapter.withLoadStateHeaderAndFooter(
                    header = PostsLoadStateAdapter { postAdapter.retry() },
                    footer = PostsLoadStateAdapter { postAdapter.retry() }
                )
                buttonRetry.setOnClickListener {
                    postAdapter.retry()
                }
            }
        }

        observeViewModel(postAdapter)

//        binding.swipeRefreshLayout.setOnRefreshListener {
//            observeViewModel(postAdapter)
//            binding.swipeRefreshLayout.isRefreshing = false
//        }

        postAdapter.addLoadStateListener { loadState ->
            binding.apply {
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                recyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
                buttonRetry.isVisible = loadState.source.refresh is LoadState.Error
                textViewError.isVisible = loadState.source.refresh is LoadState.Error

                // empty view
                if (loadState.source.refresh is LoadState.NotLoading && loadState.append.endOfPaginationReached && postAdapter.itemCount < 1) {
                    recyclerView.isVisible = false
                    textViewEmpty.isVisible = true
                } else {
                    textViewEmpty.isVisible = false
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View?) {
        when (v!!) {
            binding.buttonStatus -> {
                val action = HomeFragmentDirections.actionHomeFragmentToStatusFragment()
                findNavController().navigate(action)
            }
        }
    }

    override fun onCommentButtonClicked(post: SocialNetworkPost) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(post)
        findNavController().navigate(action)
    }

    override fun onDescriptionImageClicked(post: SocialNetworkPost) {}

    override fun onLikeButtonClicked(post: SocialNetworkPost) {}

    private fun setOnClickListeners() {
        binding.buttonStatus.setOnClickListener(this)
    }

    private fun observeViewModel(postAdapter: PostsAdapter) {
        viewModel.posts.observe(viewLifecycleOwner) {
            postAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

    }
}