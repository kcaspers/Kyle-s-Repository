/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function initMap(){
    map = new google.maps.Map(document.getElementById('mapDiv'),{
        center: {lat: 44.95, lng: -93.21},
        zoom: 9
    });
    
    var midway = new google.maps.Marker({
        position: new google.maps.LatLng(44.95, -93.15),
        map: map
    });
    
    var westsaintpaul = new google.maps.Marker({
        position: new google.maps.LatLng(44.89, -93.07),
        map: map
    });
    
    var columbia = new google.maps.Marker({
        position: new google.maps.LatLng(45.062247, -93.247669),
        map: map
    });
}
