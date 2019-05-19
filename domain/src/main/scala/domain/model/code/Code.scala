package domain.model.code

import domain.model.Scope
import domain.model.thirdapp.ThirdappId
import domain.model.code.CodeId
import domain.model.user.UserId

case class Code(
                 val codeId: CodeId,
                 val userId: UserId,
                 val thirdappId: ThirdappId,
                 val state: String,
                 val scope: Scope
)