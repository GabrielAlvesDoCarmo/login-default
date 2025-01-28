package br.com.gds.login.feature.base

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

abstract class BaseFragment : Fragment() {

    private val navController by lazy {
        findNavController()
    }


}