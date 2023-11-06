package com.example.apikotlin.controller

import Person
import com.example.apikotlin.service.PersonService
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/customers")
class PersonController(val personService: PersonService) {

    @GetMapping
    fun getPerson(): ResponseEntity<List<Person>> {
        return ResponseEntity.ok(personService.getAll());
    }

    @PostMapping
    fun addPerson(@RequestBody person: Person): ResponseEntity<HttpStatus> {
        personService.add(person)
        return ResponseEntity(HttpStatusCode.valueOf(201))
    }

    @DeleteMapping(consumes = ["application/json"])
    fun removePerson(@RequestBody person: Person): ResponseEntity<String> {
        val res = personService.remove(person)
        if (res) {
            return ResponseEntity(HttpStatusCode.valueOf(202))
        }
        return ResponseEntity("Cannot delete the customer",HttpStatusCode.valueOf(400))
    }

    @GetMapping("greater-than/{age}")
    fun getPersonGreaterThan(@PathVariable age: Int): ResponseEntity<List<Person>> {
        var res = personService.getGreaterThan(age)
        if (res.isEmpty()){
            return ResponseEntity(HttpStatusCode.valueOf(404))
        }
        return ResponseEntity(res,HttpStatusCode.valueOf(300))
    }

}