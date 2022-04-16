package com.alif.githubmembers.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.alif.githubmembers.data.GithubUser
import com.alif.githubmembers.databinding.ItemUserBinding
import com.bumptech.glide.Glide
import java.util.*
import kotlin.collections.ArrayList

class UserAdapter : RecyclerView.Adapter<UserAdapter.ListViewHolder>(), Filterable {

    private var onItemClickCallback : OnItemClickCallback? = null
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    private var listUser : ArrayList<GithubUser> = ArrayList()
    private var listUserFiltered : ArrayList<GithubUser> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder((view))
    }
    override fun onBindViewHolder(holder: UserAdapter.ListViewHolder, position: Int) {
        holder.bind(listUserFiltered[position])
    }

    override fun getItemCount(): Int = listUserFiltered.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint.toString()
                listUserFiltered = if (charString.isEmpty()) {
                    listUser
                } else {
                    val filteredList = ArrayList<GithubUser>()
                    for (row in listUser) {
                        if (row.login.lowercase(Locale.getDefault()).startsWith(charString.lowercase(
                                Locale.getDefault()
                            ))) {
                            filteredList.add(row)
                        }
                    }
                    filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = listUserFiltered
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                listUserFiltered = if (results?.values == null)
                    ArrayList()
                else
                    results.values as ArrayList<GithubUser>
                notifyDataSetChanged()
            }
        }
    }

    inner class ListViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(user: GithubUser) {
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(listUserFiltered[absoluteAdapterPosition])
            }

            binding.apply{
                Glide.with(itemView)
                    .load(user.avatarUrl)
                    .circleCrop()
                    .into(imgItemAvatar)
                tvItemUsername.text = user.login
            }
        }
    }
    interface OnItemClickCallback {
        fun onItemClicked(data: GithubUser)
    }

    fun addData(list : List<GithubUser>) {
        listUser = list as ArrayList<GithubUser>
        listUserFiltered = listUser
        notifyDataSetChanged()
    }
}