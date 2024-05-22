package com.example.githubusers.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubusers.R
import com.example.githubusers.data.local.entity.UserEntity
import com.example.githubusers.databinding.UserItemRowBinding
import com.example.githubusers.helper.UserDiffCallback

class MainAdapter(
//    private val listUser: List<GithubUser>,
//    private val clickListener: (GithubUser) -> Unit
    private val clickListener: (UserEntity) -> Unit
) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    private val dataUsers = ArrayList<UserEntity>()

    fun setListUser(listUser: List<UserEntity>) {
        val diffCallback = UserDiffCallback(this.dataUsers, listUser)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.dataUsers.clear()
        this.dataUsers.addAll(listUser)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val binding = UserItemRowBinding.inflate(inflater, viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val bind = dataUsers[position]
        viewHolder.bind(bind)
    }

    override fun getItemCount() = dataUsers.size

    inner class ViewHolder(val binding: UserItemRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(userEntity: UserEntity) {
            binding.name.text = userEntity.username
            binding.idUser.text =
                binding.root.context.getString(R.string.user_id, userEntity.id.toString())
            Glide.with(binding.root.context)
                .load(userEntity.urlToImage)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(binding.imageRow)

            binding.root.setOnClickListener {
                clickListener(dataUsers[adapterPosition])
            }
        }
    }
}
