package domain.model.code

import domain.model.Scope
import domain.model.thirdapp.ThirdappId
import domain.model.user.UserId

case class Code(
                 codeId: CodeId,
                 userId: UserId,
                 thirdappId: ThirdappId,
                 state: String,
                 scope: Scope
)