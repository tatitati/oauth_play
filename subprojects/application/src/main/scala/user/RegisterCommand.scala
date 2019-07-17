package application.user

import domain.services.UserFactory
import infrastructure.persistence.user.UserRepository

class RegisterCommand(
          val userRepository: UserRepository
      ) {

      def exec(request: RegisterRequest) = {
            val noPersistedUser = UserFactory.create(
                  withEmail = request.email,
                  withFirstname = request.firstname,
                  withPassword = request.password,
                  withSurname = request.surname,
                  withUsername = request.username
            )

            userRepository.create(noPersistedUser)
      }
}
