package com.example.retrofitandcoroutines.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitandcoroutines.Helper.Status
import com.example.retrofitandcoroutines.Model.PostModel
import com.example.retrofitandcoroutines.R
import com.example.retrofitandcoroutines.Services.RestApiHelper
import com.example.retrofitandcoroutines.Services.RetrofitBuilder
import com.example.retrofitandcoroutines.UI.Adapter.RecyclerViewAdapter
import com.example.retrofitandcoroutines.ViewModel.BaseViewModel.BaseViewModel
import com.example.retrofitandcoroutines.ViewModel.PostViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.OnClickListener {

    private lateinit var mPostModelView: PostViewModel
    private lateinit var mPostModelAdapter: RecyclerViewAdapter
    private var mPostModelList: ArrayList<PostModel>? = ArrayList();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        postListRV.layoutManager = LinearLayoutManager(this)
        initViewModel()
        loadData()
    }

    private fun initViewModel() {
        mPostModelView = ViewModelProviders.of(
            this,
            BaseViewModel(RestApiHelper(RetrofitBuilder.restApi))
        ).get(PostViewModel::class.java)
    }


    private fun loadData() {
        mPostModelView.getData().observe(this, { manager ->
            manager?.let { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        result.data?.let { it ->
                            mPostModelList = ArrayList(it)
                            postListRV.visibility = View.VISIBLE
                            progressBar.visibility = View.GONE
                            mPostModelList?.let {
                                mPostModelAdapter = RecyclerViewAdapter(it, this@MainActivity)
                                postListRV.adapter = mPostModelAdapter

                            }
                        }
                    }
                    Status.ERROR -> {
                        Toast.makeText(this, "Bir hata oluştu", Toast.LENGTH_SHORT).show()
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        postListRV.visibility = View.GONE
                    }
                }
            }
        })
    }

    override fun onItemClick(postModel: PostModel) {
        Toast.makeText(this, "Clicked Item : ${postModel.title}", Toast.LENGTH_SHORT).show()
    }

}