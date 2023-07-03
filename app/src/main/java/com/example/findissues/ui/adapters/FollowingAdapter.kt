package com.example.findissues.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.findissues.databinding.ItemFollowingBinding
import com.example.findissues.models.home.Following
import com.example.findissues.utils.GlideLoader
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class FollowingAdapter @Inject constructor(
    @ApplicationContext private val context: Context
) : RecyclerView.Adapter<FollowingAdapter.FollowingViewHolder>() {

    private var followingList = ArrayList<Following>()

    inner class FollowingViewHolder(private val binding: ItemFollowingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(following: Following) {
            binding.tvFollowing.text = following.login
            val glideLoader = GlideLoader(context)
            glideLoader.loadCircularImage(following.avatar_url, binding.ivFollowing)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowingViewHolder {
        val binding =
            ItemFollowingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowingViewHolder, position: Int) {
        val following = followingList[position]
        holder.bind(following)
    }

    override fun getItemCount(): Int {
        return followingList.size
    }

    fun setUpFollowingList(followingList: List<Following>) {
        this.followingList = followingList.toMutableList() as ArrayList<Following>
        notifyDataSetChanged()
    }
}
