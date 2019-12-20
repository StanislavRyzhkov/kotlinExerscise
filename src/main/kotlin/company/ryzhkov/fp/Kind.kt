package company.ryzhkov.fp

import company.ryzhkov.fp.model.User

interface Kind<out F, out A>

interface Functor<F> {
    fun <A, B> Kind<F, A>.map(f: (A) -> B): Kind<F, B>
}

fun <F> Functor<F>.inc(fa: Kind<F, Int>): Kind<F, Int> = fa.map { it + 1 }

fun<F> Functor<F>.toUpperCase(fa: Kind<F, String>): Kind<F, String> = fa.map { it.toUpperCase() }

fun<F> Functor<F>.toShortName(fa: Kind<F, User>): Kind<F, String> = fa.map {
    val (_, firstName, secondName) = it
    val letter = "${firstName.substring(0, 1)}."
    "$letter $secondName"
}
