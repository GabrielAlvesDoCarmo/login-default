package br.com.gds.login.utils.commons

import android.os.Parcelable
import br.com.gds.login.feature.container.model.LayoutDefault
import br.com.gds.login.feature.login.model.LoginUI
import br.com.gds.login.feature.register.address.model.AddressRegisterUI
import br.com.gds.login.feature.register.automovel.model.AutomovelRegisterUI
import br.com.gds.login.feature.register.personal.model.RegisterPersonalUI
import br.com.gds.login.feature.resetpassword.model.ResetPasswordUI
import kotlinx.parcelize.Parcelize

@Parcelize
data class LayoutSetup(
    val layoutDefault: LayoutDefault = LayoutDefault(),
    val loginFragment: LoginUI = LoginUI(),
    val registerFragment: RegisterPersonalUI = RegisterPersonalUI(),
    val resetPasswordFragment: ResetPasswordUI = ResetPasswordUI(),
    val addressRegisterFragment: AddressRegisterUI = AddressRegisterUI(),
    val autoRegisterFragment: AutomovelRegisterUI = AutomovelRegisterUI(),
) : Parcelable
