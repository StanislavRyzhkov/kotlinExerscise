package company.ryzhkov.monads

interface Applicative<out A> : Functor<A> {

    fun <B> ap(box: Functor<(A) -> B>): Functor<B>

    fun <B> pure(value: B): Functor<B>

    override fun <B> map(f: (A) -> B): Functor<B> = ap(pure(f))
}
