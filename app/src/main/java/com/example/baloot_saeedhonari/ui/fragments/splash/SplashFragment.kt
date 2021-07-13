package com.example.baloot_saeedhonari.ui.fragments.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.baloot_saeedhonari.R
import kotlinx.coroutines.*

class SplashFragment : Fragment() {

    companion object {
        fun newInstance() = SplashFragment()
    }

    private lateinit var viewModel: SplashViewModel
    val activityScope = CoroutineScope(Dispatchers.Main)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.splash_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activityScope.launch {
            delay(1000)

            NavHostFragment.findNavController(this@SplashFragment)
                .navigate(R.id.action_splashFragment_to_articleFragment2)

        }
    }

    override fun onPause() {
        activityScope.cancel()
        super.onPause()
    }
}