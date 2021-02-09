package city.smartb.f2.spring.data.entity

import org.springframework.data.annotation.*
import java.util.*
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass
import javax.persistence.Temporal
import javax.persistence.TemporalType

@MappedSuperclass
@EntityListeners
abstract class EntityBase(
		@CreatedBy
		var createdBy: String? = null,
		@CreatedDate
		@Temporal(TemporalType.TIMESTAMP)
		var creationDate: Date? = null,
		@LastModifiedBy
		var lastModifiedBy: String? = null,
		@LastModifiedDate
		@Temporal(TemporalType.TIMESTAMP)
		var lastModifiedDate: Date? = null,
		@Version
		var version: Integer? = null
)