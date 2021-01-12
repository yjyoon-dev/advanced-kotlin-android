package com.example.advancedtest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_recycler.view.*
import java.text.SimpleDateFormat

class RecyclerAdapter: RecyclerView.Adapter<RecyclerAdapter.Holder>() {
    var helper: RoomHelper? = null
    var listData = mutableListOf<RoomMemo>()

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
        holder.setRoomMemo(memo)
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var selectedRoomMemo: RoomMemo? = null
        init{
            itemView.deleteRoomMemoBtn.setOnClickListener {
                helper?.roomMemoDao()?.delete(selectedRoomMemo!!)
                listData.remove(selectedRoomMemo)
                notifyDataSetChanged()
            }
        }
        fun setRoomMemo(memo:RoomMemo){
            itemView.memoNo.text = "${memo.no}"
            itemView.memoContent.text = memo.content
            val sdf = SimpleDateFormat("yyyy/MM/dd hh:mm")
            itemView.memoDatetime.text = "${sdf.format(memo.datetime)}"
            this.selectedRoomMemo = memo
        }
    }
}

