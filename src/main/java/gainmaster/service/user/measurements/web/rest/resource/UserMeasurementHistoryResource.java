package gainmaster.service.user.measurements.web.rest.resource;

import com.fasterxml.jackson.annotation.JsonCreator;

import org.springframework.hateoas.Resources;

public class UserMeasurementHistoryResource extends Resources<UserMeasurementResource> {

    @JsonCreator
    public UserMeasurementHistoryResource(Iterable<UserMeasurementResource> userResources) {
        super(userResources);
    }

}
