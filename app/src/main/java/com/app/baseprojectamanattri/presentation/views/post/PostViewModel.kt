package com.app.baseprojectamanattri.presentation.views.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.baseprojectamanattri.data.remote.post.entities.Result
import com.app.baseprojectamanattri.domain.post.interactor.PostUserCase
import com.app.baseprojectamanattri.domain.post.models.PostModel
import com.app.baseprojectamanattri.presentation.common.base.BaseViewModel
import com.app.baseprojectamanattri.presentation.common.defaultSubscription
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(private val postUserCase: PostUserCase) :
    BaseViewModel() {

    private lateinit var postsArray: List<PostModel>
    val posts = MutableLiveData<Result<List<PostModel>>>()

    fun fetchPostUsingFlow() {
        viewModelScope.launch {
            postUserCase.getPosts().defaultSubscription(posts)
        }
    }

    fun getPosts(): LiveData<Result<List<PostModel>>> {
        return posts;
    }

}
