package org.example.dam.exer_v1

class Library {

    private val books = mutableListOf<Book>()

    fun addBook(book: Book) {
        books.add(book)
        println("Added $book to the library")
    }

    fun borrowBook(title: String) {
        val book = books.find { it.title.equals(title, ignoreCase = true) }

        if (book == null) {
            println("Book '$title' not found.")
            return
        }

        if (book.availableCopies > 0) {
            book.availableCopies--
            println("You borrowed '$title'.")
        } else {
            println("No copies of '$title' are available.")
        }
    }

    fun returnBook(title: String) {
        val book = books.find { it.title.equals(title, ignoreCase = true) }

        if (book == null) {
            println("Book '$title' not found.")
        } else{

            println("You returned '$title' as '$book'")
        }



    }
}