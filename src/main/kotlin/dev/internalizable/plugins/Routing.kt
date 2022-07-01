package dev.internalizable.plugins

import dev.internalizable.models.ContactDetails
import dev.internalizable.models.ContactResult
import dev.internalizable.utils.EmailUtilities
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.http.content.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*

fun Application.configureRouting() {

    routing {

        static("/assets") {
            resources("assets")
        }

        get("/") {
            call.respond(FreeMarkerContent("index.ftl", null))
        }

        route("forms") {
            post("contact") {
                val contactDetails = call.receive<ContactDetails>()
                val emailUtils = EmailUtilities()

                val contactResult = if (emailUtils.sendEmail(contactDetails))
                    ContactResult(200, "Successfully sent the email")
                else
                    ContactResult(300, "Could not send the email, try again later.")

                call.respond(HttpStatusCode.Accepted, contactResult)
            }
        }

    }
}
