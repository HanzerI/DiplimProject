
enum class Tonality {
    POSITIVE,
    NEGATIVE,
    NEUTRAL;
    companion object {
        @JvmStatic
        fun String.toTonality(): Tonality {
            return when (this) {
                "positive" -> POSITIVE
                "negative" -> NEGATIVE
                "neutral" -> NEUTRAL
                else -> throw IllegalArgumentException("Unknown tonality: $this")
            }
        }
    }
}