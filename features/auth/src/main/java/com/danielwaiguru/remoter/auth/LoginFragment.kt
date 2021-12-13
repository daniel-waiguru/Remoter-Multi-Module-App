package com.danielwaiguru.remoter.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.danielwaiguru.remoter.auth.databinding.FragmentLoginBinding
import com.danielwaiguru.shared.BindingFragment

class LoginFragment : BindingFragment<FragmentLoginBinding>() {
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentLoginBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        binding.getStarted.setOnClickListener {
            findNavController().navigate(
                R.id.action_auth_to_onBoarding
            )
        }
    }
}