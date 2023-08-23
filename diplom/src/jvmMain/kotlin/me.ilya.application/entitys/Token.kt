package me.ilya.application.entitys

import jakarta.persistence.*
import me.ilya.application.SocialNetwork

@Entity
@Table(name = "tokens")
class Token(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    val token: String,
    val userTokenId: Long,
    @ManyToOne(fetch = FetchType.LAZY) // Many tokens can belong to one user
    @JoinColumn(name = "user_id") // Foreign key to user
    var user: User? = null,

    @Enumerated(EnumType.STRING)
    var socialNetwork: SocialNetwork
)