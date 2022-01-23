package com.example.retrofitandcoroutines.UI.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitandcoroutines.Model.PostModel
import com.example.retrofitandcoroutines.R
import kotlinx.android.synthetic.main.main_item.view.*

class RecyclerViewAdapter(
    private val postModelList: ArrayList<PostModel>,
    private val onClickListener: OnClickListener
) : RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>() {

    interface OnClickListener {
        fun onItemClick(postModel: PostModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_item, parent, false)
        return RowHolder(view)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.bind(postModelList[position], onClickListener)
    }

    override fun getItemCount(): Int = postModelList.size


    class RowHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(postModel: PostModel, onClickListener: OnClickListener) {
            itemView.titleTextView.text = postModel.title
            itemView.bodyTextView.text = postModel.body

            itemView.setOnClickListener {
                onClickListener.onItemClick(postModel)
            }
        }
    }
}