package com.app.baseprojectamanattri.presentation.views.post

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.baseprojectamanattri.databinding.ActivityPostLayoutBinding
import com.app.baseprojectamanattri.domain.post.models.PostModel
import com.app.baseprojectamanattri.presentation.common.base.BaseActivity
import com.app.baseprojectamanattri.presentation.common.customObserver
import com.app.baseprojectamanattri.presentation.views.postdetail.PostDetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostActivity : BaseActivity<ActivityPostLayoutBinding>() {

    private var onItemSelected: ((PostModel) -> Unit)? = {
        PostDetailActivity.start(PostActivity@ this,it)
    }

    companion object {
        fun start(activity: Activity) {
            var intent = Intent(activity, PostActivity::class.java)
            activity.startActivity(intent)
        }
    }

    private val viewmodel: PostViewModel by viewModels()
    private lateinit var postRecylarAdapter: PostRecylarAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityPostLayoutBinding.inflate(layoutInflater))
        setViews()
        init()
        setUpObserver()
    }

    private fun init() {
        viewmodel.fetchPostUsingFlow()
    }

    private fun setViews() {
        binding.rvPosts.layoutManager = LinearLayoutManager(this)
        binding.rvPosts.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        postRecylarAdapter = PostRecylarAdapter()
        binding.rvPosts.adapter = postRecylarAdapter
        postRecylarAdapter.onItemSelected = onItemSelected
    }

    private fun setUpObserver() {
        viewmodel.getPosts().customObserver(
            this,
            onLoading = ::onLoading,
            onSuccess = {
                postRecylarAdapter.setData(it)
            },
            onError = ::onError
        )
    }


}






