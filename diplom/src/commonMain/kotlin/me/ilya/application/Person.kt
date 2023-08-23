package me.ilya.application
import kotlinx.serialization.Serializable

@Serializable
open class Person(
    open var id: Long?,
    open var username: String,
    open var email: String,
    open var password: String,
    open var socialTokens: MutableMap<SocialNetwork, String> = mutableMapOf(),
    open var roles: MutableSet<String> = HashSet()
) { constructor(user: Person) : this(user.id, user.username, user.email, user.password, user.socialTokens, user.roles)

    override fun equals(other: Any?): Boolean {
        if (other !is Person) return false
        return (username != other.username && email != other.email)
    }

    override fun hashCode(): Int {
        var result = username.hashCode()
        result = 31 * result + email.hashCode()
        return result
    }

    override fun toString(): String {
        return "User(id=$id, username='$username', email='$email')"
    }
    constructor():this(null,"","","",)
}

