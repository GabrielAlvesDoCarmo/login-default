package br.com.gds.login.feature.resetpassword.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import br.com.gds.login.LoginModuleSession
import br.com.gds.login.R
import br.com.gds.login.feature.resetpassword.model.ResetPasswordUI
import br.com.gds.login.feature.resetpassword.viewmodel.ResetPasswordViewModel

class ResetPasswordFragment : Fragment() {
    private val viewModel: ResetPasswordViewModel by viewModels()
    private val fragmentUI by lazy {
      LoginModuleSession
          .loginModuleDependency
          ?.layoutSetup
          ?.resetPasswordFragment
          ?: ResetPasswordUI()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_reset_password, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = ResetPasswordFragment()
    }

}