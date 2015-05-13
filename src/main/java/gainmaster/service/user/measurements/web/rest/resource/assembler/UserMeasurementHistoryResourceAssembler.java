package gainmaster.service.user.measurements.web.rest.resource.assembler;

import gainmaster.service.user.measurements.persistence.entity.UserMeasurementEntity;
import gainmaster.service.user.measurements.web.rest.endpoint.UserMeasurementsEndpoint;
import gainmaster.service.user.measurements.web.rest.resource.UserMeasurementHistoryResource;
import gainmaster.service.user.measurements.web.rest.resource.UserMeasurementResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.*;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Component
public class UserMeasurementHistoryResourceAssembler extends
    ResourceAssemblerSupport<Iterable<UserMeasurementEntity>, UserMeasurementHistoryResource> {

    @Autowired
    private UserMeasurementResourceAssembler userMeasurementResourceAssembler;

    @Autowired
    private EntityLinks links;

    UserMeasurementHistoryResourceAssembler() {
        super(UserMeasurementsEndpoint.class, UserMeasurementHistoryResource.class);
    }

    public UserMeasurementHistoryResource toResource(Iterable<UserMeasurementEntity> entities, String username, String property) {
        UserMeasurementHistoryResource resource = toResource(entities);
        resource.add(linkTo(UserMeasurementsEndpoint.class, username).slash(property).withSelfRel());
        return resource;
    }

    @Override
    public UserMeasurementHistoryResource toResource(Iterable<UserMeasurementEntity> entities) {
        UserMeasurementHistoryResource resource = instantiateResource(entities);
        return resource;
    }

    @Override
    protected UserMeasurementHistoryResource instantiateResource(Iterable<UserMeasurementEntity> entities) {
        Iterable<UserMeasurementResource> embeddedResources = userMeasurementResourceAssembler.toResources(entities);
        UserMeasurementHistoryResource resource = new UserMeasurementHistoryResource(embeddedResources);
        return resource;
    }
}
