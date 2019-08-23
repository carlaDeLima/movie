package com.example.carladelima.movie.app.login.model

class AutenticacaoResponse(
    var success: Boolean? = null,
    var expires_at: String = "",
    var request_token: String = "",
    var session_id: String = "",
    var status_code: Int = -1,
    var status_message: String = ""
)