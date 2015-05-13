package gainmaster.service.user.measurements.web.rest.resource.assembler;

import gainmaster.service.user.measurements.persistence.entity.UserMeasurementEntity;
import gainmaster.service.user.measurements.web.rest.endpoint.UserMeasurementsEndpoint;
import gainmaster.service.user.measurements.web.rest.resource.UserMeasurementCollectionResource;
import gainmaster.service.user.measurements.web.rest.resource.UserMeasurementResource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class UserMeasurementCollectionResourceAssembler extends
    ResourceAssemblerSupport<Iterable<UserMeasurementEntity>, UserMeasurementCollectionResource> {

    @Autowired
    private UserMeasurementResourceAssembler userMeasurementResourceAssembler;


    public UserMeasurementCollectionResourceAssembler() {
        super(UserMeasurementsEndpoint.class, UserMeasurementCollectionResource.class);
    }

    public UserMeasurementCollectionResource toResource(Iterable<UserMeasurementEntity> entities, String username) {
        UserMeasurementCollectionResource resource = toResource(entities);
        resource.add(linkTo(methodOn(UserMeasurementsEndpoint.class).getMeasurementCollection(username)).withSelfRel());
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
