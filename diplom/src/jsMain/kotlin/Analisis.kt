
import dom.html.HTMLInputElement
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import org.w3c.fetch.RequestInit
import org.w3c.fetch.Response
import react.create
import react.dom.client.createRoot
import kotlin.js.Promise
import kotlin.js.*


suspend fun postToBackend(data: Map<String, Any>): Flow<Result<String>> = flow {
    val url = "http://localhost:8081/analysis" // Замените на ваш реальный бэкэнд URL
    val requestInit = RequestInit(
        method = "POST",
        headers = jsonHeaders(),
        body = JSON.stringify(data)
    )

    val response = window.fetch(url, requestInit).await()

    if (response.status.toInt() == 200) {
        val text = response.text().await()
        emit(Result.success(text))
    } else {
        emit(Result.failure(Throwable("Failed to send data to backend")))
    }
}.catch { emit(Result.failure(it)) }

fun jsonHeaders(): dynamic {
    return json("Content-Type" to "application/json")
}

suspend fun Response.text(): Promise<String> = this.asDynamic().text().unsafeCast<Promise<String>>()
//
//fun main() {
//        val rootElement = document.getElementById("root")
//        if (rootElement != null) {
//            val root = createRoot(rootElement)
//            root.render(AnalysisForm.create())
//        }
//        AnalysisForm {
//            onSubmit { hashtag, postCount ->
//                // Отправка данных на сервер и обработка результата
//                val dataToSend = json(
//                    "hashtag" to hashtag,
//                    "postCount" to postCount
//                )
//
//                val requestInit = json(
//                    "method" to "POST",
//                    "headers" to json("Content-Type" to "application/json"),
//                    "body" to JSON.stringify(dataToSend)
//                )
//
//                window.fetch("http://localhost:8081/analysis", requestInit)
//                    .then { response ->
//                        if (response.ok) {
//                            response.text()
//                        } else {
//                            Promise.reject(Error("Failed to analyze hashtag"))
//                        }
//                    }
//                    .then { result ->
//                        console.log("Analysis result:", result)
//                        // Дополнительная обработка результата
//                    }
//                    .catch { error ->
//                        console.error("Error:", error)
//                    }
//            }
//        }
//    })
//}
//}

@OptIn(DelicateCoroutinesApi::class)
fun main() {
    val rootElement = document.getElementById("root")
    if (rootElement != null) {
        val root = createRoot(rootElement)
        root.render(AnalysisForm.create())
    }

    val analyzeButton = document.getElementById("analyzeButton")
    analyzeButton?.addEventListener("click", {
        val hashtag = (document.getElementById("hashtag") as? HTMLInputElement)?.value ?: ""
        val postCount = (document.getElementById("postCount") as? HTMLInputElement)?.value?.toIntOrNull() ?: 0

        val dataToSend = mapOf(
            "hashtag" to hashtag,
            "postCount" to postCount
        )

        GlobalScope.launch {
            val result = postToBackend(dataToSend).firstOrNull()
            if (result != null) {
                if (result.isSuccess) {
                    console.log("Response from backend:", result.getOrNull())
                } else {
                    console.error("Error:", result.exceptionOrNull())
                }
            }
        }
    })
}
