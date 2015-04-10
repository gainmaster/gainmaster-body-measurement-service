package gainmaster.service.user.measurements.entity;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "user_measurement")
public class UserMeasurementEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "property", nullable = false, length = 255)
    private String property;

    @Column(name = "magnitude", nullable = false, precision = 2)
    private BigDecimal magnitude;

    @Column(name = "unit", nullable = false, length = 255)
    private String unit;

    @Column(name = "date", nullable = false)
    private Date date;

    public UserMeasurementEntity() {
    }

    public UserMeasurementEntity(Long id, Long userId, String property, BigDecimal magnitude, String unit, Date date) {
        this.id = id;
        this.userId = userId;
        this.property = property;
        this.magnitude = magnitude;
        this.unit = unit;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public BigDecimal getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(BigDecimal magnitude) {
        this.magnitude = magnitude;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
