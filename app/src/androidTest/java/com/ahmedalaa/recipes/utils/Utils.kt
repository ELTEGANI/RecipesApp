package com.ahmedalaa.recipes.utils

import com.ahmedalaa.recipes.data.model.Recipe
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import okio.buffer
import okio.source
import java.lang.reflect.Type

object Utils {
    fun readFileResponse(fileName: String): String {
        val inputStream = javaClass.classLoader!!.getResourceAsStream("api_response/$fileName")
        return inputStream.source().buffer().readString(Charsets.UTF_8)
    }
    fun <T> readFileResponseToListOfObject(fileName: String, moshi: Moshi): List<T>? {
        val inputStream = javaClass.classLoader!!.getResourceAsStream("api_response/$fileName")
        val temp = inputStream.source().buffer().readString(Charsets.UTF_8)
        val type: Type = Types.newParameterizedType(
            MutableList::class.java,
            Recipe::class.java
        )

        val jsonAdapter: JsonAdapter<out List<T>> = moshi.adapter(type)
        return jsonAdapter.fromJson(temp)
    }
}
