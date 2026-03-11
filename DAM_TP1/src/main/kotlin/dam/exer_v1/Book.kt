package org.example.dam.exer_v1

open class Book(
    public var title: String,
    private var author: String,
    private var publicationYear: Int,
    var availableCopies: Int
) {

    init {
        println("Book created: \"$title\" by $author")
    }

    public fun getPublicationYear(publicationYear: Int): String {
        if(publicationYear < 1980){
            return "Classic"
        }
        else if (publicationYear in 1980..<2010) {
            return "Modern"
        }
        else{return "Contemporary"}
    }

    fun setAvailableCopies(copies: Int) {
        if (copies < 0) {
            println("Warning: cannot set a negative number of available copies")
            return
        }

        if (copies == 0) {
            println("Warning: Book is now out of stock!")
        }

        availableCopies = copies
    }
}