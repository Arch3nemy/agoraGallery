package com.alacrity.template.ui.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.alacrity.template.TemplateApp
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity: AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TemplateApp(context = this, homeViewModel = mainViewModel)
        }
    }

}