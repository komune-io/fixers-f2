package f2.spring.data.entity

import java.util.Date
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass
import javax.persistence.Temporal
import javax.persistence.TemporalType
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.annotation.Version

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
