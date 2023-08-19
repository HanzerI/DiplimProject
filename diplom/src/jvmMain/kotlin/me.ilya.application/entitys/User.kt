package me.ilya.application.entitys


import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import me.ilya.application.sotial.SocialNetwork

@Entity
@Table(name = "app_user")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    var username: String,
    @Column(nullable = false)
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    var email: String,
    @Column(nullable = false)
    @NotBlank(message = "Password is required")
    @Pattern(
        regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}\$",
        message = "Password must be at least 8 characters long and contain at least one letter and one number"
    )
    var password: String,
    @ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(name = "user_social_tokens", joinColumns = [JoinColumn(name = "user_id")])
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "social_network")
    @Column(name = "token")
    var socialTokens: MutableMap<SocialNetwork, String> = mutableMapOf(),
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = [JoinColumn(name = "user_id")])
    @Column(name = "role")
    var roles: MutableSet<String> = HashSet()
) { constructor(user: User) : this(user.id, user.username, user.email, user.password, user.socialTokens, user.roles)

    override fun equals(other: Any?): Boolean {
        if (other !is User) return false
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

}

