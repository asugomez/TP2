package com.example.tp1

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class AdapterList(private val dataset: MutableList<List>): RecyclerView.Adapter<AdapterList.ItemViewHolder>()  {

    private var mOnItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ItemViewHolder(itemView = inflater.inflate(R.layout.list_list, parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind((dataset[position]))

        holder.itemView.setOnClickListener {
            if (mOnItemClickListener != null) {
                mOnItemClickListener!!.onItemClick(position)
            }
        }
    }

    override fun getItemCount() = dataset.size

    public fun addData(text: String) {
        dataset.add(List(text))
        notifyItemChanged(dataset.size)
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        mOnItemClickListener = listener
    }

    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textView = itemView.findViewById<TextView>(R.id.ObjectList)

        fun bind(list: List) {
            textView.text = list.listTextStr
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}
