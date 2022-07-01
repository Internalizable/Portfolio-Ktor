package dev.internalizable.utils

import dev.internalizable.models.ContactDetails
import org.apache.commons.mail.DefaultAuthenticator
import org.apache.commons.mail.SimpleEmail

class EmailUtilities() {

    val email = SimpleEmail()

    init {
        email.hostName = "smtp.googlemail.com"
        email.setSmtpPort(465)
        email.setAuthenticator(DefaultAuthenticator("internalizable@gmail.com", "wvcechbmjymknuxm"))
        email.isSSLOnConnect = true
    }

    fun sendEmail(details: ContactDetails): Boolean {
        email.setFrom(details.email)
        email.subject = details.subject
        email.setMsg(details.message)
        email.addTo("internalizable@gmail.com")

        return try {
            email.send()
            true
        } catch (e: Exception) {
            false
        }
    }
}