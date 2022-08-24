package com.busrayasar.itunessearchapp.ui.main.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.busrayasar.itunessearchapp.R
import com.busrayasar.itunessearchapp.ui.main.view.DetailPageFragment
import com.busrayasar.itunessearchapp.data.model.Result

class ItunesAdapter(private val data: ArrayList<Result>):
    RecyclerView.Adapter<ItunesAdapter.DataViewHolder>() {
    class DataViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewCollectionName: TextView
        val imageViewAvatar: ImageView
        init {
            textViewCollectionName = view.findViewById(R.id.textView_item)
            imageViewAvatar = view.findViewById(R.id.imageView_item)
        }

        fun bind(data: Result) {
            textViewCollectionName.text = data.collectionName

            Glide.with(imageViewAvatar.context)
                .load(data.artworkUrl100)
                .into(imageViewAvatar)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_design_layout, parent, false)
        return DataViewHolder(view)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(data[position])
        holder.itemView.setOnClickListener {
            val resultData = data[position]

            Log.e("adapter", resultData.collectionName)

            val action = PlaceholderFragmentDirections.actionPlaceholderFragmentToDetailPageFragment2()
           // action.name = resultData.collectionName
            //action.avatar = resultData.artworkUrl100
            //action.detail = resultData.collectionPrice.toString()
            val navController = Navigation.findNavController(holder.itemView)
            navController.navigate(action)
        }

    }

    override fun getItemCount():Int {
        Log.d("count", data.size.toString())
        return data.size
    }
    fun addData(list: List<Result>){
        Log.d("Data added", data.addAll(list).toString())
        data.addAll(list)
    }

    fun changeData(newList: ArrayList<Result>){
        this.data.clear()
        this.data.addAll(newList)
        notifyDataSetChanged()
    }



}





