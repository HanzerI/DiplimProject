package com.example.demo.entitys

import com.example.demo.sotial.SocialNetwork
import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "app_user")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(nullable = false)
  //  @get:JvmName("getName")
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
  //  @get:JvmName("getPas")
    var password: String,
    @ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(name = "user_social_tokens", joinColumns = [JoinColumn(name = "user_id")])
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "social_network")
    @Column(name = "token")
    val socialTokens: MutableMap<SocialNetwork, String> = mutableMapOf(),
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = [JoinColumn(name = "user_id")])
    @Column(name = "role")
    val roles: MutableSet<String> = HashSet()
) { constructor(user: User) : this(user.id, user.username, user.email, user.password, user.socialTokens, user.roles) }
