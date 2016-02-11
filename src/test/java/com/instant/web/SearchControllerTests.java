package com.instant.web;

import com.instant.InstantApplication;
import com.instant.controller.SearchController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @author sroshchupkin
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = InstantApplication.class)
@WebAppConfiguration
public class SearchControllerTests {

    @Autowired
    private SearchController searchController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(searchController).build();
    }

    @Test
    public void testGetExistingVenue() throws Exception {
        mockMvc.perform(get("/clients?query=some&city=&category=")).andExpect(status().isOk());
    }
}
