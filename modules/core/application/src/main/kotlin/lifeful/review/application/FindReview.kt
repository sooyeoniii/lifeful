package lifeful.review.application

import lifefule.review.domain.Review
import lifefule.shared.BookId
import lifefule.shared.ReviewId

/**
 * 후기 검색 유즈케이스
 * @author hd15807@gmail.com
 */
interface FindReview {
    fun byId(
        bookId: BookId,
        reviewId: ReviewId,
    ): Review

    fun all(bookId: BookId): List<Review>
}
