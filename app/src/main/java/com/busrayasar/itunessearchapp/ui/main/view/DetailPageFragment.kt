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

        val name = arguments?.getString("name")
        val imageUrl = arguments?.getString("avatar")
        val description = arguments?.getString("detail")


        binding.tvDetailName.text = name
        binding.tvDetailPrice.text = description

        Glide.with(binding.ivDetailAvatar.context)
            .load(imageUrl)
            .into(binding.ivDetailAvatar)
        Log.e("dataName",name.toString())
        Log.e("dataAvatar",imageUrl.toString())
        Log.e("dataDescription",description.toString())
    }

}