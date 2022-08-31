package com.busrayasar.itunessearchapp.ui.main.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.busrayasar.itunessearchapp.R
import com.busrayasar.itunessearchapp.data.api.ApiHelperClass
import com.busrayasar.itunessearchapp.data.api.ApiServiceImplementation
import com.busrayasar.itunessearchapp.data.model.Result
import com.busrayasar.itunessearchapp.databinding.FragmentMoviesBinding
import com.busrayasar.itunessearchapp.databinding.FragmentMusicBinding
import com.busrayasar.itunessearchapp.ui.base.ViewModelFactory
import com.busrayasar.itunessearchapp.ui.main.adapter.ItunesAdapter
import com.busrayasar.itunessearchapp.ui.main.viewmodel.ITunesViewModel
import com.busrayasar.itunessearchapp.utils.Status

class MusicFragment : Fragment() {

    private lateinit var binding: FragmentMusicBinding
    private lateinit var mainViewModel: ITunesViewModel
    private lateinit var viewModelFactory: ViewModelFactory
    private lateinit var recyclerViewAdapter: ItunesAdapter
    private var progressBar: ProgressBar? = null

    private  var temp: String =""
    var limit: Int = 20

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.d("Music", "Music fragment is choosen")
        //return inflater.inflate(R.layout.fragment_music, container, false)
        //temp = "Rihanna" //holds search string
        binding = FragmentMusicBinding.inflate(inflater, container, false);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpUI()
        setUpViewModel()
        setUpObserver()
        setUpSearch()

        mainViewModel.fetchPosts(temp, limit, "track")
    }
    private fun setUpSearch() {
        binding.searchBarView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    Log.d("search", "search yapılıyor onQueryTextSubmit")

                    mainViewModel.fetchPosts(query, limit,"track")
                    temp = query

                }
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    Log.d("search", "search text değişti onQueryTextChange")

                    mainViewModel.fetchPosts(newText,limit, "track")
                    temp = newText
                }
                return false
            }
        })
    }
    private fun setUpUI() {
        progressBar = binding.progressBar
        binding.musicItemRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewAdapter = ItunesAdapter(arrayListOf())
        binding.musicItemRecyclerView.adapter = recyclerViewAdapter
    }
    private fun setUpViewModel() {
        //Mainfragmentla ItunesViewModeli ViewModelProviders ile bağla
        viewModelFactory = ViewModelFactory(ApiHelperClass(ApiServiceImplementation()))
        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(ITunesViewModel::class.java)
        //mainViewModel = ViewModelProvider(this, viewModelFactory).get(ITunesViewModel::class.java)
    }
    private fun setUpObserver() {
        mainViewModel.data.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    it.data?.let { data -> renderList(data.results) }
                    Log.d("STATUS/OK", it.toString())
                    binding.musicItemRecyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    Log.d("STATUS/LOADING", it.toString())
                    binding.progressBar.visibility = View.VISIBLE
                    binding.musicItemRecyclerView.visibility = View.GONE
                }

                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Log.e("STATUS/ERR", it.toString())
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun renderList(data: List<Result>) {
        val list = ArrayList<Result>()
        list.addAll(data)
        recyclerViewAdapter.changeData(list)
    }


}