package lifefule.book.domain

open class BookException(
    override val message: String,
) : Exception(message)

/**
 * 후기가 존재하지 않을 때 발생할 수 있는 예외 클래스
 * @author hd15807@gmail.com
 */
class BookNotFoundException(
    override val message: String,
) : BookException(message)
