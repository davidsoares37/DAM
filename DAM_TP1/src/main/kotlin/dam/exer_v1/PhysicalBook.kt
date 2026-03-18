package org.example.dam.exer_v1

class PhysicalBook(
    title: String,
    author: String,
    publicationYear: Int,
    availableCopies: Int,
    var weight: Int,
    var hasHardover: Boolean = true,
) : Book(title, author, publicationYear, availableCopies)
{

}