import java.time.LocalDate

class Person(
    var name: String,
    var lastName: String,
    var age: Int,
    var birthdate: LocalDate
) {

    fun saludar() {
        println("Hola, mi nombre es $name $lastName. ¡Hola a todos!")
    }

    override fun toString(): String {
        return "Person(name='$name', lastName='$lastName', age=$age, birthdate=$birthdate)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Person

        if (name != other.name) return false
        if (lastName != other.lastName) return false
        if (age != other.age) return false
        if (birthdate != other.birthdate) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + lastName.hashCode()
        result = 31 * result + age
        result = 31 * result + birthdate.hashCode()
        return result
    }

}