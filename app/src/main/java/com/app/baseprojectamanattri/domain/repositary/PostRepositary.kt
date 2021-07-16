package com.app.baseprojectamanattri.domain.post.repositary

import com.app.baseprojectamanattri.domain.post.models.PostModel
import io.reactivex.Single

interface PostRepositary {
     fun getPosts(): List<PostModel>?
    fun getPostsRx(): Single<List<PostModel>>
}