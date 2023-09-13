package com.example.apis

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(private val usersList: List<UsersModelItem>,private val listener:OnItemClickListener):RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    interface OnItemClickListener{
        fun onItemClick(userId:Int)
    }

    class UserViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val userId=itemView.findViewById<TextView>(R.id.tvUserId)
        val userName=itemView.findViewById<TextView>(R.id.tvUserName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
    val itemView=LayoutInflater.from(parent.context).inflate(R.layout.users_rv_layout,parent,false)
      return UserViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
      holder.userId.text=usersList[position].id.toString()
      holder.userName.text=usersList[position].name
      holder.itemView.setOnClickListener{
         listener.onItemClick(usersList[position].id)
     }
    }
}