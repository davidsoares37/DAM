package org.example.dam.exer_v1

class DigitalBook(
    title: String,
    author: String,
    publicationYear: Int,
    availableCopies: Int,
    var fileSize: Double,
    var format: String
) : Book(title, author, publicationYear, availableCopies)
{

}
