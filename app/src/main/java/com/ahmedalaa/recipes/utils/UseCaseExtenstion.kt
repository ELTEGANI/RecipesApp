package com.ahmedalaa.recipes.utils

import androidx.lifecycle.MutableLiveData
import com.ahmedalaa.recipes.R
import com.ahmedalaa.recipes.domain.BaseUseCase
import timber.log.Timber

fun <T, Params> BaseUseCase<T, Params>.loadWithLiveData(viewStateLiveData: MutableLiveData<ApiResponse<T>>): BaseUseCase<T, Params> {
    doBefore {
        viewStateLiveData.value = ApiResponse.InProgress
    }
    onResult {
        viewStateLiveData.value = ApiResponse.Success(it)
    }
    onCancel {
        viewStateLiveData.value = ApiResponse.InProgress
    }
    onError {
        Timber.d(it)
        viewStateLiveData.value = ApiResponse.Error(R.string.app_name)
    }
    doAfter {
        val result = (viewStateLiveData.value as? ApiResponse.Success)?.data
        viewStateLiveData.value = ApiResponse.Success(result)
    }
    return this
}
