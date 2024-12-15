package br.com.gds.login.feature.login.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import br.com.gds.login.LoginModuleSession
import br.com.gds.login.databinding.FragmentLoginBinding
import br.com.gds.login.feature.login.model.LoginUI
import br.com.gds.login.feature.login.model.UserLogin
import br.com.gds.login.feature.login.model.isFormValid
import br.com.gds.login.feature.login.viewmodel.LoginUIState
import br.com.gds.login.feature.login.viewmodel.LoginViewModel
import br.com.gds.login.utils.extensions.edittext.EditTextState
import br.com.gds.login.utils.extensions.navigateTo
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModel()

    private val fragmentUI by lazy {
        LoginModuleSession.loginModuleDependency?.layoutSetup?.loginFragment ?: LoginUI()
    }
    private val provider by lazy {
        LoginModuleSession.loginModuleDependency?.loginModuleCallbackProvider
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        fieldsObservers()
        uiStateObserver()
    }

    private fun uiStateObserver() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is LoginUIState.Error -> {

                }

                is LoginUIState.Loading -> {

                }

                is LoginUIState.Success -> {
                    provider?.successLogin()
                }
            }
        }
    }

    private fun fieldsObservers() {
        viewModel.loginFormState.observe(viewLifecycleOwner) { state ->
            binding.apply {
                loginEmailEdit.error = if (state.emailState is EditTextState.Invalid)
                    state.emailState.errorMessage else null

                loginPasswordEdit.error = if (state.passwordState is EditTextState.Invalid)
                    state.passwordState.errorMessage else null

                loginButtonEnterApp.isEnabled = state.isFormValid()
            }
        }
    }

    private fun setupViews() {
        defineColorsScreen()
        changeEditTextFormLogin()
        navigateScreenListeners()
        providersListeners()
        enterAppListeners()
    }

    private fun enterAppListeners() = with(binding) {
        loginButtonEnterApp.setOnClickListener {
            provider?.clickBtnLogin()
            viewModel.login(
                userLogin = UserLogin(
                    email = loginEmailEdit.text.toString(),
                    password = loginPasswordEdit.text.toString(),
                    isRemember = loginCkRemember.isChecked
                )
            )
        }
        loginBtnFingerprint.setOnClickListener {

        }
    }

    private fun navigateScreenListeners() = with(binding) {
        loginBtnResetPassword.setOnClickListener {
            navigateTo(
                LoginFragmentDirections.actionLoginFragmentToResetPasswordFragment2()
            )
        }
        loginBtnRegister.setOnClickListener {
            navigateTo(
                LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            )
        }
    }

    private fun defineColorsScreen() = with(binding) {
        loginRootContainerConstraint.setBackgroundColor(
            requireContext().getColor(fragmentUI.backgroundColor)
        )
        loginTextTitle.setTextColor(
            requireContext().getColor(fragmentUI.titleColor)
        )
    }

    private fun changeEditTextFormLogin() = with(binding) {
        loginEmailEdit.doOnTextChanged { text, _, _, _ ->
            viewModel.onEmailChanged(
                email = text.toString()
            )
        }
        loginPasswordEdit.doOnTextChanged { text, _, _, _ ->
            viewModel.onPasswordChanged(
                password = text.toString()
            )
        }
    }

    private fun providersListeners() = with(binding) {
        googleProvider.setOnClickListener { }
        appleProvider.setOnClickListener { }
        facebookProvider.setOnClickListener { }
        phoneProvider.setOnClickListener { }
        guestsProvider.setOnClickListener { }
    }


    override fun onDestroyView() {
        super.onDestroyView()
    }
}