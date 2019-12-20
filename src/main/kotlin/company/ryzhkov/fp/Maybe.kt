package company.ryzhkov.fp

import company.ryzhkov.monads.None
import java.util.function.Consumer

class ForMaybe private constructor() { companion object }

typealias MaybeOf<A> = Kind<ForMaybe, A>

sealed class Maybe<out A> : MaybeOf<A> {
    class Just<out A>(val value: A) : Maybe<A>()

    object Nil : Maybe<Nothing>()

    companion object {
        fun <A> just(a: A): Maybe<A> = Just(a)
        fun <A> empty(): Maybe<A> = Nil
        operator fun <A> invoke(a: A): Maybe<A> = Just(a)
    }

    fun<B> map(f: (A) -> B): Maybe<B> = when (this) {
        is Just -> Just(f(value)).fix()
        is Nil -> Nil
    }

    fun subscribe(consumer: Consumer<in A>) {
        when(this) {
            is Just -> consumer.accept(this.value)
            is Nil -> print("EMPTY")
        }
    }

    override fun toString(): String = when(this) {
        is Just -> "Just(${this.value})"
        is Nil -> "Nil"
    }
}

@Suppress("UNCHECKED_CAST", "NOTHING_TO_INLINE")
inline fun <A> MaybeOf<A>.fix(): Maybe<A> = this as Maybe<A>

interface MaybeFunctor : Functor<ForMaybe> {
    override fun <A, B> MaybeOf<A>.map(f: (A) -> B): Maybe<B> = fix().map(f)
}

fun Maybe.Companion.functor(): MaybeFunctor = object : MaybeFunctor {}
