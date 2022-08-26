package com.busrayasar.itunessearchapp.ui.main.view

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.androidnetworking.AndroidNetworking
import com.busrayasar.itunessearchapp.R
import com.busrayasar.itunessearchapp.data.api.ApiHelperClass
import com.busrayasar.itunessearchapp.data.api.ApiServiceImplementation
import com.busrayasar.itunessearchapp.databinding.ActivityMainBinding
import com.busrayasar.itunessearchapp.ui.base.ViewModelFactory
import com.busrayasar.itunessearchapp.ui.main.adapter.ItunesAdapter
import com.busrayasar.itunessearchapp.ui.main.adapter.SectionsPagerAdapter
import com.busrayasar.itunessearchapp.ui.main.viewmodel.ITunesViewModel

class MainActivity : AppCompatActivity(), LifecycleOwner {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidNetworking.initialize(applicationContext);

        val viewModelFactory = ViewModelFactory(ApiHelperClass(ApiServiceImplementation()))
       val  mainViewModel = ViewModelProvider(this, viewModelFactory).get(ITunesViewModel::class.java)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)
        val position = tabs.selectedTabPosition

        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{

            override fun onTabSelected(tab: TabLayout.Tab?) {
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })


    }
}