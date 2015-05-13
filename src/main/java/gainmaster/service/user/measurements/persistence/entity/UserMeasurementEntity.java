package gainmaster.service.user.measurements.persistence.entity;

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

    @Column(name = "username", nullable = false, length = 255)
    private String username;

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

    public UserMeasurementEntity(Long id, String username, String property, BigDecimal magnitude, String unit, Date date) {
        this.id = id;
        this.username = username;
        this.property = property;
        this.magnitude = magnitude;
        this.unit = unit;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
