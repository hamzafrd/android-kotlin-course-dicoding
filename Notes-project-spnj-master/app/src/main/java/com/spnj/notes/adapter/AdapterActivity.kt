package com.spnj.notes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.spnj.notes.MainActivity.Companion.data
import com.spnj.notes.databinding.ListRecycleBinding

class AdapterActivity : RecyclerView.Adapter<AdapterViewHolderActivity>() {
    private lateinit var aListener: OnItemClickListener
    private lateinit var bListener: OnItemLongClickListener

    interface OnItemClickListener {
        fun onItemclick(position: Int)
    }
    fun setItemClickListener(listener: OnItemClickListener){
        aListener = listener
    }
    //// Long Click
    interface OnItemLongClickListener   {
        fun onItemclick(position: Int){
        }
    }

    fun setItemLongClickListener(listener: OnItemLongClickListener){
        bListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolderActivity {
        val binding = ListRecycleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdapterViewHolderActivity(binding,aListener,bListener)
    }

    override fun onBindViewHolder(holder: AdapterViewHolderActivity, position: Int) {
//        holder.setData(arrayData[position])
        val posisi = data[position]
        holder.apply{
            setData(posisi)
        }
        //            parent bisa manggil fun dari child apapun itu
    }

    override fun getItemCount(): Int {
        return data.size
    }
}