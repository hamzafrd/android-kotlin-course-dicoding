package com.hamzafrd.springboot.model

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class UpdateProductRequest(
    @field:NotBlank
    val name: String,

    @field:NotNull
    @Min(value = 1)
    val price: Long,

    @field:NotNull
    @Min(value = 0)
    val quantity: Int
)
