package com.javi.common

object ValidateUsername {
    fun execute(username: String): ValidationResult {
        if (username.isBlank()) {
            return ValidationResult(
                successfull = false,
                errorMessage = R.string.username_empty
            )
        }

        if (username.length < 3) {
            return ValidationResult(
                successfull = false,
                errorMessage = R.string.username_few_characters
            )
        }

        return ValidationResult(
            successfull = true
        )
    }
}

object ValidatePassword {
    fun execute(password: String): ValidationResult {
        if (password.isBlank()) {
            return ValidationResult(
                successfull = false,
                errorMessage = R.string.password_empty
            )
        }

        if (password.length < 3) {
            return ValidationResult(
                successfull = false,
                errorMessage = R.string.password_few_characters
            )
        }

        val containsLettersAndDigits = password.any { it.isDigit() } && password.any { it.isLetter() }
        if (!containsLettersAndDigits) {
            return ValidationResult(
                successfull = false,
                errorMessage = R.string.password_no_digit_or_letter
            )
        }

        return ValidationResult(
            successfull = true
        )
    }
}

data class ValidationResult(
    val successfull: Boolean,
    val errorMessage: Int? = null
)