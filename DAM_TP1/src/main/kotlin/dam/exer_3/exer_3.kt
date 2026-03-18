package org.example.dam.exer_3

fun main() {
    //.map, .list, etc, tem que ser tudo feita na mesma generateSequence, porque se se faz à parte cria sempre uma nova lista
    val test = generateSequence(100.0) { it * 0.6 }
        .takeWhile { it >= 1 }
        .map { "%.2f".format(it) }
        .toList()

    println(test)
}