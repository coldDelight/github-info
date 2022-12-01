package com.colddelight.github_info

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.colddelight.github_info.databinding.ActivityMainBinding
import com.colddelight.github_info.presentaion.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import net.openid.appauth.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var authService: AuthorizationService
    lateinit var binding : ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authService = AuthorizationService(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            githubAuth()
        }
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == RESULT_OK) {
            val ex = AuthorizationException.fromIntent(it.data!!)
            val result = AuthorizationResponse.fromIntent(it.data!!)
            if (ex != null){
                Log.e("Github Auth", "launcher: $ex")
            } else {

                val secret = ClientSecretBasic(BuildConfig.GITHUB_CLIENT_SECRET)
                val tokenRequest = result?.createTokenExchangeRequest()

                authService.performTokenRequest(tokenRequest!!, secret) {res, exception ->
                    if (exception != null){
                        Log.e("Github Auth", "launcher: ${exception.error}" )
                    } else {
                        val token = res?.accessToken
                        viewModel.setToken(token!!)

                        val intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            }
        }
    }

    private fun githubAuth(){
        val redirectUri = Uri.parse("info-app://callback")
        val authorizeUri = Uri.parse("https://github.com/login/oauth/authorize")
        val tokenUri = Uri.parse("https://github.com/login/oauth/access_token")
//        val authorizeUri = Uri.parse(BuildConfig.BASE_URL+"login/oauth/authorize")
//        val tokenUri = Uri.parse(BuildConfig.BASE_URL+"login/oauth/access_token")

        val config = AuthorizationServiceConfiguration(authorizeUri,tokenUri)

        val req = AuthorizationRequest.Builder(config,BuildConfig.GITHUB_CLIENT_ID,ResponseTypeValues.CODE,redirectUri ).
        setScope("user repo admin").build()

        val intent = authService.getAuthorizationRequestIntent(req)
        launcher.launch(intent)

    }

    override fun onDestroy() {
        super.onDestroy()
        authService.dispose()
    }
}