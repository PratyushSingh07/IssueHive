package com.example.findissues.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.findissues.databinding.ItemRepoBinding
import com.example.findissues.models.home.Repository
import com.example.findissues.utils.Browser
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class RepositoryAdapter @Inject constructor(
    @ApplicationContext private val context: Context
) : RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {

    private var repoList = ArrayList<Repository>()

    inner class RepositoryViewHolder(private val binding: ItemRepoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(repo: Repository) {
            binding.tvRepoName.text = repo.name
            binding.tvStars.text = repo.stargazers_count.toString()
            binding.tvLanguage.text = repo.language
            binding.tvDescription.text = repo.description
            binding.parentRlRepo.setOnClickListener {
                Browser(context).launch(repo.html_url)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val binding =
            ItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepositoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val repo = repoList[position]
        holder.bind(repo)
    }

    override fun getItemCount(): Int {
        return repoList.size
    }

    fun setUpRepositoryList(repoList: List<Repository>) {
        this.repoList = repoList.toMutableList() as ArrayList<Repository>
        notifyDataSetChanged()
    }
}
