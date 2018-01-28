$(document).ready(function () {
    $('#headerRow').css('background-color', 'white');
    $('#headerRow').css('background', 'white');
});

setTimeout(function initMap() {

    //make this respond to viewport size
    var windowSize = $(window).width();
    if (windowSize > 425) {
        map = new google.maps.Map(document.getElementById('mapDiv'), {
            center: {lat: 44.9880338, lng: -93.16883050},
            zoom: 11
        });
    } else {
        map = new google.maps.Map(document.getElementById('mapDiv'), {
            center: {lat: 44.9880338, lng: -93.16883050},
            zoom: 10
        });
    }

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
}, 100);

if(jQuery.browser.mobile){
    console.log("mobile browser");
    //add a class to button that disables hover or something
    $('.locationTitle').addClass('mobileButton');
}