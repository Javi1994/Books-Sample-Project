package com.javi.booksampleproject.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.javi.booksampleproject.R
import com.javi.booksampleproject.databinding.FragmentLoginSavedUserBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginSavedUserFragment : Fragment(R.layout.fragment_login_saved_user) {

    private var _binding: FragmentLoginSavedUserBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginSavedUserBinding.inflate(inflater, container, false)
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