package company.ryzhkov.monads

interface Functor<out A> {
    fun <B> map(f: (A) -> B): Functor<B>
}

sealed class Option<out A> : Functor<A> {
    override fun <B> map(f: (A) -> B): Option<B> = when(val param = this) {
        is Some -> Some(f(param.value))
        is None -> None
    }
}

data class Some<A>(val value: A) : Option<A>()

object None : Option<Nothing>()

data class Foo<out T>(val t: T)

//fun <F: Foo, A, B> bla(a: A, b: F<B>): F<A> {
//
//}