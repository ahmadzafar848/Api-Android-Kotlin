package com.example.apis

import retrofit2.Call
import retrofit2.http.GET

interface UserInterface {
    @GET("/users")
    fun  getAllUsers():Call<List<UsersModelItem>>
}