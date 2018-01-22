$(document).ready(function(){
    $('#headerRow').css('background-color', 'white');
    $('#headerRow').css('background', 'white');
});

function initMap() {
    map = new google.maps.Map(document.getElementById('mapDiv'), {
        center: {lat: 44.95, lng: -93.15},
        zoom: 11
    });
    var marker = new google.maps.Marker({
        position: new google.maps.LatLng(44.955001, -93.158172),
        map: map
    });

}
