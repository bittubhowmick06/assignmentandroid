package com.example.assignmentandroid.ui.mainlist

import android.content.DialogInterface
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignmentandroid.R
import com.example.assignmentandroid.data.model.AuthorData
import com.example.assignmentandroid.databinding.ActivityMainListBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainListActivity : AppCompatActivity() {
    private val viewModel: MainListViewModel by viewModels()
    private val authorListAdapter:AuthorListAdapter by lazy { AuthorListAdapter()}
    private var _binding: ActivityMainListBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.authorRecylerview.setHasFixedSize(true)
        viewModel.observableLiveData.observe(this) { listOf->
            authorListAdapter.setData(listOf)
        }

        authorListAdapter.setListener(object : AuthorListAdapter.AuthorAdapterClickListener{
            override fun onClick(position: Int, authorData: AuthorData) {
                val alertDialog: AlertDialog? = this?.let {
                    val builder = AlertDialog.Builder(this@MainListActivity)
                    builder.apply {
                        setPositiveButton(R.string.ok,
                            DialogInterface.OnClickListener { dialog, id ->
                                // User clicked OK button
                            })

                    }
                    builder.setMessage("Author: ${authorData.author}, Width: ${authorData.width}, Height: ${authorData.height}")
                    builder.create()
                }
                alertDialog?.show()
            }

        })

       binding.layoutManager= LinearLayoutManager(this@MainListActivity)
        binding.adapter=authorListAdapter
        binding.swiperefreshLayout.setOnRefreshListener {
            binding.swiperefreshLayout.isRefreshing=false
            viewModel.getAuthor()
        }
    }
}