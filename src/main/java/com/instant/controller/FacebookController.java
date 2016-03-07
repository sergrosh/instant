package com.instant.controller;

import org.springframework.social.ExpiredAuthorizationException;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;

/**
 * @author sroshchupkin
 */

@Controller
public class FacebookController {

    private final Facebook facebook;

    @Inject
    public FacebookController(Facebook facebook) {
        this.facebook = facebook;
    }

    @RequestMapping(value = Mappings.FB_FEED, method = RequestMethod.GET)
    public String showFeed(Model model) {
        model.addAttribute("feed", facebook.feedOperations().getFeed());
        return "facebook/feed";
    }

    @RequestMapping(value = Mappings.FB_FEED, method = RequestMethod.POST)
    public String postUpdate(String message) {
        facebook.feedOperations().updateStatus(message);
        return "redirect:/facebook/feed";
    }

    @RequestMapping(value = Mappings.FB_FRIENDS, method = RequestMethod.GET)
    public String getFriends(Model model) {
        model.addAttribute("friends", facebook.friendOperations().getFriendProfiles());
        return "facebook/friends";
    }

    @RequestMapping(value = Mappings.FB_ALBUMS, method = RequestMethod.GET)
    public String showAlbums(Model model) {
        model.addAttribute("albums", facebook.mediaOperations().getAlbums());
        return "facebook/albums";
    }

    @RequestMapping(value = Mappings.FB_ALBUM, method = RequestMethod.GET)
    public String showAlbum(@PathVariable("albumId") String albumId, Model model) {
        model.addAttribute("album", facebook.mediaOperations().getAlbum(albumId));
        model.addAttribute("photos", facebook.mediaOperations().getPhotos(albumId));
        return "facebook/album";
    }

    @RequestMapping(Mappings.FB_EXPIRED)
    public void simulateExpiredToken() {
        throw new ExpiredAuthorizationException("facebook");
    }

}
