package gainmaster.service.user.measurements.web.rest.resource.assembler;

import gainmaster.service.user.measurements.persistence.entity.UserMeasurementEntity;
import gainmaster.service.user.measurements.web.rest.endpoint.UserMeasurementsEndpoint;
import gainmaster.service.user.measurements.web.rest.resource.UserMeasurementCollectionResource;
import gainmaster.service.user.measurements.web.rest.resource.UserMeasurementResource;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class UserMeasurementCollectionResourceAssembler extends
    ResourceAssemblerSupport<Iterable<UserMeasurementEntity>, UserMeasurementCollectionResource> {

    @Inject
    private UserMeasurementResourceAssembler userMeasurementResourceAssembler;


    public UserMeasurementCollectionResourceAssembler() {
        super(UserMeasurementsEndpoint.class, UserMeasurementCollectionResource.class);
    }

    public UserMeasurementCollectionResource toResource(Iterable<UserMeasurementEntity> entities, Long userId) {
        UserMeasurementCollectionResource resource = toResource(entities);
        resource.add(linkTo(methodOn(UserMeasurementsEndpoint.class).getMeasurementCollection(userId)).withSelfRel());
        return resource;
    }

    @Override
    public UserMeasurementCollectionResource toResource(Iterable<UserMeasurementEntity> entities) {
        UserMeasurementCollectionResource resource = instantiateResource(entities);
        return resource;
    }

    @Override
    protected UserMeasurementCollectionResource instantiateResource(Iterable<UserMeasurementEntity> entities) {
        Iterable<UserMeasurementResource> embeddedResources = userMeasurementResourceAssembler.toResources(entities);
        UserMeasurementCollectionResource resource = new UserMeasurementCollectionResource(embeddedResources);
        return resource;
    }
}
