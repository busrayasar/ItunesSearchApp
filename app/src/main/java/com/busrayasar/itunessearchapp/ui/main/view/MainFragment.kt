package com.busrayasar.itunessearchapp.ui.main.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.busrayasar.itunessearchapp.data.api.ApiHelperClass
import com.busrayasar.itunessearchapp.data.api.ApiServiceImplementation
import com.busrayasar.itunessearchapp.databinding.FragmentMainBinding
import com.busrayasar.itunessearchapp.ui.base.ViewModelFactory
import com.busrayasar.itunessearchapp.ui.main.adapter.ItunesAdapter
import com.busrayasar.itunessearchapp.ui.main.viewmodel.ITunesViewModel
import com.busrayasar.itunessearchapp.utils.Status
import com.busrayasar.itunessearchapp.data.model.Result
import androidx.lifecycle.ViewModelProviders

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var mainViewModel: ITunesViewModel
    private lateinit var viewModelFactory: ViewModelFactory
    private lateinit var recyclerViewAdapter: ItunesAdapter


    private lateinit var temp: String
    var limit: Int = 20

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false);
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpUI()
        setUpViewModel()
        setUpObserver()

        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    mainViewModel.fetchPosts(query, limit)
                    temp = query
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    mainViewModel.fetchPosts(newText,limit)
                    temp = newText
                }
                return false
            }
        })


        binding.nestedScrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                limit += 2
               // binding.progressBar.visibility = View.VISIBLE
                mainViewModel.fetchPosts(temp, limit)
            }
        })

    }
    //observe our livedata
    //lifeCycleOwner -> yaşam döngüsünün sahibi kim, this-> fragmentin kendisini verir
    //vievLifecycleOwner ise lifecycleOwner kimse onu bize getitirir
    private fun setUpObserver() {
        mainViewModel.data.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    //binding.progressBar.visibility = View.GONE
                    it.data?.let { data -> renderList(data.results) }
                    Log.d("STATUS/OK", it.toString())
                    binding.itemRecyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    Log.d("STATUS/LOADING", it.toString())
                    //binding.progressBar.visibility = View.VISIBLE
                    binding.itemRecyclerView.visibility = View.GONE
                }

                Status.ERROR -> {
                    //binding.progressBar.visibility = View.GONE
                    Log.e("STATUS/ERR", it.toString())
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setUpViewModel() {
        //Mainfragmentla ItunesViewModeli ViewModelProviders ile bağla
        viewModelFactory = ViewModelFactory(ApiHelperClass(ApiServiceImplementation()))
        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(ITunesViewModel::class.java)
        //mainViewModel = ViewModelProvider(this, viewModelFactory).get(ITunesViewModel::class.java)
    }

    private fun setUpUI() {
        binding.itemRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewAdapter = ItunesAdapter(arrayListOf())
        /*
        binding.itemRecyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.itemRecyclerView.context,
                (binding.itemRecyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
*/
        binding.itemRecyclerView.adapter = recyclerViewAdapter

    }
    @SuppressLint("NotifyDataSetChanged")
    private fun renderList(data: List<Result>) {
        val list = ArrayList<Result>()
        list.addAll(data)
        recyclerViewAdapter.changeData(list)
    }

}

