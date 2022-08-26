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
import com.busrayasar.itunessearchapp.ui.main.view.MainFragmentDirections

class ItunesAdapter(private val data: ArrayList<Result>):
    RecyclerView.Adapter<ItunesAdapter.DataViewHolder>() {
    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textViewCollectionName: TextView
        val imageViewAvatar: ImageView
        init {
            textViewCollectionName = itemView.findViewById(R.id.textView_item)
            imageViewAvatar = itemView.findViewById(R.id.imageView_item)
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
        //recyclerviewdaki item tık
        holder.itemView.setOnClickListener {
            val resultData = data[position]

            Log.e("adapter", resultData.collectionName)

            val action = MainFragmentDirections.actionMainFragmentToDetailPageFragment()
          // action.name = resultData.collectionName
            //action.avatarArtWorkUrl100 = resultData.artworkUrl100
            //action.price = resultData.collectionPrice.toString()
            //action.releaseDate = resultData.releaseDate.toString()
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

    //gelen item listesini güncelle
    fun changeData(newList: ArrayList<Result>){
        this.data.clear()
        this.data.addAll(newList)
        notifyDataSetChanged()
    }

}





