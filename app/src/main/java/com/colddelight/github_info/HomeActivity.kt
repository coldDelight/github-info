package com.colddelight.github_info

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.colddelight.github_info.databinding.ActivityHomeBinding
import com.colddelight.github_info.databinding.ActivityMainBinding
import com.colddelight.github_info.presentaion.RepoRecyclerAdapter
import com.colddelight.github_info.presentaion.viewmodel.HomeViewModel
import com.colddelight.github_info.presentaion.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    lateinit var binding : ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var adapter : RepoRecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter =  RepoRecyclerAdapter().apply {
            setHasStableIds(true) // 리사이클러 뷰 업데이트 시 깜빡임 방지
        }
        binding.rvRepo.adapter = adapter

        lifecycleScope.launchWhenStarted {
            viewModel.user.collectLatest {
                if (it != null) {
                    binding.tvName.text = it.name
                    Glide.with(binding.root)
                        .load(it.image)
                        .circleCrop()
                        .into(binding.ivImg)
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.repos.collectLatest {
                if (it != null) {
                    adapter.setData(it)
                }
            }
        }
    }
}