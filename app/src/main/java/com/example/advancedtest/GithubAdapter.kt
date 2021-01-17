package com.example.advancedtest

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.github_recycler.view.*

class GithubAdapter: RecyclerView.Adapter<GithubAdapter.Holder>(){
    var userList = Repository()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.github_recycler, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val user = userList.get(position)
        holder.setUser(user)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        var reposUrl: String? = null
        init{
            itemView.setOnClickListener{
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(reposUrl))
                itemView.context.startActivity(intent)
            }
        }
        fun setUser(user:RepositoryItem){
            itemView.githubName.text = user.name
            itemView.githubId.text = user.node_id
            reposUrl = user.html_url
            Glide.with(itemView).load(user.owner.avatar_url).into(itemView.githubImg)

        }
    }
}

