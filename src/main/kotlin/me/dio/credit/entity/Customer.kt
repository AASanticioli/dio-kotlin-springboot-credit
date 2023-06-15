package me.dio.credit.entity

import jakarta.persistence.*
import org.hibernate.Hibernate

@Entity
@Table(name = "customers")
data class Customer(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val firstName: String,

    @Column(nullable = false)
    val lastName: String,

    @Column(nullable = false, unique = true)
    val cpf: String,

    @Column(nullable = false, unique = true)
    val email: String,

    @Column(nullable = false, unique = true)
    val income: Double = 0.0,

    @Column(nullable = false)
    val password: String,

    @Column(nullable = false) @Embedded
    val address: Address,

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE, CascadeType.PERSIST], mappedBy = "customer")
    val credits: List<Credit> = mutableListOf()
) {
    constructor(id: Long): this(
        id = id,
        firstName = "",
        lastName = "",
        cpf = "",
        email = "",
        password = "",
        address = Address(zipCode = "", street = ""),
    )
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Customer

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id )"
    }
}
