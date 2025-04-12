package com.example.eslatma.screen.splash
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.eslatma.R
import com.example.eslatma.databinding.FragmentSplashBinding

/**
 * Creator: Javohir Oromov
 * Project: Eslatma
 * Date: 06/04/25
 * Javohir's MacBook Air
 */
class SplashFragment: Fragment(R.layout.fragment_splash) {

    private val binding: FragmentSplashBinding by viewBinding(FragmentSplashBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        object: CountDownTimer(1500,3000){
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToMainFragment())
            }
        }.start()
    }
}