//global
var local_city;

jQuery.ajax( {
      url: '//freegeoip.net/json/',
      type: 'POST',
      dataType: 'jsonp',
      success: function(location) {
        // example where I update content on the page.
        local_city = location.city;
        jQuery('#city_input').val(location.city);
        /*
        jQuery('#region-code').html(location.region_code);
        jQuery('#region-name').html(location.region_name);
        jQuery('#areacode').html(location.areacode);
        jQuery('#ip').html(location.ip);
        jQuery('#zipcode').html(location.zipcode);
        jQuery('#longitude').html(location.longitude);
        jQuery('#latitude').html(location.latitude);
        jQuery('#country-name').html(location.country_name);
        jQuery('#country-code').html(location.country_code);
        */
      }
    } );