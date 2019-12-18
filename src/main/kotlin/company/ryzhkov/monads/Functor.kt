package company.ryzhkov.monads


sealed class Maybe<out T>

data class Just<out T>(val t: T) : Maybe<T>()

object Empty : Maybe<Nothing>()

interface Functor {
    fun<A, B> map(maybeA: Maybe<A>, f: (A) -> B): Maybe<B>
}

object MaybeFunctor : Functor {
    override fun <A, B> map(maybeA: Maybe<A>, f: (A) -> B): Maybe<B> = when(maybeA) {
        is Just -> Just(f(maybeA.t))
        is Empty -> Empty
    }
}

fun <A, B> Maybe<A>.map(f: (A) -> B): Maybe<B> {
    return MaybeFunctor.map(this, f)
}

fun main() {
    val maybeString: Maybe<String> = Just("Hello")
    val res = maybeString
        .map { it.toUpperCase() }
    println(res)
}

