package gainmaster.service.user.measurements.web.rest.resource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import java.math.BigDecimal;
import java.util.Date;
import javax.validation.constraints.NotNull;

@Relation(value = "measurement", collectionRelation = "measurements")
public class UserMeasurementResource extends ResourceSupport {

    @Relation(value = "weight", collectionRelation = "weight")
    private static class Weight extends UserMeasurementResource {}

    @Relation(value = "height", collectionRelation = "height")
    private static class Height extends UserMeasurementResource {}

    @NotNull
    private String property;

    @NotNull
    private BigDecimal magnitude;

    @NotNull
    private String unit;

    private Date date;


    @JsonCreator
    private UserMeasurementResource() {}

    public static UserMeasurementResource fromProperty(String property) {
        switch (property) {
            case "weight": return new Weight();
            case "height": return new Height();
            default: return new UserMeasurementResource();
        }
    }

    @JsonIgnore
    public String getProperty() {
        return property;
    }

    @JsonProperty
    public void setProperty(String property) {
        this.property = property;
    }

    @JsonProperty
    public BigDecimal getMagnitude() {
        return magnitude;
    }

    @JsonProperty
    public void setMagnitude(BigDecimal magnitude) {
        this.magnitude = magnitude;
    }

    @JsonProperty
    public String getUnit() {
        return unit;
    }

    @JsonProperty
    public void setUnit(String unit) {
        this.unit = unit;
    }

    @JsonProperty
    public Date getDate() {
        return date;
    }

    @JsonIgnore
    public void setDate(Date date) {
        this.date = date;
    }
}
