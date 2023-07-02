package com.example.findissues.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.findissues.R
import com.example.findissues.models.home.Following
import com.example.findissues.utils.GlideLoader
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class FollowingAdapter @Inject constructor(
    @ApplicationContext private val context: Context
) : RecyclerView.Adapter<FollowingAdapter.FollowingViewHolder>(){

    private var followingList = ArrayList<Following>()

    inner class FollowingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.iv_following)
        val login = itemView.findViewById<TextView>(R.id.tv_following)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowingViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_following, parent, false)
        return FollowingViewHolder(view)
    }

    override fun onBindViewHolder(holder: FollowingViewHolder, position: Int) {
        val following = followingList[position]
        holder.login.text = following.login
        val glideLoader = GlideLoader(context)
        glideLoader.loadCircularImage(following.avatar_url, holder.image)
    }

    override fun getItemCount(): Int {
        return followingList.size
    }

    fun setUpFollowingList(followingList: List<Following>) {
        this.followingList = followingList.toMutableList() as ArrayList<Following>
        notifyDataSetChanged()
    }
}