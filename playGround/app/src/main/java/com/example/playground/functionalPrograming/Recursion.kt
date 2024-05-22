package com.example.playground.functionalPrograming
//pemanggilan fungsi berulang kali menggunakan operasi di dalam fungsi.
tailrec fun factorial(n: Int, result: Int = 1): Int {
    val newResult = n * result
    return if (n == 1) {
        newResult
    } else {
        factorial(n - 1, newResult)
    }
}