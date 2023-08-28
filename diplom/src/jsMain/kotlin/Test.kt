//import kotlinx.browser.document
//import react.FC
//import react.Props
//import react.create
//import react.dom.client.createRoot
//import react.dom.html.ReactHTML.h1
//
//fun main() {
//    document.getElementById("root")?.let { createRoot(it).render(Welcome.create()) }
//}
//external interface WelcomeProps : Props {
//    var name: String
//}
//
//private val Welcome = FC<WelcomeProps>("Welcome") { props ->
//    h1 {
//        +"Hello, ${props.name}"
//    }
//}