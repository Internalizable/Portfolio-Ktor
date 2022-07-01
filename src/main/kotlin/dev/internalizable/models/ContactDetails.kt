package dev.internalizable.models

import kotlinx.serialization.Serializable

@Serializable
data class ContactDetails(val name: String, val email: String, val subject: String, val message: String)