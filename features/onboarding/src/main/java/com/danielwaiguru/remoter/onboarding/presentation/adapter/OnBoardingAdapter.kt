package com.danielwaiguru.remoter.onboarding.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.danielwaiguru.remoter.onboarding.presentation.OnBoardingItemFragment

class OnBoardingAdapter(
    private val fragmentActivity: FragmentActivity,
    private val itemsCount: Int
): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = itemsCount

    override fun createFragment(position: Int): Fragment {
        return OnBoardingItemFragment.getInstance(position)
    }
}