package org.example.dam.exer_v1

class Library(string: String) {

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

    fun showBooks() {
        if (books.isEmpty()) {
            println("No books in the library.")
            return
        }

        for (book in books) {
            println("${book.title} by ${book.author} (${book.publicationYear}) - Copies: ${book.availableCopies}")
        }
    }

    fun searchByAuthor(author: String) {
        val results = books.filter { it.author.equals(author, ignoreCase = true) }

        if (results.isEmpty()) {
            println("No books found by $author.")
            return
        }

        println("Books by $author:")
        for (book in results) {
            println(book.title)
        }
    }
}