package com.social.network.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.social.network.data.Repository

class HomeViewModel @ViewModelInject constructor(repository: Repository) : ViewModel() {
    val posts = repository.getPostsResults().cachedIn(viewModelScope)
}