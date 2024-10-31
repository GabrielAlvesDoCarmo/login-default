package br.com.gds.login.feature.register.address.view

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.gds.login.LoginModuleSession
import br.com.gds.login.R
import br.com.gds.login.feature.register.address.viewmodel.AddressRegisterViewModel

class AddressRegisterFragment : Fragment() {

    companion object {
        fun newInstance() = AddressRegisterFragment()
    }

    private val viewModel: AddressRegisterViewModel by viewModels()
    private val fragmentUI by lazy {
        LoginModuleSession.loginModuleDependency?.addressRegisterFragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_address_register, container, false)
    }
}