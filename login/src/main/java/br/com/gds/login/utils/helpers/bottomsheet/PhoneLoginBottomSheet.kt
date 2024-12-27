package br.com.gds.login.utils.helpers.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import br.com.gds.login.databinding.PhoneLoginBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PhoneLoginBottomSheet(
    private val onClickSendPhone: (String) -> Unit
) : BottomSheetDialogFragment() {

    private lateinit var binding: PhoneLoginBottomSheetBinding
    private val editTexts = mutableListOf<EditText>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PhoneLoginBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editTexts.addAll(
            listOf(
                binding.editTextDigit1,
                binding.editTextDigit2,
                binding.editTextDigit3,
                binding.editTextDigit4,
                binding.editTextDigit5,
                binding.editTextDigit6,
                binding.editTextDigit7,
                binding.editTextDigit8,
                binding.editTextDigit9,
                binding.editTextDigit10,
                binding.editTextDigit11
            )
        )

        editTexts.forEachIndexed { index, editText ->
            editText.apply {
                doOnTextChanged { text, _, _, _ ->
                    if (text?.isNotEmpty() == true && index < editTexts.size - 1) {
                        editTexts[index + 1].requestFocus()
                    }
                }

//                doAfterTextChanged {
//                    if (it?.isEmpty() == true && index > 0) {
//                        editTexts[index - 1].requestFocus()
//                        editTexts[index - 1].setSelection(editTexts[index - 1].text.toString().length)
//                    }
//                }

            }
        }


        binding.buttonSendCode.setOnClickListener {
            val phoneNumber = getPhoneNumber()

            onClickSendPhone.invoke(phoneNumber)
            // TODO: Enviar o código de verificação para o número de telefone
            // Utilize o ViewModel, Use Case e Repository para realizar a verificação
            // Exemplo: viewModel.verifyPhoneNumber(phoneNumber, requireActivity())
        }
    }

    private fun getPhoneNumber(): String {
        return editTexts.joinToString("") { it.text.toString() }
    }
}