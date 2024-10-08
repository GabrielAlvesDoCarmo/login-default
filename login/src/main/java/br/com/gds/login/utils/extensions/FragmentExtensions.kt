package br.com.gds.login.utils.extensions

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.toastMessage(message: String){
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}