package f2.spring.data.entity

import java.util.Date
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.Temporal
import jakarta.persistence.TemporalType
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.annotation.Version

@Deprecated("Use the one from s2 or import it in your project")
@MappedSuperclass
@EntityListeners
open class EntityBase(
	@CreatedBy var createdBy: String? = null,
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP) var creationDate: Date? = null,
	@LastModifiedBy var lastModifiedBy: String? = null,
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP) var lastModifiedDate: Date? = null,
	@Version var version: Int? = null,
)
