package com.thesis.distanceguard.presentation.countries

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.thesis.distanceguard.R
import com.thesis.distanceguard.api.model.CountryResponse
import com.thesis.distanceguard.presentation.base.BaseFragment
import com.thesis.distanceguard.presentation.base.BaseRecyclerViewAdapter
import com.thesis.distanceguard.presentation.detail.DetailFragment
import com.thesis.distanceguard.presentation.main.activity.MainActivity
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_countries.*
import timber.log.Timber

/**
 * Created by Viet Hua on 04/10/2021.
 */

class CountriesFragment : BaseFragment() {

    private lateinit var countriesRecyclerViewAdapter: CountriesRecyclerViewAdapter
    private lateinit var countriesViewModel: CountriesViewModel
    private lateinit var testRecyclerViewAdapter: TestRecyclerViewAdapter
    override fun getResLayoutId(): Int {
        return R.layout.fragment_countries
    }

    override fun onMyViewCreated(view: View) {
        Timber.d("onMyViewCreated")
        setupViewModel()
        //setupRecyclerView()
        val linearLayoutManager = LinearLayoutManager(view.context)
        testRecyclerViewAdapter = TestRecyclerViewAdapter(view.context)
        rv_countries.apply {
            layoutManager = linearLayoutManager
            adapter = testRecyclerViewAdapter
        }

        testRecyclerViewAdapter.itemClickListener = object :
            TestRecyclerViewAdapter.ItemClickListener<CountryResponse> {
            override fun onClick(position: Int, item: CountryResponse) {
                Timber.d("onClick: $item")
                ViewCompat.postOnAnimationDelayed(view!!, // Delay to show ripple effect
                    Runnable {
                        val mainActivity = activity as MainActivity
                        mainActivity.addFragment(DetailFragment(), DetailFragment.TAG,R.id.container_main)
                    }
                    , 50)

            }

            override fun onLongClick(position: Int, item: CountryResponse) {}
        }
        edt_search.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val filteredList = TestRecyclerViewAdapter.filter(countriesViewModel.countryList.value!!,p0.toString())
                testRecyclerViewAdapter.replaceAll(filteredList!!)
                rv_countries.scrollToPosition(0)
            }
        })

    }

    private fun setupViewModel() {
        Timber.d("setupViewModel")
        AndroidSupportInjection.inject(this)
        countriesViewModel = ViewModelProvider(this, viewModelFactory).get(CountriesViewModel::class.java)

        countriesViewModel.fetchCountryList().observe(this,countryListObserver)
    }

    private fun setupRecyclerView() {
        Timber.d("setupRecyclerView")
        val linearLayoutManager = LinearLayoutManager(view!!.context)
        countriesRecyclerViewAdapter = CountriesRecyclerViewAdapter(view!!.context)

        countriesRecyclerViewAdapter.itemClickListener = object :
            BaseRecyclerViewAdapter.ItemClickListener<CountryResponse> {
            override fun onClick(position: Int, item: CountryResponse) {
                Timber.d("onClick: $item")
                ViewCompat.postOnAnimationDelayed(view!!, // Delay to show ripple effect
                    Runnable {
                        val mainActivity = activity as MainActivity
                        mainActivity.addFragment(DetailFragment(), DetailFragment.TAG,R.id.container_main)
                    }
                    , 50)

            }

            override fun onLongClick(position: Int, item: CountryResponse) {}
        }

        rv_countries.apply {
            layoutManager = linearLayoutManager
            setHasFixedSize(true)
            adapter = countriesRecyclerViewAdapter
        }
    }

    private val countryListObserver = Observer<ArrayList<CountryResponse>> {
        it?.let {
            testRecyclerViewAdapter.add(it)
        }
    }
}