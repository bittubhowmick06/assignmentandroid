package com.example.assignmentandroid.ui.mainlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.assignmentandroid.data.model.AuthorData
import com.example.assignmentandroid.databinding.ItemAuthorListBinding

import java.util.Collections.emptyList

class AuthorListAdapter : RecyclerView.Adapter<AuthorListAdapter.MyViewHolder>(){

    var dataList = emptyList<AuthorData>()
    private var listner: AuthorAdapterClickListener?=null
    class MyViewHolder(private val binding: ItemAuthorListBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(authorData: AuthorData, mListener: AuthorAdapterClickListener?){
            binding.viewModel = authorData
            binding.executePendingBindings()

            itemView.setOnClickListener {
                mListener?.onClick(adapterPosition, authorData)
            }

            binding.textViewTitle.text = authorData.author
            binding.textViewDescription.text = "Author: ${authorData.author}, Width: ${authorData.width}, Height: ${authorData.height}"
            Glide.with(itemView.context).load(authorData.download_url).into(binding.imageViewAuthor)

        }
        companion object{
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemAuthorListBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(
                    binding
                )
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(
            parent
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = dataList[position]
        currentItem.let { holder.bind(it,listner) }




        holder.itemView.setOnClickListener {
            dataList.get(position)?.let { it1 -> listner?.onClick(position, it1) }
        }

    }

    override fun getItemViewType(position: Int): Int {
        return dataList[position].id.toInt()
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setData(imageList: List<AuthorData>?){
        this.dataList = imageList
        notifyDataSetChanged()
    }

    interface AuthorAdapterClickListener{
        fun onClick(position:Int, authorData: AuthorData)
    }

    fun setListener(mListner: AuthorAdapterClickListener)
    {
        listner=mListner
    }
}