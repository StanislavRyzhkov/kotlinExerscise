package company.ryzhkov.fp.repository

import company.ryzhkov.fp.MonoF
import company.ryzhkov.fp.model.User

interface UserRepository {
    fun findById(id: Long): MonoF<User>
}
