package dev.internalizable.models

import kotlinx.serialization.Serializable

@Serializable
data class ContactResult(val id: Int, val result: String)