/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    loadMostRecentSightings();
});

var sightingLocations = new Array();
function loadMostRecentSightings() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/Superheroes/sightings/mostRecent',
        /*We want this to return location, all superheroes present and date*/
        success: function (sightingList) {
            console.log(sightingList);
            $.each(sightingList, function (index, sighting) {
                var date = sighting.sightingDate;
                var latestDate = '<td>' + date[1] + '-' + date[2] + '-' + date[0] + '</td>';
                var latestLocation = '<td>' + sighting.location.locationName + '</td>';
                var tableRow = '<tr>' + latestDate + latestLocation + '</td>';

                $('#latestSightingsList').append(tableRow);

                var latitude = sighting.location.latitude;
                var longitude = sighting.location.longitude;
                var coordinates = [
                    latitude,
                    longitude
                ];
                sightingLocations.push(coordinates);
                initMap();
            });
        },
        error: function () {
            console.log("there was an ajax error");
        }
    });
}
;
function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: 39.97, lng: -93.26},
        zoom: 4
    });
    var marker;
    var i;
    for (i = 0; i < sightingLocations.length; i++) {
        marker = new google.maps.Marker({
            position: new google.maps.LatLng(sightingLocations[i][0], sightingLocations[i][1]),
            //position: new google.maps.LatLng(40.363, -93.26),
            map: map
        });
    }
}




