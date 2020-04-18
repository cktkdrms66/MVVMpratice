package com.packages.kakaomvvmpratice.view

import EndlessRecyclerViewScrollListener
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.packages.kakaomvvmpratice.R
import com.packages.kakaomvvmpratice.adapter.MainAdapter
import com.packages.kakaomvvmpratice.base.BaseActivity
import com.packages.kakaomvvmpratice.databinding.ActivityMainBinding
import com.packages.kakaomvvmpratice.model.service.Document
import com.packages.kakaomvvmpratice.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val layoutId: Int
        get() = R.layout.activity_main

    override val viewModel: MainViewModel by viewModel()

    val mainAdapter : MainAdapter by inject()
    lateinit var scrollListener : EndlessRecyclerViewScrollListener

    override fun initStart() {
        initAdapter()
        initDatabind()
        initAfterBind()
    }



    fun initAdapter(){
        recyclerView.run{
            this.adapter = mainAdapter
            val staggeredGridLayoutManager = StaggeredGridLayoutManager(2,1).apply {
                gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
                orientation = StaggeredGridLayoutManager.VERTICAL
            }
            layoutManager = staggeredGridLayoutManager
            scrollListener = object : EndlessRecyclerViewScrollListener(staggeredGridLayoutManager){
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                    viewModel.getImageSearch(editText.text.toString(), page+1)
                }

            }
            addOnScrollListener(scrollListener)
            setHasFixedSize(true)

        }
    }



    fun initDatabind(){
        viewModel.searchResponseLiveData.observe(this, Observer {
            mainAdapter.setList(it as ArrayList<Document>)
            mainAdapter.notifyDataSetChanged()
        })
        viewModel.snackbarMessage.observe(this, Observer {
            println("aaaa")
            Snackbar.make(findViewById(android.R.id.content), "asdasd", Snackbar.LENGTH_LONG).show()
        })
    }



    fun initAfterBind(){
        button.setOnClickListener {
            scrollListener.resetState()
            viewModel.getImageSearchAfterRemove(editText.text.toString(), 1)
            viewModel.doSomething()
        }

    }


}
