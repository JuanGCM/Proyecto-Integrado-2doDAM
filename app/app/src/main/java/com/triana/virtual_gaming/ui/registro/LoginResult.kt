package com.triana.virtual_gaming.ui.registro

import com.triana.virtual_gaming.ui.registro.LoggedInUserView

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult(
        val success: LoggedInUserView? = null,
        val error: Int? = null
)