package br.com.gds.login.feature.register.automovel.view

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.gds.login.LoginModuleSession
import br.com.gds.login.R
import br.com.gds.login.feature.register.automovel.viewmodel.AutomovelRegisterViewModel

class AutomovelRegisterFragment : Fragment() {

    companion object {
        fun newInstance() = AutomovelRegisterFragment()
    }

    private val viewModel: AutomovelRegisterViewModel by viewModels()
    private val fragmentUI by lazy {
        LoginModuleSession.loginModuleDependency?.autoRegisterFragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_automovel_register, container, false)
    }
}