package com.shaadi.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shaadi.R
import com.shaadi.data.entity.UserEntity
import com.shaadi.databinding.UserItemBinding

class UserAdapter(private val onClickLisner: OnClickLisner, private val context: Context) :
    ListAdapter<UserEntity, UserAdapter.MyNewsViewHolder>(Callback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyNewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: UserItemBinding =
            DataBindingUtil.inflate(inflater, R.layout.user_item, parent, false)
        return MyNewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyNewsViewHolder, position: Int) {
        holder.bind(getItem(position), onClickLisner, context)
    }

    class Callback : DiffUtil.ItemCallback<UserEntity>() {
        override fun areItemsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
            return oldItem == newItem
        }
    }

    class MyNewsViewHolder(private val binding: UserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UserEntity?, onClickLisner: OnClickLisner, context: Context) {
            binding.user = item
            when (item?.accepted) {
                0 -> {
                    binding.btnAccept.visibility = View.VISIBLE
                    binding.btnDecline.visibility = View.VISIBLE
                }
                1 -> {
                    binding.btnAccept.visibility = View.GONE
                    binding.btnDecline.visibility = View.VISIBLE
                    binding.btnDecline.text = context.getString(R.string.member_declined)
                }
                2 -> {
                    binding.btnDecline.visibility = View.GONE
                    binding.btnAccept.visibility = View.VISIBLE
                    binding.btnAccept.text = context.getString(R.string.member_accepted)
                }
            }

            binding.btnAccept.setOnClickListener {
                onClickLisner.onClickOnAccept(item!!)
            }
            binding.btnDecline.setOnClickListener {
                onClickLisner.onClickOnDecline(item!!)
            }


        }


    }

    interface OnClickLisner {
        fun onClickOnDecline(newsEntity: UserEntity)
        fun onClickOnAccept(newsEntity: UserEntity)

    }

}