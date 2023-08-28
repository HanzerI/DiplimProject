
import kotlinx.browser.document
import react.*
import react.dom.*
import react.dom.client.createRoot
import react.dom.html.InputType
import react.dom.html.ReactHTML.br
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.form
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.label
import kotlin.js.Promise
import kotlin.js.json

external interface AnalysisFormProps : Props {
    var onSubmit: (String, Int) -> Unit
}

val AnalysisForm = FC<AnalysisFormProps> { props ->
    val (hashtag, setHashtag) = useState("")
    val (postCount, setPostCount) = useState(0)

    form {
        label {
            +"Hashtag:"
            input {
                type = InputType.text
                value = hashtag
                onChange = { event ->
                    val newValue = event.target.asDynamic().value as String
                    setHashtag(newValue)
                }

            }
        }
        br {}
        label {
            +"Post Count:"
            input {
                    type = InputType.number
                    value = postCount.toString()
                    onChange = { event ->
                        val newValue = event.target.asDynamic().value as String
                        setPostCount(newValue.toIntOrNull() ?: 0)
                    }

            }
        }
        br {}
        button {
                onClick = {
                    props.onSubmit(hashtag, postCount)
                }

            +"Analyze"
        }
    }
}
