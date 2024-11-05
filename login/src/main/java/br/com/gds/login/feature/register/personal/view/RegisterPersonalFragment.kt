package br.com.gds.login.feature.register.personal.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import br.com.gds.login.LoginModuleSession
import br.com.gds.login.databinding.FragmentRegisterPersonalBinding
import br.com.gds.login.feature.register.personal.model.RegisterPersonalUI
import br.com.gds.login.feature.register.personal.model.RegisterPersonalUser
import br.com.gds.login.feature.register.personal.viewmodel.RegisterPersonalState
import br.com.gds.login.feature.register.personal.viewmodel.RegisterPersonalViewModel
import br.com.gds.login.utils.commons.isFormValid
import br.com.gds.login.utils.extensions.appendMessageToFile
import br.com.gds.login.utils.extensions.edittext.EditTextState
import br.com.gds.login.utils.extensions.toastMessage
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterPersonalFragment : Fragment() {

    private lateinit var binding: FragmentRegisterPersonalBinding
    private val viewModel: RegisterPersonalViewModel by viewModel()

    private val fragmentUI by lazy {
        LoginModuleSession.loginModuleDependency?.layoutSetup?.registerFragment ?: RegisterPersonalUI()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterPersonalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        fieldObserver()
        uiStateObserver()
        appendMessageToFile(this.requireContext(), "TELA DE REGISTRO DISPONIVEL")
    }

    private fun uiStateObserver() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is RegisterPersonalState.Error -> {
                    binding.progressBar.isVisible = false
                    toastMessage(state.message)
                }

                is RegisterPersonalState.Loading -> {
                    binding.progressBar.isVisible = true
                }

                is RegisterPersonalState.Success ->{
                    LoginModuleSession
                        .loginModuleDependency
                        ?.loginModuleCallbackProvider
                        ?.gotToLoginSuccess(requireContext())
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        appendMessageToFile(this.requireContext(), "TELA DE REGISTRO ENCERRADA")
    }

    private fun setupViews() {
        binding.apply {
            fragmentUI.apply {
                containerRegisterPersonConstraintRoot.setBackgroundColor(
                    requireContext().getColor(backgroundColor)
                )
                registerTextTitle.setTextColor(
                    requireContext().getColor(titleColor)
                )

            }
            registerNameEdit.doOnTextChanged { text, _, _, _ ->
                viewModel.onNameChanged(
                    name = text.toString()
                )
            }
            loginEmailEdit.doOnTextChanged { text, _, _, _ ->
                viewModel.onEmailChanged(
                    email = text.toString()
                )
            }
            registerPasswordEdit.doOnTextChanged { text, _, _, _ ->
                viewModel.onPasswordChanged(
                    password = text.toString()
                )
            }
            registerConfirmPasswordEdit.doOnTextChanged { text, _, _, _ ->
                viewModel.onConfirmPasswordChanged(
                    password = binding.registerPasswordEdit.text.toString(),
                    confirmPassword = text.toString()
                )
            }

            buttonRegister.setOnClickListener {
                viewModel.register(
                    registerPersonalUser = getUserRegister()
                )
            }
            ckUseNickname.isVisible = fragmentUI.enableNickname
            buttonAddress.isVisible = fragmentUI.enableButtonAddress


            ckUseNickname.setOnClickListener {
                registerNicknameLayout.isVisible = ckUseNickname.isChecked
            }

        }
    }

    private fun fieldObserver() {
        viewModel.formState.observe(viewLifecycleOwner) { state ->
            binding.apply {
                registerNameLayout.error = if (state.nameState is EditTextState.Invalid)
                    state.nameState.errorMessage else null

                loginEmailLayout.error = if (state.emailState is EditTextState.Invalid)
                    state.emailState.errorMessage else null

                registerPasswordLayout.error = if (state.passwordState is EditTextState.Invalid)
                    state.passwordState.errorMessage else null

                registerConfirmPasswordLayout.error =
                    if (state.confirmPasswordState is EditTextState.Invalid)
                        state.confirmPasswordState.errorMessage else null

                buttonRegister.isEnabled = state.isFormValid()
                buttonAddress.isEnabled = state.isFormValid()
            }
        }
    }

    private fun getUserRegister() = RegisterPersonalUser(
        name = binding.registerNameEdit.text.toString(),
        email = binding.loginEmailEdit.text.toString(),
        password = binding.registerPasswordEdit.text.toString(),
        confirmPassword = binding.registerConfirmPasswordEdit.text.toString()
    )
}