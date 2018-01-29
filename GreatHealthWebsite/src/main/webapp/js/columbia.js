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

$(window).ready(function (event) {
    $("#learnMore").each(function (i, el) {
        var el = $(el);
        if (el.visible(true)) {
            setTimeout(function () {
                el.addClass("come-in");
            }, 700);
        }
    });
});

var map, marker, location, directions, directionsService, directionsDisplay;
setTimeout(function initMap() {
    map = new google.maps.Map(document.getElementById('mapDiv'), {
        center: {lat: 45.062247, lng: -93.247669},
        zoom: 11
    });
    marker = new google.maps.Marker({
        position: new google.maps.LatLng(45.062247, -93.247669),
        map: map
    });
    
    if(navigator.geolocation){
        navigator.geolocation.getCurrentPosition(function (position){
            var pos = {
                lat: position.coords.latitude,
                lng: position.coords.longitude
            };
            
            //directions
            directionsService = new google.maps.DirectionsService();
            directionsDisplay = new google.maps.DirectionsRenderer();
            directionsDisplay.setMap(map);
            directions = {
                origin: pos,
                //destination: new google.maps.LatLng(marker.position.lat(), marker.position.lng()),
                destination: '5192 Central Ave NE, Columbia Heights, MN 55421',
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

$(window).ready(function checkWidth(){
    var windowSize = $(window).width();
    if(windowSize < 990){
        $('#mapCol').detach().insertAfter('#mapRepos');
    }
    if(windowSize > 990){
        $('#mapCol').detach().insertAfter('#picDiv');
    }
});

$(window).resize(function checkWidth(){
    var windowSize = $(window).width();
    if(windowSize < 990){
        $('#mapCol').detach().insertAfter('#mapRepos');
    }
    if(windowSize > 990){
        $('#mapCol').detach().insertAfter('#picDiv');
    }
});