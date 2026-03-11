package org.example.dam.exer_1

fun main() {


    // IntArray
    val squares = IntArray(50)
    {
        i -> (i + 1) * (i + 1)
    }

    println(squares.joinToString())

    // range and map
    val squares1 = (1..50)
        .map { it * it }
        .toIntArray()

    println(squares1.joinToString())

    // Array
    val squares2 = Array(50) { i ->
        (i + 1) * (i + 1)
    }

    println(squares2.joinToString())


}