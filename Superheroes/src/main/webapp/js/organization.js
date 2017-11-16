/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
jQuery(document).ready(function () {
    //console.log("organization script is working");
    loadOrganizations();

});

function loadOrganizations() {
    $('#organizationList').empty();
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/Superheroes/organizations',
        success: function (organizationArray) {
            $.each(organizationArray, function (index, organization) {
                var organizationID = organization.organizationID;
                var organizationName = organization.organizationName;
                var organizationDescription = organization.organizationDescription;
                var organizationLocationName = organization.location.locationName;

                console.log(organizationID + "" + organizationName + "" +
                        organizationDescription + "" + organizationLocationName);

                var organizationSlot = '<li id="selectList">' + '<span onclick="viewOrganizationDetails(' +
                        organizationID + ')">' + organizationID + ' ' + organizationName + '</span>' + '</li>';
                $('#organizationList').append(organizationSlot);
            });
        },
        error: function () {
            console.log("error loading organizations");
        }
    });
}


function viewOrganizationDetails(organizationID) {

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/Superheroes/organization/' + organizationID,
        success: function (organization) {

            var organizationID = organization.organizationID;
            var organizationName = organization.organizationName;
            var organizationDescription = organization.organizationDescription;
            var organizationLocationName = organization.location.locationName;

            $('#organizationID').empty();
            $('#organizationName').empty();
            $('#organizationLocation').empty();
            $('#organizationDescription').empty();

            $('#organizationName').append('<b>Name:</b> ' + organizationName);
            $('#organizationLocation').append('<b>Headquarters:</b> ' + organizationLocationName);
            $('#organizationDescription').append('<br>' + organizationDescription);
            $('#organizationID').append(organizationID);

            $('#organizationDetailsMain').css('visibility', 'visible');
        },
        error: function () {
            console.log("error loading that organization");
        }
    });

    $('#memberList').empty();
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/Superheroes/superheroes/organization/' + organizationID,
        success: function (superheroesList) {
            //this should be a list of superheroes, iterate through and get their names
            console.log(superheroesList);
            $.each(superheroesList, function (index, superhero) {
                
                var superheroName = superhero.superheroName;
                var memberSlot = '<li>' + superheroName + '</li>';
                $('#memberList').append(memberSlot);
            });
        },
        error: function () {
            console.log("error loading members of organization");
        }
    });
}
;

function addOrganization() {
    //alert("add is being called");
    var organizationName = $('#add-organization-name').val();
    var organizationDescription = $('#add-organization-description').val();
    var locationID = $('#addLocationList').val();
    //var newLocation = getLocation();

    var organization = {
        organizationName: organizationName,
        organizationDescription: organizationDescription,
        location: {
            locationID: locationID
        }
    };

    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/Superheroes/organizations',
        data: JSON.stringify(organization),
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        success: function () {
            loadOrganizations();

        },
        error: function (data) {
            alert(data.values());
        }
    });
    

}
;

function deleteOrganization() {
    var organizationID = $('#organizationID').text();


    $.ajax({
        type: 'DELETE',
        url: 'http://localhost:8080/Superheroes/organization/' + organizationID,
        success: function (data) {
            var message = data;
            if (message.toString().length > 1) {
                $('#errorMessage').css('display', 'block');
                $('#errorMessage').text(message);
            } else {
                $('#errorMessage').empty();
                $('#errorMessage').css('display', 'none');
            }
            loadOrganizations();
        },
        error: function () {
            alert("error calling delete method");
        }
    });
    
}

function supplyEditData() {
    var organizationID = $('#organizationID').text();

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/Superheroes/organization/' + organizationID,
        success: function (organization) {

            var organizationID = organization.organizationID;
            var organizationName = organization.organizationName;
            var organizationDescription = organization.organizationDescription;
            var locationID = organization.location.locationID;

            $('#edit-organization-ID').val(organizationID);
            $('#edit-organization-name').val(organizationName);
            $('#edit-organization-description').val(organizationDescription);
            $('#locationList').val(locationID);

        },
        error: function () {
            console.log("error loading that organization");
        }
    });

    $('#addLocationList').empty();
    $('#locationList').empty();
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/Superheroes/locations',
        success: function (locations) {
            $.each(locations, function (index, location) {
                var locationID = location.locationID;
                var locationName = location.locationName;

                var locationSlot = '<option value="' + locationID + '">' + locationName + '</option>';
                //<option value="volvo">Volvo XC90</option>
//                var locationSlot = '<li id="selectList">' + '<span onclick="viewLocationDetails(' + locationID + ')">'
//                        + locationID + ' ' + locationName + '</span>' + '</li>';

                $('#locationList').append(locationSlot);
                $('#addLocationList').append(locationSlot);
            });
        },
        error: function () {
            console.log("error loading locations");
        }
    });
}

function getLocation() {
    //I probably will call this inside editOrganization
    //alert("get location is being called");
    var locationID = $('#locationList').val();
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/Superheroes/location/' + locationID,
        success: function (ajaxLocation) {
            var newLocation = {
                locationID: ajaxLocation.locationID,
                locationName: ajaxLocation.locationName,
                locationDescription: ajaxLocation.locationDescription,
                address: ajaxLocation.address,
                latitude: ajaxLocation.latitude,
                longitude: ajaxLocation.longitude
            };
            alert(newLocation);
            return newLocation;
            //why does this return undefined CORBIN HELP!
        },
        error: function () {
            alert("error loading that location");
        }
    });

}

function editOrganization() {
    var organizationID = $('#edit-organization-ID').val();
    var organizationName = $('#edit-organization-name').val();
    var organizationDescription = $('#edit-organization-description').val();
    var locationID = $('#locationList').val();


    //var newLocation = getLocation();


    var organization = {
        organizationID: organizationID,
        organizationName: organizationName,
        organizationDescription: organizationDescription,
        location: {
            locationID: locationID
        }

    };

    $.ajax({
        type: 'PUT',
        url: 'http://localhost:8080/Superheroes/organizations',
        data: JSON.stringify(organization),
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        success: function () {
            console.log("successfully connected with rest controller");
        },
        error: function (data) {
            alert(data.values());
        }
    });
    loadOrganizations();
}