package com.danielwaiguru.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.viewbinding.ViewBinding
import com.danielwaiguru.onboarding.databinding.FragmentOnBoardingBinding
import com.danielwaiguru.shared.BindingFragment

class OnBoardingFragment : BindingFragment<FragmentOnBoardingBinding>() {
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentOnBoardingBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}