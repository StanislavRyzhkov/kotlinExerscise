package company.ryzhkov.fp

import company.ryzhkov.fp.model.User
import company.ryzhkov.fp.repository.UserRepository
import company.ryzhkov.fp.repository.UserRepositoryImpl
import company.ryzhkov.monads.Option
import reactor.core.publisher.Mono
import java.util.function.Consumer

fun <A> foo(functor: Functor<A>, kind: Kind<A, User>): Kind<A, String> = functor.toShortName(kind)

fun main() {

    val users = mapOf(
        1L to User(1L, "Bob", "Smith"),
        2L to User(2L, "Alice", "Brown")
    )
    val userRepository: UserRepository = UserRepositoryImpl(users)

    val u1: MonoF<User> = userRepository.findById(11L)
    val u2: Maybe<User> = Maybe(User(3L, "John", "May"))

    val shortName = foo(Maybe.functor(), u2).fix()
    shortName.subscribe(Consumer { println(it) })
}
