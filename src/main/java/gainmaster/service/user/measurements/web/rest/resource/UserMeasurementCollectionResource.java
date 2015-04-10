package gainmaster.service.user.measurements.web.rest.resource;

import com.fasterxml.jackson.annotation.JsonCreator;

import org.springframework.hateoas.Resources;

public class UserMeasurementCollectionResource extends Resources<UserMeasurementResource> {

    @JsonCreator
    public UserMeasurementCollectionResource(Iterable<UserMeasurementResource> embeddedResources) {
        super(embeddedResources);
    }

}
