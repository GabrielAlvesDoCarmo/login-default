package br.com.gds.login

import br.com.gds.login.feature.login.model.LoginUI
import br.com.gds.login.feature.register.address.model.AddressRegisterUI
import br.com.gds.login.feature.register.automovel.model.AutomovelRegisterUI
import br.com.gds.login.feature.register.personal.model.RegisterPersonalUI
import br.com.gds.login.feature.resetpassword.model.ResetPasswordUI

data class LoginModuleDependency(
    val registerFragment: RegisterPersonalUI,
    val addressRegisterFragment: AddressRegisterUI,
    val autoRegisterFragment: AutomovelRegisterUI,
    val loginFragment: LoginUI,
    val resetPasswordFragment: ResetPasswordUI
)
