/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function initMap(){
    map = new google.maps.Map(document.getElementById('mapDiv'), {
        center: {lat: 44.95, lng: -93.15},
        zoom: 11
    });
    var marker = new google.maps.Marker({
        position: new google.maps.LatLng(44.95, -93.15),
        map: map
    });
    
}