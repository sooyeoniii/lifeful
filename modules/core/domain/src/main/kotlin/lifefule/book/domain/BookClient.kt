package lifefule.book.domain

/**
 * 책 제공 클라이언트
 *
 * @author hd15807@gmail.com
 */
@Suppress("UnnecessaryInterface")
interface BookClient {
    /**
     * 검색 조건으로 책 데이터를 외부에서 가져온다.
     * @param bookId 책 ID
     * @return 책 도메인 모델, 찾지 못하면 null을 반환한다.
     */
    fun getBook(bookId: String): Book?
    
    /**
     * 검색어로 책 목록을 가져온다.
     * @param query 검색어
     * @return 책 도메인 모델 목록
     */
    fun searchBooks(query: String): List<Book>
    
    /**
     * ISBN으로 책을 검색한다.
     * @param isbn ISBN
     * @return 책 도메인 모델, 찾지 못하면 null을 반환한다.
     */
    fun getBookByIsbn(isbn: String): Book?
}
