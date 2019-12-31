package domain.model

case class Scope(
                      firstname: Boolean,
                      surname: Boolean,
                      email: Boolean
                    ) {

  if(!firstname && !surname && !email) {
    throw new IllegalArgumentException("An scope that forbid everything doesnt make sense")
  }
}
