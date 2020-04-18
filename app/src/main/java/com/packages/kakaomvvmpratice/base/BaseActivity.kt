package com.packages.kakaomvvmpratice.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.snackbar.Snackbar

abstract class BaseActivity <T : ViewDataBinding, R : BaseViewModel> : AppCompatActivity(){

    lateinit var binding: T
    abstract val layoutId : Int
    abstract val viewModel : R

    abstract fun initStart()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, layoutId)
        initStart()
    }

}