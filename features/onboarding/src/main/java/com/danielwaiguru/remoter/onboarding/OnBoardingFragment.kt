package com.danielwaiguru.remoter.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import androidx.viewpager2.widget.ViewPager2
import com.danielwaiguru.remoter.onboarding.databinding.FragmentOnBoardingBinding
import com.danielwaiguru.remoter.onboarding.presentation.adapter.OnBoardingAdapter
import com.danielwaiguru.remoter.shared.BindingFragment
import com.danielwaiguru.remoter.shared.utils.extensions.slideOutBottom

class OnBoardingFragment : BindingFragment<FragmentOnBoardingBinding>() {
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentOnBoardingBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        setupViewPager()
    }

    private fun setupViewPager() {
        val numberOfScreens = resources.getStringArray(R.array.onboard_titles).size
        val onBoardingAdapter = createPagerAdapter(numberOfScreens)
        binding.onBoardPager.apply {
            adapter = onBoardingAdapter
            registerOnPageChangeCallback(onBoardingPageChangeCallback)
        }
    }
    private val onBoardingPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            updateMarker(position)
        }
    }

    private fun updateMarker(position: Int) {
        with(binding) {
            when (position) {
                0 -> {
                    btnExplore.slideOutBottom()
                    indicator1.background = getDrawable(requireContext(), R.drawable.blue_circle)
                    indicator2.background = getDrawable(
                        requireContext(),
                        R.drawable.light_blue_cicle
                    )
                    indicator3.background = getDrawable(
                        requireContext(),
                        R.drawable.light_blue_cicle
                    )
                }
                1 -> {
                    btnExplore.slideOutBottom()
                    indicator1.background = getDrawable(
                        requireContext(),
                        R.drawable.light_blue_cicle
                    )
                    indicator2.background = getDrawable(
                        requireContext(),
                        R.drawable.blue_circle
                    )
                    indicator3.background = getDrawable(
                        requireContext(),
                        R.drawable.light_blue_cicle
                    )
                }
                2 -> {
                    showExploreButton()
                    indicator1.background = getDrawable(
                        requireContext(),
                        R.drawable.light_blue_cicle
                    )
                    indicator2.background = getDrawable(
                        requireContext(),
                        R.drawable.light_blue_cicle
                    )
                    indicator3.background = getDrawable(
                        requireContext(),
                        R.drawable.blue_circle
                    )
                }
            }
        }
    }

    private fun showExploreButton() {
        val anim =  android.view.animation.AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.slide_in_bottom
        )
        binding.btnExplore.apply {
            startAnimation(anim)
            visibility = View.VISIBLE
            setOnClickListener {
                navToDashboardScreen()
            }
        }
        /*binding.btnExplore.animate()
            .setDuration(ANIMATION_DURATION)
            .alpha(1f)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {

                    binding.btnExplore.visibility = View.VISIBLE
                }
            })*/
    }
    private fun navToDashboardScreen() {
        val deepLink = NavDeepLinkRequest.Builder
            .fromUri("remoter://dashboard".toUri())
            .build()
        findNavController().apply {
            popBackStack()
            navigate(deepLink)
        }
        /*val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.nav_graph, true)
            .setEnterAnim(R.anim.nav_default_enter_anim)
            .setEnterAnim(R.anim.nav_default_exit_anim)
            .build()*/
    }

    private fun createPagerAdapter(screenCount: Int): OnBoardingAdapter {
        return OnBoardingAdapter(requireActivity(), screenCount)
    }

    override fun onDestroyView() {
        binding.onBoardPager.unregisterOnPageChangeCallback(onBoardingPageChangeCallback)
        super.onDestroyView()
    }
}