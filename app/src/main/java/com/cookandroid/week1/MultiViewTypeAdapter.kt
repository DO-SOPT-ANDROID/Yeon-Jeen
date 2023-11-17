package com.cookandroid.week1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView


class MultiViewTypeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<A> = emptyList()

    fun setFriendList(friendList: List<A>) {
        items = friendList.toList()
        notifyDataSetChanged()
    }

    class FriendViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivProfile: ShapeableImageView = itemView.findViewById(R.id.iv_profile)
        private val tvName: TextView = itemView.findViewById(R.id.tv_name)
        private val tvSelfDescription: TextView = itemView.findViewById(R.id.tv_self_decription)

        fun onBind(friendData: A.Friend) {
            Glide.with(itemView)
                .load(friendData.profileImage)
                .into(ivProfile)
            tvName.text = friendData.name
            tvSelfDescription.text = friendData.self_description
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_friend, parent, false)
        return FriendViewHolder(view)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val friendData = items.getOrNull(position) as? A.Friend ?: return
        (holder as FriendViewHolder).onBind(friendData)
    }

    override fun getItemCount() = items.size
}