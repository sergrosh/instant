package com.instant.controller;

/**
 * @author sroshchupkin
 */
public interface Mappings {

    //MVC
    String HOME = "/home";
    String ID = "/{id}";
    String VENUES = "/venues";
    String VENUE = "/venue";
    String SAVE = "/save";
    String FAVOURITE = "/{id}/favourite";

    String UPLOAD_VENUE_IMAGE = "/upload/venue/image";
    String DELETE_VENUE_IMAGE = "/delete/venue/image";
    String VENUE_SUGGESTIONS = "/suggestions/venue";
    String CITY_SUGGESTIONS = "/suggestions/city";
    String ERROR_PATH = "/error";

    String ACCOUNT = "/account";
    String USER = "/user";
    String USER_ADD = "/user/add";

    //REST
    String REST = "/rest";
    String SEARCH = "/search";

    String FB = "/facebook";
    String FB_FEED = FB + "/feed";
    String FB_FRIENDS = FB + "/friends";
    String FB_ALBUMS = FB + "/albums";
    String FB_ALBUM = FB + "/album/{albumId}";
    String FB_EXPIRED = FB + "/expired";

}
