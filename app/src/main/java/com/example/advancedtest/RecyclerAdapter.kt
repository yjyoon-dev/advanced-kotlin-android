package com.example.advancedtest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_recycler.view.*
import java.text.SimpleDateFormat

class RecyclerAdapter: RecyclerView.Adapter<RecyclerAdapter.Holder>() {
    var helper: SQLiteHelper? = null
    var listData = mutableListOf<Memo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val memo = listData.get(position)
        holder.setMemo(memo)
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var selectedMemo: Memo? = null
        init{
            itemView.deleteMemoBtn.setOnClickListener {
                helper?.deleteMemo((selectedMemo!!))
                listData.remove(selectedMemo)
                notifyDataSetChanged()
            }
        }
        fun setMemo(memo:Memo){
            itemView.memoNo.text = "${memo.no}"
            itemView.memoContent.text = memo.content
            val sdf = SimpleDateFormat("yyyy/MM/dd hh:mm")
            itemView.memoDatetime.text = "${sdf.format(memo.datetime)}"
            this.selectedMemo = memo
        }
    }
}

