package com.example.findissues.ui.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.findissues.R
import com.example.findissues.models.IssuesList

class IssuesAdapter(
    val context: Context,
): RecyclerView.Adapter<IssuesAdapter.IssuesViewHolder>() {

    private var issueList = ArrayList<IssuesList>()

    fun setUpIssuesList(issuesList: List<IssuesList>) {
        this.issueList = issueList as ArrayList<IssuesList>
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
            Toast.makeText(context,"clicked",Toast.LENGTH_SHORT).show()
            val url = holder.link.text.toString()
            val browserIntent: Intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(browserIntent)
        }
    }

    override fun getItemCount(): Int {
        return issueList.size
    }

    inner class IssuesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val link = itemView.findViewById<TextView>(R.id.link)

    }
}