package com.app.baseprojectamanattri.network.api

import com.app.baseprojectamanattri.data.remote.post.entities.PostEntity
import com.app.baseprojectamanattri.data.remote.post.entities.PostEntityItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {

    @GET("/posts")
    suspend fun listPosts(): List<PostEntityItem>

    @GET("/posts/{postId}")
    suspend fun getPostById(@Path("postId") postId: String): PostEntityItem
}