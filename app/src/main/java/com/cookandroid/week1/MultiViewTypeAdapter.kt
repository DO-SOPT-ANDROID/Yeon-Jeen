package com.cookandroid.week1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cookandroid.week1.data.UserInformation
import com.cookandroid.week1.databinding.ItemFriendBinding

class MultiViewTypeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<UserInformation> = emptyList()

    fun setFriendList(friendList: List<UserInformation>) {
        items = friendList.toList()
        notifyDataSetChanged()
    }

    class FriendViewHolder(private val binding: ItemFriendBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val ivProfile = binding.ivProfile
        private val tvName = binding.tvName
        private val tvSelfDescription = binding.tvSelfDecription

        fun onBind(friendData: UserInformation.Friend) {
            Glide.with(itemView)
                .load(friendData.profileImage)
                .into(ivProfile)
            tvName.text = friendData.name
            tvSelfDescription.text = friendData.self_description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFriendBinding.inflate(inflater, parent, false)
        return FriendViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val friendData = items.getOrNull(position) as? UserInformation.Friend ?: return
        (holder as FriendViewHolder).onBind(friendData)
    }

    override fun getItemCount() = items.size
}
