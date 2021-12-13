package com.danielwaiguru.remoter.onboarding.presentation.preferences

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.danielwaiguru.remoter.onboarding.R
import com.danielwaiguru.remoter.onboarding.databinding.FragmentJobPreferenceBinding
import com.danielwaiguru.remoter.shared.BindingFragment


class JobPreferenceFragment : BindingFragment<FragmentJobPreferenceBinding>() {
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentJobPreferenceBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}