package lifeful.book.application.query

import lifefule.book.domain.Book
import lifefule.shared.BookId

/**
 * 책을 검색하는 유즈케이스이다.
 *
 * @author hd15807@gmail.com
 */
interface FindBook {
    fun all(): List<Book>

    fun findById(bookId: BookId): Book
}
