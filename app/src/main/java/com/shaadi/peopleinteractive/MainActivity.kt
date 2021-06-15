package com.shaadi.peopleinteractive

import android.os.Bundle
import com.shaadi.peopleinteractive.base.BaseActivity
import com.shaadi.peopleinteractive.base.BaseViewModel
import com.shaadi.peopleinteractive.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun getVM(): BaseViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}