package com.example.findissues.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.findissues.databinding.ItemFollowersBinding
import com.example.findissues.models.home.Followers
import com.example.findissues.utils.GlideLoader
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class FollowersAdapter @Inject constructor(
    @ApplicationContext private val context: Context
) : RecyclerView.Adapter<FollowersAdapter.FollowersViewHolder>() {

    private var followersList = ArrayList<Followers>()

    inner class FollowersViewHolder(private val binding: ItemFollowersBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(follower: Followers) {
            binding.tvFollowers.text = follower.login
            val glideLoader = GlideLoader(context)
            glideLoader.loadCircularImage(follower.avatar_url, binding.ivFollowers)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowersViewHolder {
        val binding =
            ItemFollowersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowersViewHolder, position: Int) {
        val follower = followersList[position]
        holder.bind(follower)
    }

    override fun getItemCount(): Int {
        return followersList.size
    }

    fun setUpFollowersList(followersList: List<Followers>) {
        this.followersList = followersList.toMutableList() as ArrayList<Followers>
        notifyDataSetChanged()
    }
}
