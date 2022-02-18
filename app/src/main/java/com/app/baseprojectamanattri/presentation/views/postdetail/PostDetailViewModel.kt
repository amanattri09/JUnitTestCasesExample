package com.app.baseprojectamanattri.presentation.views.postdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.baseprojectamanattri.data.remote.post.entities.Result
import com.app.baseprojectamanattri.domain.post.interactor.PostUserCase
import com.app.baseprojectamanattri.domain.post.models.PostModel
import com.app.baseprojectamanattri.presentation.common.base.BaseViewModel
import com.app.baseprojectamanattri.presentation.common.defaultSubscription
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostDetailViewModel @Inject constructor(private val postUserCase: PostUserCase) : BaseViewModel(){

    private var postMutableLiveData= MutableLiveData<Result<PostModel>>()

    fun getPostModelLiveData():LiveData<Result<PostModel>>{
        return postMutableLiveData
    }

    fun getPostDetail(postId: String) {
        viewModelScope.launch {
            postUserCase.getPost(postId).defaultSubscription(postMutableLiveData)
        }
    }
}