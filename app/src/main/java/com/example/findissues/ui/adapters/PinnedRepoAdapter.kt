package com.example.findissues.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.findissues.databinding.ItemPinnedRepoBinding
import com.example.findissues.models.home.PinnedRepo
import com.example.findissues.utils.Browser
import com.example.findissues.utils.GlideLoader
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PinnedRepoAdapter @Inject constructor(
    @ApplicationContext val context: Context
) : RecyclerView.Adapter<PinnedRepoAdapter.PinnedRepoViewHolder>() {

    private var pinnedRepoList = ArrayList<PinnedRepo>()

    fun setUpPinnedRepoList(pinnedRepoList: List<PinnedRepo>) {
        this.pinnedRepoList = pinnedRepoList.toMutableList() as ArrayList<PinnedRepo>
        notifyDataSetChanged()
    }

    inner class PinnedRepoViewHolder(private val binding: ItemPinnedRepoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(pinnedRepo: PinnedRepo) {
            binding.tvPinnedRepo.text = pinnedRepo.language
            val glideLoader = GlideLoader(context)
            glideLoader.loadImage(pinnedRepo.image, binding.ivPinnedRepo)
            binding.ivPinnedRepo.setOnClickListener {
                Browser(context).launch(pinnedRepo.link)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PinnedRepoAdapter.PinnedRepoViewHolder {
        val binding =
            ItemPinnedRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PinnedRepoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PinnedRepoAdapter.PinnedRepoViewHolder, position: Int) {
        val pinnedRepo = pinnedRepoList[position]
        holder.bind(pinnedRepo)
    }

    override fun getItemCount(): Int {
        return pinnedRepoList.size
    }
}
