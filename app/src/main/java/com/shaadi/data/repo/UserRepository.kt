package com.shaadi.data.repo


import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.shaadi.data.ApiService
import com.shaadi.dao.UserDao
import com.shaadi.data.entity.UserEntity
import com.shaadi.data.remote.GetResultResponse
import com.shaadi.data.util.ApiResponse
import com.shaadi.data.util.AppExecutors
import com.shaadi.data.util.NetworkBoundResource
import com.shaadi.data.util.Resource
import javax.inject.Inject


class UserRepository @Inject constructor(
    private val apiService: ApiService,
    private val appExecutors: AppExecutors,
    private val userDao: UserDao,
) {

    fun getUserData(forceFetch: Boolean = true): LiveData<Resource<List<UserEntity>>> {

        return object : NetworkBoundResource<List<UserEntity>, GetResultResponse>(appExecutors) {
            override fun saveCallResult(item: GetResultResponse) {
                val list = ArrayList<UserEntity>()
                for ((i, newsItem) in item.results.withIndex()) {
                    val userEntity = UserEntity(
                        id = i,
                        name = "${newsItem.name.title} ${newsItem.name.first} ${newsItem.name.last}",
                        gender = when (newsItem.gender) {
                            "female" -> "F"
                            "male" -> "M"
                            else -> "M"
                        },
                        age = newsItem.dob.age,
                        picture = newsItem.picture.medium
                    )
                    list.add(userEntity)
                }
                userDao.insertAlll(list)
            }

            override fun shouldFetch(data: List<UserEntity>?): Boolean {
                return data == null || data.isEmpty() || forceFetch
            }

            override fun loadFromDb(): LiveData<List<UserEntity>> = userDao.getUserData()

            override fun createCall(): LiveData<ApiResponse<GetResultResponse>> =
                apiService.getResult()
        }.asLiveData()
    }

    @WorkerThread
    suspend fun updateUserData(accepted: Int, id: Int) {
        userDao.update(accepted = accepted, id = id)
    }


}



