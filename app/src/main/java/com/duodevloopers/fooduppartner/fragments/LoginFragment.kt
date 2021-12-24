package com.duodevloopers.fooduppartner.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.duodevloopers.fooduppartner.R
import com.duodevloopers.fooduppartner.bottomsheets.OTPInputBottomSheet
import com.duodevloopers.fooduppartner.callbacks.OTPInputBottomSheetInteractionCallback
import com.duodevloopers.fooduppartner.databinding.FragmentLoginBinding
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class LoginFragment : Fragment(), OTPInputBottomSheetInteractionCallback {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var verificationCode: String
    private lateinit var otpInputBottomSheet: OTPInputBottomSheet

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()
        otpInputBottomSheet = OTPInputBottomSheet(requireContext(), this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cirLoginButton.setOnClickListener {

            if (binding.editTextPhone.text.isNotEmpty()) {

                binding.animationView.visibility = View.VISIBLE

                val number = "+88" + binding.editTextPhone.text.toString()
                sendVerificationCode(number)

            }

        }
    }

    private fun sendVerificationCode(number: String) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            number,
            60,
            TimeUnit.SECONDS,
            requireActivity(),
            callback
        )
    }

    private val callback = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(p0: PhoneAuthCredential) {
            TODO("Not yet implemented")
        }

        override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
            super.onCodeSent(p0, p1)
            binding.animationView.visibility = View.GONE
            verificationCode = p0
            otpInputBottomSheet.showBottomSheet()
        }

        override fun onVerificationFailed(p0: FirebaseException) {
            binding.animationView.visibility = View.GONE
            Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onNumberSubmitted(number: String) {
        binding.animationView.visibility = View.VISIBLE
        val credential = PhoneAuthProvider.getCredential(verificationCode, number)
        auth.signInWithCredential(credential)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    binding.animationView.visibility = View.GONE
                    val user = auth.currentUser
                    Toast.makeText(requireContext(), "Login Successful", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_loginFragment_to_selectTypeFragment)
                } else {
                    binding.animationView.visibility = View.GONE
                    Toast.makeText(requireContext(), "Login Unsuccessful", Toast.LENGTH_SHORT)
                        .show()
                }
            }
    }
}