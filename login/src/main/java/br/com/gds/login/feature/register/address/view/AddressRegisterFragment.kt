package br.com.gds.login.feature.register.address.view

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.gds.login.LoginModuleSession
import br.com.gds.login.R
import br.com.gds.login.databinding.FragmentAddressRegisterBinding
import br.com.gds.login.feature.register.address.model.AddressRegisterUI
import br.com.gds.login.feature.register.address.viewmodel.AddressRegisterViewModel
import br.com.gds.login.provider.LoginModuleCallbackProvider
import br.com.gds.login.utils.commons.LayoutSetup
import br.com.gds.login.utils.extensions.changeColorImageButton
import br.com.gds.login.utils.extensions.createCustomShapeDrawable
import br.com.gds.login.utils.extensions.edittext.setupEdit
import br.com.gds.login.utils.extensions.toastMessage

class AddressRegisterFragment : Fragment() {

    companion object {
        fun newInstance() = AddressRegisterFragment()
    }

    private lateinit var binding: FragmentAddressRegisterBinding

    private val viewModel: AddressRegisterViewModel by viewModels()
    private val fragmentUI by lazy {
        LoginModuleSession.loginModuleDependency?.layoutSetup?.addressRegisterFragment ?: AddressRegisterUI()
    }

    private val provider: LoginModuleCallbackProvider? by lazy {
        LoginModuleSession.loginModuleDependency?.loginModuleCallbackProvider
    }

    private val defaultColors by lazy {
        LoginModuleSession
            .loginModuleDependency
            ?.layoutSetup
            ?.layoutDefault
            ?.defaultColors
            ?: LayoutSetup().layoutDefault.defaultColors
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentAddressRegisterBinding.inflate(inflater).also {
        binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupScreenAppearance()
    }

    private fun setupScreenAppearance() = with(binding) {
        containerRegisterAddressRoot.setBackgroundColor(requireContext().getColor(defaultColors.primaryColor))
        registerAddressTextTitle.setTextColor(requireContext().getColor(defaultColors.secondaryColor))

        registerAddressButtonBack.apply {
            setOnClickListener {
                provider?.backButtonRegisterAddress()
            }
            changeColorImageButton(
                isBorder = true,
                iconDrawable = R.drawable.arrow_back_24,
                defaultColors = defaultColors
            )
        }
        registerAddressCepEdit.setupEdit(requireContext(),defaultColors,R.drawable.help_24)
        registerAddressNumberEdit.setupEdit(requireContext(),defaultColors,R.drawable.help_24)
        registerAddressEdit.setupEdit(requireContext(),defaultColors,R.drawable.help_24)
        registerAddressComplementEdit.setupEdit(requireContext(),defaultColors,R.drawable.help_24)
        registerAddressReferenceEdit.setupEdit(requireContext(),defaultColors,R.drawable.help_24)
        registerAddressDistrictEdit.setupEdit(requireContext(),defaultColors,R.drawable.help_24)
        registerAddreessCityEdit.setupEdit(requireContext(),defaultColors,R.drawable.help_24)
        registerAddreessStateEdit.setupEdit(requireContext(),defaultColors,R.drawable.help_24)

        registerAddressButton.apply {
            background = requireContext().createCustomShapeDrawable(defaultColors)
            setTextColor(requireContext().getColor(defaultColors.secondaryColor))
            setOnClickListener {
                toastMessage("CLICOU")
            }
        }
    }


}