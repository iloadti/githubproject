package com.iloadti.testegithub.commons

import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.iloadti.testegithub.R
import com.iloadti.testegithub.extension.setActionBar
import com.iloadti.testegithub.presentation.MainActivity


internal abstract class AbstractFragment : Fragment() {

    protected fun setupToolbar(
        @StringRes title: Int
    ) {
        setupToolbar(getString(title))
    }

    protected fun setupToolbar(
        title: String,
        toolbar: Toolbar? = view?.findViewById(R.id.toolbar),
        displayHomeAsUp: Boolean = false
    ) {
        toolbar?.apply { setTitle(title) }
        (activity as? AppCompatActivity)?.setActionBar(toolbar, displayHomeAsUp)
    }

    protected fun showLoad() {
        (activity as? MainActivity)?.showLoad()
    }

    protected fun hideLoad() {
        (activity as? MainActivity)?.hideLoad()
    }

    override fun onDestroyView() {
        hideLoad()
        super.onDestroyView()
    }
}