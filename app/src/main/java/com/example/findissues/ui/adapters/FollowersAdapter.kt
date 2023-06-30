package com.example.findissues.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.findissues.R
import com.example.findissues.models.Followers
import com.example.findissues.utils.GlideLoader
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class FollowersAdapter @Inject constructor(
    @ApplicationContext private val context: Context
) : RecyclerView.Adapter<FollowersAdapter.FollowersViewHolder>() {

    private var followersList = ArrayList<Followers>()

    inner class FollowersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.iv_followers)
        val login = itemView.findViewById<TextView>(R.id.tv_followers)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowersViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_followers, parent, false)
        return FollowersViewHolder(view)
    }

    override fun onBindViewHolder(holder: FollowersViewHolder, position: Int) {
        val follower = followersList[position]
        holder.login.text = follower.login
        val glideLoader = GlideLoader(context)
        glideLoader.loadCircularImage(follower.avatar_url, holder.image)
    }

    override fun getItemCount(): Int {
        return followersList.size
    }

    fun setUpFollowersList(followersList: List<Followers>) {
        this.followersList = followersList.toMutableList() as ArrayList<Followers>
        notifyDataSetChanged()
    }

}