package com.app.baseprojectamanattri.presentation.common

import androidx.lifecycle.MutableLiveData
import com.app.baseprojectamanattri.data.remote.post.entities.Result
import com.app.baseprojectamanattri.domain.post.models.PostModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect

fun <T> MutableLiveData<Result<T>>.setLoading() = postValue(Result.Loading)
fun <T> MutableLiveData<Result<T>>.setSuccess(data: T) = postValue(Result.Success(data))
fun <T> MutableLiveData<Result<T>>.setError(throwable: Throwable, showErrorView: Boolean) = postValue(Result.Error(throwable,showErrorView))


suspend fun <Param> Flow<Param>.defaultSubscription(posts: MutableLiveData<Result<Param>>, showError:Boolean=true) {
    catch {e->
        posts.setError(e,showError)
    }.collect {
        posts.setSuccess(it)
    }
}