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
            setHasStableIds(true)
        }
        binding.rvRepo.adapter = adapter

        lifecycleScope.launchWhenStarted {
            viewModel.userState.collectLatest {
                if (it != null) {
                    binding.tvName.text = it.user?.name ?: ""
                    Glide.with(binding.root)
                        .load(it.user?.image)
                        .circleCrop()
                        .into(binding.ivImg)
                    it.repo?.let { it1 -> adapter.setData(it1) }
                }
            }
        }

        viewModel.state.observe(this){
            var msg=""
            msg = when(it.second){
                FetchState.BAD_INTERNET->{
                    "소켓 오류입니다."
                }
                FetchState.PARSE_ERROR->{
                    "HTTP 오류입니다."
                }
                FetchState.WRONG_CONNECTION->{
                    "호스트 오류입니다."
                }
                else->{
                    "통신 실패 ${it.first.message}"
                }
            }
        }
    }
}