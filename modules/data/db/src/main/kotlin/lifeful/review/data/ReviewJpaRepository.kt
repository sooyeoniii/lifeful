package lifeful.review.data

import com.linecorp.kotlinjdsl.support.spring.data.jpa.repository.KotlinJdslJpqlExecutor
import lifefule.review.domain.Review
import lifefule.shared.BookId
import lifefule.shared.ReviewId
import org.springframework.data.jpa.repository.JpaRepository

internal interface ReviewJpaRepository : JpaRepository<Review, ReviewId>, KotlinJdslJpqlExecutor {
    fun findAllByBookId(bookId: BookId): List<Review>

    fun findByBookIdAndId(
        bookId: BookId,
        reviewId: ReviewId,
    ): Review?
}
