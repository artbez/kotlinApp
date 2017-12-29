package utils

import org.w3c.fetch.RequestCredentials
import org.w3c.fetch.RequestInit
import kotlin.browser.window
import kotlin.coroutines.experimental.*
import kotlin.js.Promise
import kotlin.js.json

suspend fun <T> Promise<T>.await() = suspendCoroutine<T> { cont ->
    then({ value -> cont.resume(value) },
            { exception -> cont.resumeWithException(exception) })
}

fun <T> async(block: suspend () -> T): Promise<T> = Promise<T> { resolve, reject ->
    block.startCoroutine(object : Continuation<T> {
        override val context: CoroutineContext get() = EmptyCoroutineContext
        override fun resume(value: T) {
            resolve(value)
        }

        override fun resumeWithException(exception: Throwable) {
            reject(exception)
        }
    })
}

fun launch(block: suspend () -> Unit) {
    async(block).catch { exception -> console.log("Failed with $exception") }
}

suspend fun <T> getAndParseResult(url: String, body: dynamic, parse: (dynamic) -> T): T =
        requestAndParseResult("GET", url, body, parse)

suspend fun <T> requestAndParseResult(method: String, url: String, body: dynamic, parse: (dynamic) -> T): T {
    val response = window.fetch(url, object : RequestInit {
        override var method: String? = method
        override var body: dynamic = body
        override var credentials: RequestCredentials? = "same-origin".asDynamic()
        override var headers: dynamic = json("Accept" to "application/json", "Content-Type" to "application/json;charset=UTF-8")
    }).await()
    return parse(response.json().await())
}