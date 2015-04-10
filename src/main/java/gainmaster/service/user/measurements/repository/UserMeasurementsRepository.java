package gainmaster.service.user.measurements.repository;

import gainmaster.service.user.measurements.entity.UserMeasurementEntity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface UserMeasurementsRepository extends CrudRepository<UserMeasurementEntity, Long> {

    @Query("SELECT em.property, MAX(em.date) FROM #{#entityName} em WHERE em.userId = :userId GROUP BY em.property")
    List<Object[]> getAllPropertiesNewestMeasurementByUserId(@Param("userId") Long userId);

    @Query("SELECT em FROM #{#entityName} em WHERE em.userId = :userId AND em.date = :date AND em.property = :property")
    UserMeasurementEntity getByUserIDAndDateAndProperty(@Param("userId") Long userId, @Param("date") Date date, @Param("property") String property);

    @Query("SELECT em FROM #{#entityName} em WHERE em.userId = :userId AND em.property = :property")
    List<UserMeasurementEntity> getUserMeasurementsByProperty(@Param("userId") Long userId, @Param("property") String property);
}
