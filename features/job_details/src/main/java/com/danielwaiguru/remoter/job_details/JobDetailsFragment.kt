package com.danielwaiguru.remoter.job_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import androidx.viewbinding.ViewBinding
import com.danielwaiguru.remoter.job_details.databinding.FragmentJobDetailsBinding
import com.danielwaiguru.remoter.shared.BindingFragment

class JobDetailsFragment : BindingFragment<FragmentJobDetailsBinding>() {
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentJobDetailsBinding::inflate

    private val args: JobDetailsFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(requireContext(), args.name, Toast.LENGTH_SHORT).show()
    }
}