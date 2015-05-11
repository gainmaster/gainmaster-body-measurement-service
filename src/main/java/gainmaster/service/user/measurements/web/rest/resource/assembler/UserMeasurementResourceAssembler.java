package gainmaster.service.user.measurements.web.rest.resource.assembler;

import gainmaster.service.user.measurements.persistence.entity.UserMeasurementEntity;
import gainmaster.service.user.measurements.web.rest.endpoint.UserMeasurementsEndpoint;
import gainmaster.service.user.measurements.web.rest.resource.UserMeasurementResource;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class UserMeasurementResourceAssembler extends
    ResourceAssemblerSupport<UserMeasurementEntity, UserMeasurementResource> {

    public UserMeasurementResourceAssembler() {
        super(UserMeasurementsEndpoint.class, UserMeasurementResource.class);
    }

    @Override
    public UserMeasurementResource toResource(UserMeasurementEntity entity) {
        UserMeasurementResource resource = createResourceWithId(entity.getId(), entity, entity.getUserId());
        resource.add(linkTo(methodOn(UserMeasurementsEndpoint.class).getMeasurementHistoy(entity.getUserId(), entity.getProperty())).withRel("history"));
        return resource;
    }

    @Override
    protected UserMeasurementResource instantiateResource(UserMeasurementEntity entity) {
        UserMeasurementResource resource = UserMeasurementResource.fromProperty(entity.getProperty());
        resource.setProperty(entity.getProperty());
        resource.setMagnitude(entity.getMagnitude());
        resource.setUnit(entity.getUnit());
        resource.setDate(entity.getDate());
        return resource;
    }
}
