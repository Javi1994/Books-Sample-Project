package com.javi.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.javi.presentation.R
import com.javi.presentation.databinding.FragmentHomeAllUsersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeAllUsers : Fragment(R.layout.fragment_home_all_users) {

    private var _binding: FragmentHomeAllUsersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeAllUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}