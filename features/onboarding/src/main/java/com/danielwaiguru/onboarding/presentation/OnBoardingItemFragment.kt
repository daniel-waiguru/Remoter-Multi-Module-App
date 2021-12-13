package com.danielwaiguru.onboarding.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.os.bundleOf
import androidx.viewbinding.ViewBinding
import com.danielwaiguru.onboarding.R
import com.danielwaiguru.onboarding.databinding.FragmentOnBoardingItemBinding
import com.danielwaiguru.shared.BindingFragment
import com.danielwaiguru.shared.utils.AppConstants.ARG_POSITION

class OnBoardingItemFragment : BindingFragment<FragmentOnBoardingItemBinding>() {
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentOnBoardingItemBinding::inflate

    companion object {
        fun getInstance(position: Int) = OnBoardingItemFragment().apply {
            arguments = bundleOf(ARG_POSITION to position)
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
    }

    private fun setupUi() {
        val position = requireArguments().getInt(ARG_POSITION)
        val onBoardingTitles = resources.getStringArray(R.array.onboard_titles)
        val onBoardingDesc = resources.getStringArray(R.array.onboard_descriptions)
        binding.lottieView.setAnimation("1.json")
    }
}