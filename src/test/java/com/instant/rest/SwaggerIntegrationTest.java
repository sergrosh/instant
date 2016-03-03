package com.instant.rest;

import com.instant.InstantApplication;
import com.instant.persistence.repository.VenueRepository;
import io.github.robwin.swagger.test.SwaggerAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.File;

/**
 * @author sroshchupkin
 */
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {InstantApplication.class})
@IntegrationTest
@WebAppConfiguration
public class SwaggerIntegrationTest {

    @Autowired
    VenueRepository venueRepository;

    @Test
    public void shouldFindNoDifferences() {
        File designFirstSwaggerLocation = new File(SwaggerIntegrationTest.class.getResource("/itemadmin-swagger-api-v1.json").getFile());
        String implFirstSwaggerLocation = "http://localhost:8080/v2/api-docs";
        SwaggerAssertions.assertThat(implFirstSwaggerLocation).isEqualTo(designFirstSwaggerLocation.getAbsolutePath());
    }
}
