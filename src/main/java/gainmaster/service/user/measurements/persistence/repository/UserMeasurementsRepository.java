package gainmaster.service.user.measurements.persistence.repository;

import gainmaster.service.user.measurements.persistence.entity.UserMeasurementEntity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface UserMeasurementsRepository extends CrudRepository<UserMeasurementEntity, Long> {

    @Query("SELECT em.property, MAX(em.date) FROM #{#entityName} em WHERE em.username = :username GROUP BY em.property")
    List<Object[]> getAllPropertiesNewestMeasurementByUsername(@Param("username") String username);

    @Query("SELECT em FROM #{#entityName} em WHERE em.username = :username AND em.date = :date AND em.property = :property")
    UserMeasurementEntity getByUsernameAndDateAndProperty(@Param("username") String username, @Param("date") Date date, @Param("property") String property);

    @Query("SELECT em FROM #{#entityName} em WHERE em.username = :username AND em.property = :property")
    List<UserMeasurementEntity> getUserMeasurementsByProperty(@Param("username") String username, @Param("property") String property);
}
