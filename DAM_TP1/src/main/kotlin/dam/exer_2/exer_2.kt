package org.example.dam.exer_2

fun main() {

    while (true) {
        try {
            println("\nCalculator")
            println("Choose operation:")
            println("1. +  2. -  3. *  4. /")
            println("5. AND  6. OR  7. NOT")
            println("0. Exit")

            print("Option: ")
            val option = readln().toInt()

            if (option == 0) {
                println("Goodbye!")
                break
            }


            val result: Any = when (option) {

                1 -> {
                    print("Enter first value: ")
                    val a = readln().toInt()
                    print("Enter second value: ")
                    val b = readln().toInt()
                    a + b
                }

                2 -> {
                    print("Enter first value: ")
                    val a = readln().toInt()
                    print("Enter second value: ")
                    val b = readln().toInt()
                    a - b
                }

                3 -> {
                    print("Enter first value: ")
                    val a = readln().toInt()
                    print("Enter second value: ")
                    val b = readln().toInt()
                    a * b
                }

                4 -> {
                    print("Enter first value: ")
                    val a = readln().toInt()
                    print("Enter second value: ")
                    val b = readln().toInt()
                    if (b == 0) throw ArithmeticException("Division by zero!")
                    a / b
                }

                5 -> {
                    print("Enter first value(0 or 1: ")
                    val a = readln().toInt()
                    print("Enter second value (0 or 1): ")
                    val b = readln().toInt()
                    (a != 0 && b != 0)
                }

                6 -> {
                    print("Enter first value(0 or 1: ")
                    val a = readln().toInt()
                    print("Enter second value (0 or 1): ")
                    val b = readln().toInt()
                    (a != 0 || b != 0)
                }

                7 -> {
                    print("Enter first value(0 or 1): ")
                    val a = readln().toInt()
                    !(a != 0)
                }


                else -> throw IllegalArgumentException("Invalid option")
            }

            // Output formatting
            println("\nResult")
            when (result) {
                is Int -> {
                    println("Decimal: $result")
                    println("Hex: ${result.toString(16)}")
                    println("Boolean: ${result != 0}")
                }
                is Boolean -> {
                    println("Boolean: $result")
                }
            }

        } catch (e: Exception) {
            println("Error: ${e.message}")
        }
    }
}