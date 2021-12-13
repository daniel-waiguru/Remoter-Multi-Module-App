package com.danielwaiguru.onboarding.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnBoardingAdapter(
    private val fragmentActivity: FragmentActivity,
    private val itemsCount: Int
): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = itemsCount

    override fun createFragment(position: Int): Fragment {
        TODO("Not yet implemented")
    }
}