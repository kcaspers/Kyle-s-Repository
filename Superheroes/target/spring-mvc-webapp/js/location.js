/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
jQuery(document).ready(function () {
    console.log("location script is working");
    /*I will need to get all locations and add them to #locationList*/
    loadLocations();

});

$('#deleteLocationBtn').click(function () {
    deleteLocation();
});

$('#addLocation').click(function () {
    addLocation();
});

function loadLocations() {
    //I clear out the list on load also
    $('#locationList').empty();
    
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/Superheroes/locations',
        success: function (locationArray) {
            //console.log(locationArray);
            //I want to get put the locationID and locationName
            //$('#locationList').empty();
            $.each(locationArray, function (index, location) {
                var locationID = location.locationID;
                var locationName = location.locationName;

                var locationSlot = '<li id="selectList">' + '<span onclick="viewLocationDetails(' + locationID + ')">'
                        + locationID + ' ' + locationName + '</span>' + '</li>';
                $('#locationList').append(locationSlot);
            });

        },
        error: function () {
            console.log("error loading locations");
        }
    });
}

function viewLocationDetails(locationID) {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/Superheroes/location/' + locationID,
        success: function (location) {
            var locationID = location.locationID;
            var locationName = location.locationName;
            var locationDescription = location.locationDescription;
            var address = location.address;
            var latitude = location.latitude;
            var longitude = location.longitude;

            $('#locationID').empty();
            $('#locationName').empty();
            $('#locationDescription').empty();
            $('#locationAddress').empty();
            $('#latLong').empty();

            $('#locationID').append(locationID);
            $('#locationName').append(locationName);
            $('#locationDescription').append(locationDescription);
            $('#locationAddress').append(address);
            $('#latLong').append(latitude + String.fromCharCode(176) + ' '
                    + longitude + String.fromCharCode(176));

        },
        error: function () {
            console.log("error loading that location");
        }
    });

    $('#locationDetailsMain').css('visibility', 'visible');
};

function deleteLocation() {
    var locationID = $('#locationID').text();

    $.ajax({
        type: 'DELETE',
        url: 'http://localhost:8080/Superheroes/location/' + locationID,
        success: function (data) {
            var message = data;
            //output this message to the page if needed
            if (message.toString().length > 1) {
                $('#errorMessage').css('display', 'block');
                $('#errorMessage').text(message);
            } else {
                $('#errorMessage').empty();
                $('#errorMessage').css('display', 'none');
            }
            loadLocations();
        },
        error: function () {
            console.log("error calling delete method");
        }
    });
}
;

function addLocation() {
    //get all the variables from the modal to make a location and send it to to location controller method
    var locationName = $('#add-location-name').val();
    var locationDescription = $('#add-location-description').val();
    var locationAddress = $('#add-location-address').val();
    var locationLatitude = $('#add-location-latitude').val();
    var locationLongitude = $('#add-location-longitude').val();

    var location = {
        locationName: locationName,
        locationDescription: locationDescription,
        address: locationAddress,
        latitude: locationLatitude,
        longitude: locationLongitude
    };

    if (location.longitude === "" || location.latitude === ""){
        alert("Please provide valid latitude and longitude values.");
    }else if(Math.abs(location.longitude) > 180.99 || Math.abs(location.latitude) > 90.99){
        alert("Please provide latitude longitude values within range");
    }else {
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/Superheroes/locations',
            data: JSON.stringify(location),
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            success: function () {
                loadLocations();
            },
            error: function (data) {
                alert(data.values());
            }
        });
    }
};

function supplyEditData() {
    var locationID = $('#locationID').text();

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/Superheroes/location/' + locationID,
        success: function (location) {
            var locationID = location.locationID;
            var locationName = location.locationName;
            var locationDescription = location.locationDescription;
            var address = location.address;
            var latitude = location.latitude;
            var longitude = location.longitude;

            $('#edit-Location-ID').val(locationID);
            $('#edit-location-name').val(locationName);
            $('#edit-location-description').val(locationDescription);
            $('#edit-location-address').val(address);
            $('#edit-location-latitude').val(latitude);
            $('#edit-location-longitude').val(longitude);

        },
        error: function () {
            console.log("error loading that location");
        }
    });
}
;

function editLocation() {
    var locationID = $('#edit-Location-ID').val();
    var locationName = $('#edit-location-name').val();
    var locationDescription = $('#edit-location-description').val();
    var locationAddress = $('#edit-location-address').val();
    var locationLatitude = $('#edit-location-latitude').val();
    var locationLongitude = $('#edit-location-longitude').val();

    var location = {
        locationID: locationID,
        locationName: locationName,
        locationDescription: locationDescription,
        address: locationAddress,
        latitude: locationLatitude,
        longitude: locationLongitude
    };

    if (location.longitude === "" || location.latitude === ""){
        alert("Please provide valid latitude and longitude values.");
    }else if(Math.abs(location.longitude) > 180.99 || Math.abs(location.latitude) > 90.99){
        alert("Please provide latitude longitude values within range");
    }else {
        $.ajax({
            type: 'PUT',
            url: 'http://localhost:8080/Superheroes/locations',
            data: JSON.stringify(location),
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            success: function () {
                //alert("succesfully connected with rest controller");
            },
            error: function (data) {
                alert(data.values());
            }
        });
        loadLocations();
    }

};