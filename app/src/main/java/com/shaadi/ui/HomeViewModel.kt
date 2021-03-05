package com.shaadi.ui

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.shaadi.data.ApiService
import com.shaadi.data.entity.UserEntity
import com.shaadi.data.repo.UserRepository
import com.shaadi.data.util.Resource
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val newsRepository: UserRepository
):ViewModel() {
    fun loadNews(): LiveData<Resource<List<UserEntity>>> = newsRepository.getUserData(true)

    suspend fun updateData(acceptedId:Int,id:Int) = newsRepository.updateUserData(acceptedId,id)
}