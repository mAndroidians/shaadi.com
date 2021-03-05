package com.shaadi.ui

import android.content.Context
import android.view.LayoutInflater
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
            binding.vm = item
            binding.cardView.setOnClickListener {
                onClickLisner.onClick(item!!)
            }



        }


    }

    interface OnClickLisner {
        fun onClick(newsEntity: UserEntity)
    }

}