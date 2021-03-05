package com.shaadi.data.remote

data class GetResultResponse(val results: List<Data>) {
    data class Data(
        val gender: String,
        val name: UserName,
        val id: UserID,
        val picture: UserPicture,
        val dob:UserDOB
    ) {
        data class UserName(val title: String, val first: String, val last: String)
        data class UserID(val name: String, val value: String)
        data class UserPicture(val large: String, val medium: String, val thumbnail: String)
        data class UserDOB(val date:String,val age:Int)
    }
}