package com.app.baseprojectamanattri.presentation.common

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.baseprojectamanattri.data.remote.post.entities.Result
import com.app.baseprojectamanattri.network.ErrorCodes
import com.app.baseprojectamanattri.network.NetworkError
import retrofit2.HttpException



fun <T> LiveData<Result<T>>.customObserver(
    owner: LifecycleOwner,
    onLoading: (Boolean) -> Unit,
    onSuccess: ((data:T) -> Unit)?,
    onError: ((throwable:Throwable,showError:Boolean) -> Unit)?
) {
    this.observe(owner) {
        when (it) {
            is Result.Loading -> {
                onLoading.invoke(true)
            }
            is Result.Success<T> -> {
                onLoading.invoke(false)
                onSuccess?.invoke(it.data)
            }
            is Result.Error -> {
                onLoading.invoke(false)
                onError?.invoke(it.throwable, it.showErrorView)
            }
        }
    }
}
