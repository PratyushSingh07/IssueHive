package com.example.findissues.ui.adapters

import android.content.Context
import android.content.Intent
import android.media.Image
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.findissues.R
import com.example.findissues.models.IssuesList
import com.example.findissues.models.PinnedRepo
import com.example.findissues.utils.Browser
import com.example.findissues.utils.GlideLoader

class PinnedRepoAdapter(
    val context: Context
) : RecyclerView.Adapter<PinnedRepoAdapter.PinnedRepoViewHolder>() {

    private var pinnedRepoList = ArrayList<PinnedRepo>()

    fun setUpPinnedRepoList(pinnedRepoList: List<PinnedRepo>) {
        this.pinnedRepoList = pinnedRepoList.toMutableList() as ArrayList<PinnedRepo>
        notifyDataSetChanged()
    }

    inner class PinnedRepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val language = itemView.findViewById<TextView>(R.id.tv_pinned_repo)
        val image = itemView.findViewById<ImageView>(R.id.iv_pinned_repo)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PinnedRepoAdapter.PinnedRepoViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_pinned_repo, parent, false)
        return PinnedRepoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PinnedRepoAdapter.PinnedRepoViewHolder, position: Int) {
        val pinnedRepo = pinnedRepoList[position]
        holder.language.text = pinnedRepo.language
        val glideLoader = GlideLoader(context)
        glideLoader.loadImage(pinnedRepo.image, holder.image)
        holder.image.setOnClickListener {
            Browser(context).launch(pinnedRepo.link)
        }
    }

    override fun getItemCount(): Int {
        return pinnedRepoList.size
    }
}