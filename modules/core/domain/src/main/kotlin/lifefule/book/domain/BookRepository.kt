package lifefule.book.domain

import lifefule.shared.BookId

/**
 * 책 저장소
 *
 * @author hd15807@gmail.com
 */
interface BookRepository {
    fun findAll(): List<Book>

    fun findById(bookId: BookId): Book?
}
