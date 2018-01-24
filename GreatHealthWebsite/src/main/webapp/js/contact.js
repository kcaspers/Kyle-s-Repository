/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function initMap() {
    map = new google.maps.Map(document.getElementById('mapDiv'), {
        center: {lat: 44.955001, lng: -93.158172},
        zoom: 9
    });

    var midway = new google.maps.Marker({
        position: new google.maps.LatLng(44.955001, -93.158172),
        map: map
    });
    google.maps.event.addListener(midway, 'click', function () {
        window.location.href = "https://obscure-thicket-57749.herokuapp.com/jsp/midway.jsp";
    });

    var westsaintpaul = new google.maps.Marker({
        position: new google.maps.LatLng(44.895164, -93.07865),
        map: map
    });
    google.maps.event.addListener(westsaintpaul, 'click', function () {
        window.location.href = "https://obscure-thicket-57749.herokuapp.com/jsp/westsaintpaul.jsp";
    });

    var columbia = new google.maps.Marker({
        position: new google.maps.LatLng(45.062247, -93.247669),
        map: map
    });
    google.maps.event.addListener(columbia, 'click', function () {
        window.location.href = "https://obscure-thicket-57749.herokuapp.com/jsp/columbia.jsp";
    });
}
