package company.ryzhkov.fp

import reactor.core.publisher.Mono
import java.util.function.Consumer

class ForMonoF private constructor()
typealias MonoFOf<A> = Kind<ForMonoF, A>

data class MonoF<A>(val mono: Mono<A>) : MonoFOf<A> {
    fun <B> map(f: (A) -> B): MonoF<B> = MonoF(this.mono.map(f))

    fun subscribe(consumer: Consumer<A>) {
        this.mono
//            .onErrorResume { Mono.empty() }
            .doOnError() { println("EMPTY") }
            .subscribe(consumer)
            .dispose()
    }
    companion object
}

@Suppress("UNCHECKED_CAST", "NOTHING_TO_INLINE")
inline fun <A> MonoFOf<A>.fix() = this as MonoF<A>

interface MonoFunctor : Functor<ForMonoF> {
    override fun <A, B> Kind<ForMonoF, A>.map(f: (A) -> B): Kind<ForMonoF, B> = this.fix().map(f)
}

fun MonoF.Companion.functor(): MonoFunctor = object : MonoFunctor {}
