package com.app.baseprojectamanattri.domain.post.interactor

import com.app.baseprojectamanattri.domain.post.models.PostModel
import com.app.baseprojectamanattri.domain.post.repositary.PostRepositary
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostUserCase  @Inject constructor(private val repositary: PostRepositary){

    fun getPosts(): Flow<List<PostModel>> {
        return repositary.getPosts()
    }

    fun getPost(postId:String): Flow<PostModel> {
        return repositary.getPost(postId)
    }

}