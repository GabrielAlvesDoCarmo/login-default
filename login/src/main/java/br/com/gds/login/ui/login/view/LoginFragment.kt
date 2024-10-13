package br.com.gds.login.ui.login.view

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import br.com.gds.login.R
import br.com.gds.login.databinding.FragmentLoginBinding
import br.com.gds.login.ui.login.model.UserLogin
import br.com.gds.login.utils.extensions.edittext.EditTextState
import br.com.gds.login.ui.login.viewmodel.LoginViewModel
import br.com.gds.login.utils.extensions.edittext.applyStyle
import br.com.gds.login.utils.extensions.toastMessage
import br.com.gds.login.utils.extensions.edittext.validateEmailField
import br.com.gds.login.utils.extensions.edittext.validatePasswordField
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private val viewModel: LoginViewModel by viewModels()
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val navController by lazy { findNavController() }

    private var email = false
    private var password = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        fieldObserve()
        viewModelObserve()
    }

    private fun setupListeners() = binding.apply {
        loginBtnRegister.setOnClickListener {
            register()
            toastMessage("Clique btn register")
        }
        loginBtnResetPassword.setOnClickListener {
            resetPassword()
            toastMessage("Clique btn reset password")
        }
        loginBtnEnterApp.setOnClickListener {
            viewModel.login(
                userLogin = getUserLogin()
            )
        }
        loginCkRemember.setOnCheckedChangeListener { _, check ->
            if (check) viewModel.rememberDataUser() else viewModel.removeDataUser()
            toastMessage("Clique checkbox")
        }
        providersListeners()
    }

    private fun getUserLogin() = UserLogin(
        email = binding.loginEmailEdit.text.toString(),
        password = binding.loginPasswordEdit.text.toString(),
        isRemember = binding.loginCkRemember.isChecked
    )

    private fun resetPassword() {
        navController.navigate(R.id.action_loginFragment_to_resetPasswordFragment2)
    }

    private fun register() {
        navController.navigate(R.id.action_loginFragment_to_registerFragment)
    }

    private fun providersListeners() = binding.apply {
        googleProvider.setOnClickListener {
            toastMessage("Clique google")
        }
        appleProvider.setOnClickListener {
            toastMessage("Clique apple")
        }
        facebookProvider.setOnClickListener {
            toastMessage("Clique facebook")
        }
        gameCenterProvider.setOnClickListener {
            toastMessage("Clique game center")
        }
        githubProvider.setOnClickListener {
            toastMessage("Clique github")
        }
        microsoftProvider.setOnClickListener {
            toastMessage("Clique microsoft")
        }
        playGamesProvider.setOnClickListener {
            toastMessage("Clique play games")
        }
        twitterProvider.setOnClickListener {
            toastMessage("Clique twitter")
        }
        yahooProvider.setOnClickListener {
            toastMessage("Clique yahoo")
        }
        phoneProvider.setOnClickListener {
            toastMessage("Clique phone")
        }
        guestsProvider.setOnClickListener {
            toastMessage("Clique guests")
        }
    }

    private fun fieldObserve() {
        lifecycleScope.launch {
            binding.loginEmailEdit.validateEmailField().collect { state ->
                when (state) {
                    is EditTextState.Empty -> emailEmptyStyle()
                    is EditTextState.Invalid -> emailInvalidStyle(state)
                    is EditTextState.Valid -> emailValidStyle()
                }
                enabledButton()
            }
        }
        lifecycleScope.launch {
            binding.loginPasswordEdit.validatePasswordField().collect { state ->
                when (state) {
                    is EditTextState.Empty -> passwordEmptyStyle()
                    is EditTextState.Invalid -> passwordInvalidStyle(state)
                    is EditTextState.Valid -> passwordValidStyle()
                }
                enabledButton()
            }
        }
    }

    private fun enabledButton() = binding.loginBtnEnterApp.apply {
        isEnabled = email && password
    }

    private fun viewModelObserve() {
        lifecycleScope.launch {
            viewModel.uiState.collect { state ->
//                when (state) {
//                    is LoginUIState -> {}
//                    is LoginUIState.Loading -> {}
//                    is LoginUIState.Success -> {}
//                }
            }
        }
    }

    private fun passwordInvalidStyle(state: EditTextState.Invalid) {
        binding.loginPasswordLayout.apply {
            error = state.errorMessage
            applyStyle(R.style.TextInputLayout_Invalid)
        }
        password = false
    }

    private fun passwordValidStyle() {
        binding.loginPasswordLayout.apply {
            error = null
            applyStyle(R.style.TextInputLayout_Valid)
        }
        password = true
    }

    private fun passwordEmptyStyle() {
        binding.loginPasswordLayout.apply {
            error = null
            applyStyle(R.style.TextInputLayout_Empty)
        }
        password = false
    }

    private fun emailValidStyle() {
        binding.loginEmailLayout.apply {
            error = null
            applyStyle(R.style.TextInputLayout_Valid)
        }
        email = true
    }

    private fun emailInvalidStyle(state: EditTextState.Invalid) {
        binding.loginEmailLayout.apply {
            error = state.errorMessage
            applyStyle(R.style.TextInputLayout_Invalid)
        }
        email = false
    }

    private fun emailEmptyStyle() {
        binding.loginEmailLayout.apply {
            error = null
            applyStyle(R.style.TextInputLayout_Empty)
        }
        email = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}