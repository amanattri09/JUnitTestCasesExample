package com.app.baseprojectamanattri

import android.app.Application
import android.content.SharedPreferences
import com.app.baseprojectamanattri.data.local.SharedPrefManager
import com.app.baseprojectamanattri.di.TestRepositoryProvider
import com.app.baseprojectamanattri.domain.post.repositary.PostRepositary
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(MockitoJUnitRunner::class)
class PostsUnitTest {

    lateinit var postRepository: PostRepositary
    @Mock
    lateinit var application: Application
    @Mock
    lateinit var sharedPreferences: SharedPreferences

    @Before
    fun onSetUp() {
        MockitoAnnotations.initMocks(this)
        postRepository=TestRepositoryProvider().getPostRepository(application, SharedPrefManager(sharedPreferences))
    }

    @Test
     fun validatePosts() = runBlocking {
        postRepository.getPosts().catch {e->
            assertTrue(false)
        }.collect {
            assertTrue(it.isNotEmpty())
        }
    }

    @Test
    fun validateGetPostById()= runBlocking {
        postRepository.getPost("1").catch {e->
            assertTrue(false)
        }.collect {
            assertNotNull(it)
        }
    }
}