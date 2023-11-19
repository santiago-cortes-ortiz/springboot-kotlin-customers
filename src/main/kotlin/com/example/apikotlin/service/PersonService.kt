package com.example.apikotlin.service

import Person
import com.example.apikotlin.exception.PersonException
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class PersonService {

    var people: MutableList<Person> = mutableListOf(
        Person("John", "Doe", 30, LocalDate.of(1993, 1, 15)),
        Person("Alice", "Smith", 25, LocalDate.of(1996, 5, 20)),
        Person("Bob", "Johnson", 28, LocalDate.of(1993, 11, 10)),
        Person("Jeff", "Bezzos", 30, LocalDate.of(1991, 11, 10))
    )

    fun getAll(): List<Person> {
        notFound(people)
        return people;
    }

    fun add(person: Person) {
        person.age = person.birthdate.until(LocalDate.now()).years
        people.add(person)
    }

    fun remove(person: Person): Boolean {
        if (!people.contains(person)) {
            throw PersonException("Person not found")
        }
        return people.remove(person)
    }

    fun getGreaterThan(age: Int): List<Person> {
        notFound(people)
        return people.filter { it.age > age }
    }

    fun notFound(people: List<Person>) {
        if (people.isEmpty()) {
            throw PersonException("No people found")
        }
    }

}