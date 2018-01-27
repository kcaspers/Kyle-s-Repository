/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function ($) {

    /**
     * Copyright 2012, Digital Fusion
     * Licensed under the MIT license.
     * http://teamdf.com/jquery-plugins/license/
     *
     * @author Sam Sehnert
     * @desc A small plugin that checks whether elements are within
     *     the user visible viewport of a web browser.
     *     only accounts for vertical position, not horizontal.
     */

    $.fn.visible = function (partial) {

        var $t = $(this),
                $w = $(window),
                viewTop = $w.scrollTop(),
                viewBottom = viewTop + $w.height(),
                _top = $t.offset().top,
                _bottom = _top + $t.height(),
                compareTop = partial === true ? _bottom : _top,
                compareBottom = partial === true ? _top : _bottom;

        return ((compareBottom <= viewBottom) && (compareTop >= viewTop));

    };

})(jQuery);

//setTimeout(function initMap() {
//    map = new google.maps.Map(document.getElementById('mapDiv'), {
//        center: {lat: 44.95, lng: -93.15},
//        zoom: 11
//    });
//    var marker = new google.maps.Marker({
//        position: new google.maps.LatLng(44.955001, -93.158172),
//        map: map
//    });
//});   

    var map, marker, location, directions, directionsService, directionsDisplay;
    setTimeout(function initMap() {
        map = new google.maps.Map(document.getElementById('mapDiv'), {
          center: {lat: 44.95, lng: -93.15},
          zoom: 11
        });
        
        marker = new google.maps.Marker({
            position: new google.maps.LatLng(44.955001, -93.158172),
            map: map
        });
        
        // Try HTML5 geolocation.
        if (navigator.geolocation) {
          navigator.geolocation.getCurrentPosition(function(position) {
            var pos = {
              lat: position.coords.latitude,
              lng: position.coords.longitude
            };
            
            //blue marker
//            var location = new google.maps.Marker({
//            position: new google.maps.LatLng(pos.lat, pos.lng),
//            map: map,
//            icon: 'http://maps.google.com/mapfiles/ms/icons/blue-dot.png'
//            });
            
            //directions
            directionsService = new google.maps.DirectionsService();
            directionsDisplay = new google.maps.DirectionsRenderer();
            directionsDisplay.setMap(map);
            
            directions = {
                origin: pos,
                destination: new google.maps.LatLng(marker.position.lat(), marker.position.lng()),
                travelMode: 'DRIVING'
            };
            
            directionsService.route(directions, function(response, status){
                if(status == 'OK'){
                    directionsDisplay.setDirections(response);
                    marker.setVisible(false);
                }
            });
          });
        }
      }, 100);
      
    function handleLocationError(browserHasGeolocation, infoWindow, pos) {
    infoWindow.setPosition(pos);
    infoWindow.setContent(browserHasGeolocation ?
                              'Error: The Geolocation service failed.' :
                              'Error: Your browser doesn\'t support geolocation.');
    infoWindow.open(map);
    }


$(window).scroll(function (event) {
    $("#learnMore").each(function (i, el) {
        var el = $(el);
        if (el.visible(true)) {
            setTimeout(function () {
                el.addClass("come-in");
            }, 700);
        }
    });

});

$(window).ready(function checkWidth(){
    console.log('checkWidth called');
    var windowSize = $(window).width();
    if(windowSize < 990){
        $('#mapCol').detach().insertAfter('#mapRepos');
    }
    if(windowSize > 990){
        $('#mapCol').detach().insertAfter('#picDiv');
    }
});

$(window).resize(function checkWidth(){
    console.log('checkWidth called');
    var windowSize = $(window).width();
    if(windowSize < 990){
        $('#mapCol').detach().insertAfter('#mapRepos');
    }
    if(windowSize > 990){
        $('#mapCol').detach().insertAfter('#picDiv');
    }
});


