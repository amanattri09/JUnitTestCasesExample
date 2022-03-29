package com.app.baseprojectamanattri

import android.app.Application
import android.content.SharedPreferences
import com.app.baseprojectamanattri.data.local.SharedPrefManager
import com.app.baseprojectamanattri.data.remote.post.repo.PostRepositaryImp
import com.app.baseprojectamanattri.di.TestRepositaryProvider
import com.app.baseprojectamanattri.domain.post.repositary.PostRepositary
import com.app.baseprojectamanattri.network.*
import com.app.baseprojectamanattri.network.api.ApiService
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Retrofit

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(MockitoJUnitRunner::class)
class ExampleUnitTest {

    lateinit var postRepository: PostRepositary
    @Mock
    lateinit var application: Application
    @Mock
    lateinit var sharedPreferences: SharedPreferences

    @Before
    fun onSetUp() {
        MockitoAnnotations.initMocks(this)
        postRepository=TestRepositaryProvider().getPostRepositary(application, SharedPrefManager(sharedPreferences))
    }

    @Test
     fun addition_isCorrect() = runBlocking {
        postRepository.getPosts().catch {e->
            e.cause
            assert(false)
        }.collect {
            assertTrue(it.isNotEmpty())
        }
    }
}