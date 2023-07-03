package com.example.findissues.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.findissues.R
import com.example.findissues.models.home.Repository
import com.example.findissues.utils.Browser
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class RepositoryAdapter @Inject constructor(
    @ApplicationContext private val context: Context
) : RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {

    private var repoList = ArrayList<Repository>()

    inner class RepositoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val repoName = itemView.findViewById<TextView>(R.id.tv_repo_name)
        val stars = itemView.findViewById<TextView>(R.id.tv_stars)
        val language = itemView.findViewById<TextView>(R.id.tv_language)
        val description = itemView.findViewById<TextView>(R.id.tv_description)
        val relativeLayout = itemView.findViewById<RelativeLayout>(R.id.parent_rl_repo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_repo, parent, false)
        return RepositoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val repo = repoList[position]
        holder.repoName.text = repo.name
        holder.stars.text = repo.stargazers_count.toString()
        holder.language.text = repo.language
        holder.description.text = repo.description
        holder.relativeLayout.setOnClickListener {
            Browser(context).launch(repo.html_url)
        }
    }

    override fun getItemCount(): Int {
        return repoList.size
    }

    fun setUpRepositoryList(repoList: List<Repository>) {
        this.repoList = repoList.toMutableList() as ArrayList<Repository>
        notifyDataSetChanged()
    }

}