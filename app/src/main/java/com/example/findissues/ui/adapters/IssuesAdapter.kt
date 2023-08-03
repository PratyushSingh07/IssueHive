package com.example.findissues.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.findissues.databinding.ItemIssueBinding
import com.example.findissues.models.issues.IssuesList
import com.example.findissues.utils.Browser
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class IssuesAdapter @Inject constructor(
    @ApplicationContext val context: Context,
) : RecyclerView.Adapter<IssuesAdapter.IssuesViewHolder>() {

    private var issueList = ArrayList<IssuesList>()

    fun setUpIssuesList(issueList: List<IssuesList>) {
        this.issueList = issueList.toMutableList() as ArrayList<IssuesList>
        notifyDataSetChanged()
    }

    inner class IssuesViewHolder(private val binding: ItemIssueBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(issue: IssuesList) {
            binding.link.text = issue.html_url
            binding.link.setOnClickListener {
                Browser(context).launch(binding.link.text.toString())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssuesViewHolder {
        val binding =
            ItemIssueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IssuesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IssuesViewHolder, position: Int) {
        val issue = issueList[position]
        holder.bind(issue)
    }

    override fun getItemCount(): Int {
        return issueList.size
    }
}
