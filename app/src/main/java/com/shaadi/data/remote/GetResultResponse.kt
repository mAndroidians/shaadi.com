package com.shaadi.data.remote

data class GetResultResponse(val results:List<Data>){
    data class Data(val gender:String,val name:UserName,val id:UserID){
        data class UserName(val title:String,val first:String,val last:String)
        data class UserID(val name:String,val value:String)
    }
}