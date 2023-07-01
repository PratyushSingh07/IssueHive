package com.example.findissues.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.findissues.R
import com.example.findissues.models.IssuesList
import com.example.findissues.utils.Browser
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class IssuesAdapter @Inject constructor(
    @ApplicationContext val context: Context,
): RecyclerView.Adapter<IssuesAdapter.IssuesViewHolder>() {

    private var issueList = ArrayList<IssuesList>()

    fun setUpIssuesList(issueList: List<IssuesList>) {
        this.issueList = issueList.toMutableList() as ArrayList<IssuesList>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssuesViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_issue, parent, false)
        return IssuesViewHolder(view)
    }

    override fun onBindViewHolder(holder: IssuesViewHolder, position: Int) {
        val issue = issueList[position]
        holder.link.text = issue.html_url
        holder.link.setOnClickListener {
            Browser(context).launch(holder.link.text.toString())
        }
    }

    override fun getItemCount(): Int {
        return issueList.size
    }

    inner class IssuesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val link = itemView.findViewById<TextView>(R.id.link)

    }
}