package com.iloadti.testegithub.presentation.state

import org.junit.Assert.assertNotNull
import org.junit.Test

class LoadStateTest {

    @Test
    fun `Assert ShowLoading`() {
        assertNotNull(LoadState.ShowLoading)
    }

    @Test
    fun `Assert HideLoading`() {
        assertNotNull(LoadState.HideLoading)
    }
}