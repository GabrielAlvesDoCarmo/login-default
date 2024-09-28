package br.com.gds.login.customview.logoView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import br.com.gds.login.R
import br.com.gds.login.databinding.IconProviderCustomViewBinding

class LogoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var binding: IconProviderCustomViewBinding = IconProviderCustomViewBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    init {
        setupAttributes(attrs)
    }

    private fun setupAttributes(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.LogoView)
        try {
            val logoText = typedArray.getString(R.styleable.LogoView_logoText)
            val logoImage = typedArray.getDrawable(R.styleable.LogoView_logoImage)

            binding.textLogo.text = logoText
            binding.imageLogo.setImageDrawable(logoImage)
        } finally {
            typedArray.recycle()
        }
    }

}