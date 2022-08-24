package com.busrayasar.itunessearchapp.ui.main.view

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.busrayasar.itunessearchapp.R
import com.busrayasar.itunessearchapp.data.api.ApiHelperClass
import com.busrayasar.itunessearchapp.data.api.ApiServiceImplementation
import com.busrayasar.itunessearchapp.databinding.FragmentMainBinding
import com.busrayasar.itunessearchapp.ui.base.ViewModelFactory
import com.busrayasar.itunessearchapp.ui.main.adapter.ItunesAdapter
import com.busrayasar.itunessearchapp.ui.main.viewmodel.ITunesViewModel
import com.busrayasar.itunessearchapp.utils.Status

private val ViewBinding.search: Any
    get() {}

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var mainViewModel: ITunesViewModel
    private lateinit var adapter: ItunesAdapter


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
        // return inflater.inflate(R.layout.fragment_main2, container, false)
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


        binding.scrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                limit += 2
               // binding.progressBar.visibility = View.VISIBLE
                mainViewModel.fetchPosts(temp, limit)
            }
        })

    }
    private fun setUpObserver() {
        mainViewModel.data.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    //binding.progressBar.visibility = View.GONE
                    it.data?.let { data -> renderList(data.results) }
                    Log.e("STATUS/OK", it.toString())
                    binding.recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    //binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
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
        //val apiService: ApiService
        mainViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelperClass(ApiServiceImplementation()))
        )
            .get(ITunesViewModel::class.java)
    }

    private fun setUpUI() {
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(),3,
            GridLayoutManager.VERTICAL,false)
        adapter = ITunesAdapter(arrayListOf())
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerView.context,
                (binding.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )

        binding.recyclerView.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun renderList(data: List<Result>) {
        val list = ArrayList<Result>()
        list.addAll(data)
        adapter.changeData(list)
    }





}

