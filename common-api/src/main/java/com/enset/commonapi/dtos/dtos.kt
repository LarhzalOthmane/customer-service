package com.enset.commonapi.dtos

data class CreateCustomerRequestDTO(
    val name: String,
    val email: String
)

data class UpdateCustomerRequestDTO(
    val name: String,
    val email: String
)

data class CustomerDTO(
    val name: String,
    val email: String
)