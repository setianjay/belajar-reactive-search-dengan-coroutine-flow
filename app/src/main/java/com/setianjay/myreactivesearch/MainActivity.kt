package com.setianjay.myreactivesearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.setianjay.myreactivesearch.presenter.MainViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var edPlaces: AutoCompleteTextView

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        setupObserver()
    }

    private fun initView(){
        edPlaces = findViewById(R.id.ed_place)
        edPlaces.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                lifecycleScope.launch {
                    mainViewModel.queryChannel.send(p0.toString())
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }

    private fun setupObserver(){
        mainViewModel.searchResult.observe(this){ listPlace ->
            val placeName = arrayListOf<String?>()
            Log.d(this@MainActivity::class.simpleName, "listPlace: $listPlace")
            listPlace.map{
                placeName.add(it.address)
            }

            val adapt = ArrayAdapter(this@MainActivity, android.R.layout.select_dialog_item, placeName)
            adapt.notifyDataSetChanged()
            edPlaces.setAdapter(adapt)
        }
    }
}