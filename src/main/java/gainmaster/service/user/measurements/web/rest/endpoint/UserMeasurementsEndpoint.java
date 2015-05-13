package gainmaster.service.user.measurements.web.rest.endpoint;

import gainmaster.service.user.measurements.persistence.entity.UserMeasurementEntity;
import gainmaster.service.user.measurements.persistence.repository.UserMeasurementsRepository;
import gainmaster.service.user.measurements.web.rest.resource.UserMeasurementCollectionResource;
import gainmaster.service.user.measurements.web.rest.resource.UserMeasurementHistoryResource;
import gainmaster.service.user.measurements.web.rest.resource.UserMeasurementResource;

import gainmaster.service.user.measurements.web.rest.resource.assembler.UserMeasurementCollectionResourceAssembler;
import gainmaster.service.user.measurements.web.rest.resource.assembler.UserMeasurementHistoryResourceAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@ExposesResourceFor(UserMeasurementResource.class)
@RequestMapping("/users/{username}/measurements")
public class UserMeasurementsEndpoint {

    @Autowired
    private UserMeasurementsRepository userMeasurementsRepository;

    @Autowired
    private UserMeasurementCollectionResourceAssembler userMeasurementCollectionResourceAssembler;

    @Autowired
    private UserMeasurementHistoryResourceAssembler userMeasurementHistoryResourceAssembler;

    /**
     * Get user measurements
     */
    //@PreAuthorize("principal == 'steinar'")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getMeasurementCollection(
        @PathVariable("username") String username
    ) {
        Authentication authentication = SecurityContextHolder.getContext()
            .getAuthentication();
        System.out.println(authentication.getPrincipal().getClass().getName());
        System.out.println(authentication.getPrincipal());
        List<UserMeasurementEntity> userMeasurementEntities = new ArrayList<UserMeasurementEntity>();

        for (Object[] object : userMeasurementsRepository.getAllPropertiesNewestMeasurementByUsername(username)) {
            String property = (String) object[0];
            Date date = (Date) object[1];
            userMeasurementEntities.add(userMeasurementsRepository.getByUsernameAndDateAndProperty(username, date, property));
        }

        return new ResponseEntity<UserMeasurementCollectionResource>(
            userMeasurementCollectionResourceAssembler.toResource(userMeasurementEntities, username),
            HttpStatus.OK
        );
    }

    /**
     * Add user measurement
     *
     * @param username
     * @param userMeasurementResource
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity addMeasurement(
        @PathVariable("username") String username,
        @Valid @RequestBody UserMeasurementResource userMeasurementResource
    ) {
        UserMeasurementEntity userMeasurementEntity = new UserMeasurementEntity();
        userMeasurementEntity.setUsername(username);
        userMeasurementEntity.setProperty(userMeasurementResource.getProperty());
        userMeasurementEntity.setMagnitude(userMeasurementResource.getMagnitude());
        userMeasurementEntity.setUnit(userMeasurementResource.getUnit());
        userMeasurementEntity.setDate(new Date());

        userMeasurementEntity = userMeasurementsRepository.save(userMeasurementEntity);

        HttpHeaders headers = new HttpHeaders();
        //TODO: Add endpoint to single measurement
        //headers.setLocation(linkTo(methodOn(UserMeasurementsEndpoint.class)));

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    /**
     * Get user measurement history
     *
     * @param username
     * @param property
     * @return
     */
    @RequestMapping(value = "/{property}", method = RequestMethod.GET)
    public ResponseEntity<UserMeasurementHistoryResource> getMeasurementHistoy(
        @PathVariable("username") String username,
        @PathVariable("property") String property
    ) {
        Iterable<UserMeasurementEntity> userMeasurementEntities =
            userMeasurementsRepository.getUserMeasurementsByProperty(username, property);

        return new ResponseEntity<UserMeasurementHistoryResource>(
            userMeasurementHistoryResourceAssembler.toResource(userMeasurementEntities, username, property),
            HttpStatus.OK
        );
    }
}
