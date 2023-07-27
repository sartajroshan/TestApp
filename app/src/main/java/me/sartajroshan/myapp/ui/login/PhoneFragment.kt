package me.sartajroshan.myapp.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import me.sartajroshan.myapp.R
import me.sartajroshan.myapp.databinding.FragmentPhoneBinding

@AndroidEntryPoint
class PhoneFragment : Fragment() {

    private lateinit var binding: FragmentPhoneBinding
    private val loginViewModel: LoginViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhoneBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnContinue.setOnClickListener {
            loginViewModel.login(
                binding.edtCode.text?.trim().toString(),
                binding.edtPhone.text?.trim().toString()
            )
        }

        loginViewModel.loading.observe(viewLifecycleOwner) {
            if (it) {
                binding.loader.visibility = View.VISIBLE
            } else {
                binding.loader.visibility = View.GONE
            }
        }

        loginViewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        loginViewModel.navigateToNextScreen.observe(viewLifecycleOwner) {
            findNavController().navigate(
                PhoneFragmentDirections.actionPhoneFragmentToOtpFragment(it)
            )
        }
    }
}