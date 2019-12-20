package company.ryzhkov.fp.repository

import company.ryzhkov.fp.MonoF
import company.ryzhkov.fp.model.User
import reactor.core.publisher.Mono
import java.lang.RuntimeException

class UserRepositoryImpl(private val users: Map<Long, User>) : UserRepository  {
    override fun findById(id: Long): MonoF<User> {
        val user = users[id] ?: return MonoF(Mono.error(RuntimeException()))
        return MonoF(Mono.just(user))
    }
}
