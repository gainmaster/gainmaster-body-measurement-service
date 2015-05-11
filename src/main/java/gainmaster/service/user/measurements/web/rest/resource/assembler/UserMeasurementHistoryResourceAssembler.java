package gainmaster.service.user.measurements.web.rest.resource.assembler;

import gainmaster.service.user.measurements.persistence.entity.UserMeasurementEntity;
import gainmaster.service.user.measurements.web.rest.endpoint.UserMeasurementsEndpoint;
import gainmaster.service.user.measurements.web.rest.resource.UserMeasurementHistoryResource;
import gainmaster.service.user.measurements.web.rest.resource.UserMeasurementResource;
import org.springframework.hateoas.*;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Component
public class UserMeasurementHistoryResourceAssembler extends
    ResourceAssemblerSupport<Iterable<UserMeasurementEntity>, UserMeasurementHistoryResource> {

    @Inject
    private UserMeasurementResourceAssembler userMeasurementResourceAssembler;

    @Inject
    private EntityLinks links;

    UserMeasurementHistoryResourceAssembler() {
        super(UserMeasurementsEndpoint.class, UserMeasurementHistoryResource.class);
    }

    public UserMeasurementHistoryResource toResource(Iterable<UserMeasurementEntity> entities, Long userId, String property) {
        UserMeasurementHistoryResource resource = toResource(entities);
        resource.add(linkTo(UserMeasurementsEndpoint.class, userId).slash(property).withSelfRel());
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
