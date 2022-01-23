package com.example.retrofitandcoroutines.Managers

import com.example.retrofitandcoroutines.Helper.Status
import com.example.retrofitandcoroutines.Helper.Status.ERROR
import com.example.retrofitandcoroutines.Helper.Status.SUCCESS
import com.example.retrofitandcoroutines.Helper.Status.LOADING

data class DataManager<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): DataManager<T> =
            DataManager(status = SUCCESS, data = data, message = null)

        fun <T> error(data: T, message: String?): DataManager<T> =
            DataManager(status = ERROR, data = data, message = message)

        fun <T> loading(data: T): DataManager<T> =
            DataManager(status = LOADING, data = data, message = null)

    }
}