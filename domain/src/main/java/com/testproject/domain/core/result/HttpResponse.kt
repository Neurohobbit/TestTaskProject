package com.testproject.domain.core.result

interface HttpResponse {

    val statusCode: Int

    val statusMessage: String?

    val url: String?
}
