package br.com.gds.login.ui.register.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import br.com.gds.login.databinding.FragmentRegisterBinding
import br.com.gds.login.ui.register.model.UserRegister
import br.com.gds.login.ui.register.viewmodel.RegisterViewModel
import br.com.gds.login.ui.register.viewmodel.isFormValid
import br.com.gds.login.utils.extensions.edittext.EditTextState
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterFragment : Fragment() {

    companion object {
        fun newInstance() = RegisterFragment()
    }

    private lateinit var binding: FragmentRegisterBinding
    private val viewModel: RegisterViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        filedObserve()
    }

    private fun setupViews() {
        binding.apply {
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
                    userRegister = getUserRegister()
                )
            }
        }
    }

    private fun filedObserve() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.formState.collect { state ->
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
                }
            }
        }
    }

    private fun getUserRegister() = UserRegister(
        immage = binding.registerImage.toString(),
        name = binding.registerNameEdit.text.toString(),
        email = binding.loginEmailEdit.text.toString(),
        password = binding.registerPasswordEdit.text.toString(),
        confirmPassword = binding.registerConfirmPasswordEdit.text.toString()
    )
}