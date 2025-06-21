package lifefule.shared

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.PrePersist
import jakarta.persistence.PreUpdate
import java.time.LocalDateTime

@MappedSuperclass
abstract class BaseEntity {
    @Column(updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()

    var modifiedAt: LocalDateTime = LocalDateTime.now()

    @PrePersist
    fun onCreate() {
        createdAt = LocalDateTime.now()
        modifiedAt = LocalDateTime.now()
    }

    @PreUpdate
    fun onUpdate() {
        modifiedAt = LocalDateTime.now()
    }
}
