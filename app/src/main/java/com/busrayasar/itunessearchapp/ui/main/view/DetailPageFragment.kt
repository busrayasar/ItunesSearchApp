package com.busrayasar.itunessearchapp.ui.main.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.busrayasar.itunessearchapp.R
import com.busrayasar.itunessearchapp.databinding.FragmentDetailPageBinding


class DetailPageFragment : Fragment() {
  private lateinit var binding: FragmentDetailPageBinding

  //private lateinit var detailResultViewModel: DetailPageViewModel

  override fun onCreate(savedInstanceState: Bundle?){
      super.onCreate(savedInstanceState)
  }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       // val view = inflater.inflate(R.layout.fragment_detail_page, container, false)
        //view.setOnClickListener{Navigation.findNavController(view).navigate(R.id.action_detailPageFragment2_to_placeholderFragment) }
        //return view
        binding = FragmentDetailPageBinding.inflate(inflater, container, false);
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       // detailResultViewModel= ViewModelProviders.of(this).get(DetailPageViewModel::class.java)

// Her item “artworkUrl100, collectionPrice, collectionName, releaseDate” bilgilerini içermeli.
        val name = arguments?.getString("name") //collectionName
        val imageUrl = arguments?.getString("avatarArtWorkUrl100")
        val releaseDate = arguments?.getString("releaseDate")
        val collectionPrice = arguments?.getString("price")


        binding.tvDetailName.text = name
        binding.tvDetailPrice.text = collectionPrice
        binding.tvReleaseDate.text = releaseDate

        Glide.with(binding.ivDetailAvatar.context)
            .load(imageUrl)
            .into(binding.ivDetailAvatar)
        Log.e("dataName",name.toString())
        Log.e("dataAvatar",imageUrl.toString())
        Log.e("dataDescription",collectionPrice.toString())
    }

    //fun observableLiveData(){}
}