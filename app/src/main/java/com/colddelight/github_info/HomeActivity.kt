package com.colddelight.github_info

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.colddelight.github_info.databinding.ActivityHomeBinding
import com.colddelight.github_info.databinding.ActivityMainBinding
import com.colddelight.github_info.presentaion.viewmodel.HomeViewModel
import com.colddelight.github_info.presentaion.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    lateinit var binding : ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lifecycleScope.launchWhenStarted {
            viewModel.user.collectLatest {
                Log.e("TAG", "onCreatedddddddddddd: $it", )
            }
        }
    }
}