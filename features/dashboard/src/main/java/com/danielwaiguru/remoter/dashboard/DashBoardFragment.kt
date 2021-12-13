package com.danielwaiguru.remoter.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.danielwaiguru.remoter.dashboard.databinding.FragmentDashBoardBinding
import com.danielwaiguru.remoter.shared.BindingFragment

class DashBoardFragment : BindingFragment<FragmentDashBoardBinding>() {
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentDashBoardBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {

    }
}