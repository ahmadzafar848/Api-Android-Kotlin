package com.example.apis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val userID=findViewById<TextView>(R.id.tvId)
        val intent=intent
        val userId=intent.getStringExtra("userId")
        Log.d("Ahmad", userId.toString())
        userID.text = userId


    }
}