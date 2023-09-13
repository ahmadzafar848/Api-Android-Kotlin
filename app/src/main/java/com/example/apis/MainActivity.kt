package com.example.apis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.NavHost
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    val BASE_URL = "https://jsonplaceholder.typicode.com/"
    private lateinit var userRv: RecyclerView
    private lateinit var userAdapter: UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userRv = findViewById(R.id.usersRv)
        userRv.layoutManager = LinearLayoutManager(this)
        getListOfUsers()
    }

    fun getListOfUsers() {
        val retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build().create(UserInterface::class.java)

        var retroData=retrofit.getAllUsers()
        retroData.enqueue(object :Callback<List<UsersModelItem>>{
            override fun onResponse(
                call: Call<List<UsersModelItem>>,
                response: Response<List<UsersModelItem>>
            ) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        userAdapter = UserAdapter(body,object:UserAdapter.OnItemClickListener{
                            override fun onItemClick(userId: Int) {
                                Toast.makeText(this@MainActivity,userId.toString(),Toast.LENGTH_SHORT).show()
                            }
                        })
                        userRv.adapter = userAdapter // Set the adapter to the RecyclerView
                    } else {
                        return Toast.makeText(this@MainActivity,response.errorBody().toString(),Toast.LENGTH_SHORT).show()

                    }
                } else {
                    return Toast.makeText(this@MainActivity,response.errorBody().toString(),Toast.LENGTH_SHORT).show()
                }
            }


            override fun onFailure(call: Call<List<UsersModelItem>>, t: Throwable) {
               return Toast.makeText(this@MainActivity,t.toString(),Toast.LENGTH_SHORT).show()
            }

        })
    }
}