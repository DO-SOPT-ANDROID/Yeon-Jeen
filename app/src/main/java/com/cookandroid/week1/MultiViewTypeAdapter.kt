package com.cookandroid.week1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView


class MultiViewTypeAdapter(private var items: List<A>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private val VIEW_TYPE_ITEM1 = 1
    private val VIEW_TYPE_ITEM2 = 2

    fun setFriendList(friendList: List<A>) {
        items = friendList
        notifyDataSetChanged()
    }


    class MyProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivProfile: ShapeableImageView = itemView.findViewById(R.id.im_iv_profile)
        private val tvName: TextView = itemView.findViewById(R.id.im_tv_name)
        private val tvSelfDescription: TextView = itemView.findViewById(R.id.im_tv_self_decription)

        fun bindMyProfile(myProfile: A.MyProfile) {

            ivProfile.setImageResource(myProfile.profileImageResId)
            tvName.text = myProfile.name
            tvSelfDescription.text = myProfile.selfDescription
        }
    }

    class FriendViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivProfile: ShapeableImageView = itemView.findViewById(R.id.iv_profile)
        private val tvName: TextView = itemView.findViewById(R.id.tv_name)
        private val tvSelfDescription: TextView = itemView.findViewById(R.id.tv_self_decription)

        fun onBind(friendData: A.Friend) {
            ivProfile.setImageResource(friendData.profileImage)
            tvName.text = friendData.name
            tvSelfDescription.text = friendData.self_description
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_ITEM1 -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_myprofile, parent, false)
                MyProfileViewHolder(view)
            }

            VIEW_TYPE_ITEM2 -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_friend, parent, false)
                FriendViewHolder(view)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MyProfileViewHolder -> {
                val myProfile = items[position] as A.MyProfile
                holder.bindMyProfile(myProfile)
            }

            is FriendViewHolder -> {
                val friendData = items[position] as A.Friend
                (holder as FriendViewHolder).onBind(friendData)
            }
        }
    }


    override fun getItemViewType(position: Int): Int {
        if (items[position] is A.MyProfile) {
            return VIEW_TYPE_ITEM1
        } else {
            return VIEW_TYPE_ITEM2
        }
    }


    override fun getItemCount()=items.size + 1

}