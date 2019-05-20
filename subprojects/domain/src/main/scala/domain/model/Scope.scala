package domain.model

case class Scope(
                      val firstname: Boolean,
                      val surname: Boolean,
                      val email: Boolean
                    ) {

  if(!firstname && !surname && !email) {
    throw new IllegalArgumentException("An scope that forbid everything doesnt make sense")
  }
}
