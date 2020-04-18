package com.packages.kakaomvvmpratice.adapter

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.packages.kakaomvvmpratice.R
import com.packages.kakaomvvmpratice.databinding.ItemImageBinding
import com.packages.kakaomvvmpratice.model.DataModel
import com.packages.kakaomvvmpratice.model.service.Document
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_image.view.*
import java.util.*
import kotlin.collections.ArrayList


class MainAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var list = ArrayList<Document>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return MainViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MainViewHolder).bind(list[position])
    }

    inner class MainViewHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bind(item : Document){
            itemView.run{
                if(!item.thumbnail.isEmpty()){
                    imageView.layoutParams.height = Random().nextInt(250) + 300
                    Picasso.get().load(item.thumbnail).placeholder(R.drawable.image_default)
                        .into(imageView)
                    imageView.setOnClickListener {
                        AlertDialog.Builder(context).setMessage(item.title).setNeutralButton("네"){_,_-> }.show()
                    }
                }else{
                    itemView.imageView.setImageResource(R.drawable.image_default)
                }



            }
        }
    }

    fun setList(list : ArrayList<Document>){
        this.list = list
    }
}