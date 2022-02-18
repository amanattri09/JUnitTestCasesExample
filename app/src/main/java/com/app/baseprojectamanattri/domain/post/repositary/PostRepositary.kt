package com.app.baseprojectamanattri.domain.post.repositary

import com.app.baseprojectamanattri.domain.post.models.PostModel
import kotlinx.coroutines.flow.Flow

interface PostRepositary {
    fun getPosts(): Flow<List<PostModel>>
    fun getPost(postId:String): Flow<PostModel>
}