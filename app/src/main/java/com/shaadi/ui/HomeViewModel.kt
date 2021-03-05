package com.shaadi.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shaadi.data.entity.UserEntity
import com.shaadi.data.repo.UserRepository
import com.shaadi.data.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val newsRepository: UserRepository
) : ViewModel() {
    fun loadNews(): LiveData<Resource<List<UserEntity>>> = newsRepository.getUserData(true)

    fun updateData(acceptedId: Int, id: Int) =
        viewModelScope.launch(Dispatchers.IO) { newsRepository.updateUserData(acceptedId, id) }
}