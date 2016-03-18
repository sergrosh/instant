package com.instant.persistence.model.social;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.social.security.SocialUserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author sroshchupkin
 */
@SuppressWarnings("serial")
@Document(collection = "UserAccount")
public class UserAccount extends BaseAuditableEntity implements SocialUserDetails {

    @Indexed(unique = true)
    private String userId;

    private UserRoleType[] roles;

    private String email;

    private String displayName;

    private String imageUrl;

    private String webSite;

    private boolean accountLocked;

    private boolean trustedAccount;

    private Set<String> myVenues;

    private Set<String> reviewed;

    private Set<String> favouritesVenues;

    public UserAccount() {
        this.roles = new UserRoleType[0];
    }

    public UserAccount(String userId, UserRoleType[] roles) {
        this.userId = userId;
        this.roles = roles;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UserRoleType[] getRoles() {
        return roles;
    }

    public void setRoles(UserRoleType[] roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public boolean isAccountLocked() {
        return accountLocked;
    }

    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public boolean isTrustedAccount() {
        return trustedAccount;
    }

    public void setTrustedAccount(boolean trustedAccount) {
        this.trustedAccount = trustedAccount;
    }

    public Set<String> getMyVenues() {
        if(myVenues==null)
            myVenues=new HashSet<>();
        return myVenues;
    }

    public void setMyVenues(Set<String> myVenues) {
        this.myVenues = myVenues;
    }

    public Set<String> getReviewed() {
        if(reviewed==null)
            reviewed=new HashSet<>();
        return reviewed;
    }

    public void setReviewed(Set<String> reviewed) {
        this.reviewed = reviewed;
    }

    public Set<String> getFavouritesVenues() {
        if(favouritesVenues==null)
            favouritesVenues=new HashSet<>();
        return favouritesVenues;
    }

    public void setFavouritesVenues(Set<String> favouritesVenues) {
        this.favouritesVenues = favouritesVenues;
    }

    public boolean addMyVenue(String venueId) {
        Set<String> venues = getMyVenues();
        return venues.add(venueId);
    }

    public boolean addReviewedVenue(String venueId) {
        Set<String> venues = getReviewed();
        return venues.add(venueId);
    }

    public boolean addFavouriteVenue(String venueId) {
        Set<String> venues = getFavouritesVenues();
        boolean added = venues.add(venueId);
        if (!added)
            venues.remove(venueId);
        return added;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(roles);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {
        // No password stored
        return null;
    }

    @Override
    public String getUsername() {
        return getUserId();
    }

    public boolean isAuthor() {
        for (UserRoleType role : getRoles()) {
            if (role == UserRoleType.ROLE_AUTHOR) {
                return true;
            }
        }
        return false;
    }

    public boolean isAdmin() {
        for (UserRoleType role : getRoles()) {
            if (role == UserRoleType.ROLE_ADMIN) {
                return true;
            }
        }
        return false;
    }

    public void updateProfile(String displayName, String email, String webSite) {
        setDisplayName(displayName);
        setEmail(email);
        setWebSite(webSite);
    }

    @Override
    public String toString() {
        String str = String.format("UserAccount{userId:'%s'; displayName:'%s';roles:[", getUserId(), getDisplayName());
        for (UserRoleType role : getRoles()) {
            str += role.toString() + ",";
        }
        return str + "]}";
    }

}

