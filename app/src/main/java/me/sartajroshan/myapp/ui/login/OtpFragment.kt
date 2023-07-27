package me.sartajroshan.myapp.ui.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import me.sartajroshan.myapp.R
import me.sartajroshan.myapp.databinding.FragmentOtpBinding

@AndroidEntryPoint
class OtpFragment : Fragment() {

    private lateinit var binding: FragmentOtpBinding
    private val loginViewModel: LoginViewModel by viewModels()
    private val args by navArgs<OtpFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOtpBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnContinue.setOnClickListener {
            loginViewModel.verifyOtp(args.phoneNumber, binding.edtCode.text?.trim().toString())
        }
        binding.getOtp.text = args.phoneNumber

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
                OtpFragmentDirections.actionOtpFragmentToNotesFragment()
            )
        }
    }
}