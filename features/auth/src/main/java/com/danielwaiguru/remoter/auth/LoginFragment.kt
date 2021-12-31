package com.danielwaiguru.remoter.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.danielwaiguru.remoter.auth.databinding.FragmentLoginBinding
import com.danielwaiguru.remoter.logging.Logger.timberLogger
import com.danielwaiguru.remoter.shared.BindingFragment

class LoginFragment : BindingFragment<FragmentLoginBinding>() {
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentLoginBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        timberLogger.i("Hihihi")
        setupUI()
    }

    private fun setupUI() {
        with(binding) {
            getStarted.setOnClickListener {
                findNavController().navigate(
                    R.id.action_auth_to_onBoarding
                )
            }
            val anim =  android.view.animation.AnimationUtils.loadAnimation(
                requireContext(),
                R.anim.slide_in_bottom)

            getStarted.apply {
                startAnimation(anim)
                visibility = View.VISIBLE
            }
        }
    }
}