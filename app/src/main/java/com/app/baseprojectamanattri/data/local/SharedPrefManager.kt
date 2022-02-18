package com.app.baseprojectamanattri.data.local

import android.content.SharedPreferences
import com.app.baseprojectamanattri.domain.post.models.PostModel
import com.app.baseprojectamanattri.presentation.common.getValue
import com.app.baseprojectamanattri.presentation.common.saveValue
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class SharedPrefManager @Inject constructor(val sharedPreferences: SharedPreferences) {

    private val gson = Gson()

    companion object {
        const val SESSION_ID = "session_id"
        const val POSTS = "posts"
    }

    fun saveSessionId(sessionId: String) {
        sharedPreferences.saveValue(SESSION_ID, sessionId)
    }

    fun getSessionId(): String? {
        return sharedPreferences.getValue(SESSION_ID)
    }

    fun savePosts(it: List<PostModel>) {
        var posts = gson.toJson(it)
        sharedPreferences.saveValue(POSTS, posts)
    }

    fun getPosts(): List<PostModel> {
        var list= mutableListOf<PostModel>()
        var posts = sharedPreferences.getValue<String>(POSTS)
        posts?.let {
            val empMapType = object : TypeToken<List<PostModel>>() {}.type
            list = gson.fromJson(posts, empMapType)
        }
        return list
    }

}


