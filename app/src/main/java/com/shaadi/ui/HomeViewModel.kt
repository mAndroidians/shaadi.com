package com.shaadi.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.shaadi.data.ApiService
import com.shaadi.data.entity.UserEntity
import com.shaadi.data.repo.UserRepository
import com.shaadi.data.util.Resource
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val newsRepository: UserRepository,
    private val apiService: ApiService
):ViewModel() {
    fun loadNews(): LiveData<Resource<List<UserEntity>>> = newsRepository.getNews(true)
}