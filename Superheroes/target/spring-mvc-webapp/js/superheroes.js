/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    loadSupers();
    loadLists();
});

function loadSupers() {
    $('#superheroList').empty();
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/Superheroes/superheroes',
        success: function (superheroList) {
            $.each(superheroList, function (index, superhero) {
                var superheroID = superhero.superheroID;
                var superheroName = superhero.superheroName;
                var description = superhero.description;
                var power = superhero.power;
                var organizations = superhero.organizations;
                var sightings = superhero.sightings;

                var superListSlot = '<li id="selectList">' + '<span onclick="viewSuperDetails(' +
                        superheroID + ')">' + superheroID + ' ' + superheroName + '</span></li>';
                $('#superheroList').append(superListSlot);
            });
        },
        error: function () {
            console.log("error loading supers");
        }
    });
}

function removeAddOrg(elementId) {
    $('#addOrg' + elementId).remove();
}

function removeAddSighting(elementId) {
    $('#addSighting' + elementId).remove();
}

function removeEditOrg(elementId) {
    $('#editOrg' + elementId).remove();
}

function removeEditSighting(elementId) {
    $('#editSighting' + elementId).remove();
}

function loadLists() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/Superheroes/organizations',
        success: function (organizations) {
            $.each(organizations, function (index, organization) {
                var organizationID = organization.organizationID;
                var organizationName = organization.organizationName;
                var organizationSlot = '<option value="' + organizationID + '">' + organizationName +
                        '</option';

                $('.superOrganizationList').append(organizationSlot);
            });
        },
        error: function () {
            console.log("error loading orgs");
        }
    });

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/Superheroes/sightings',
        success: function (sightings) {
            $.each(sightings, function (index, sighting) {
                var sightingID = sighting.sightingID;
                var sightingLocation = sighting.location.locationName;
                var sightingMonth = sighting.sightingDate[1];
                var sightingDay = sighting.sightingDate[2];
                var sightingYear = sighting.sightingDate[0];
                var sightingDate = sightingMonth + '-' + sightingDay + '-' + sightingYear;

                var sightingSlot = '<option value="' + sightingID + '">' + sightingLocation + ' ' +
                        sightingDate + '</option>';
                $('.superSightingList').append(sightingSlot);
            });
        },
        error: function () {
            console.log("error loading orgs");
        }
    });
}

function viewSuperDetails(superheroID) {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/Superheroes/superhero/' + superheroID,
        success: function (superhero) {
            //console.log(superhero.superheroName);
            var superheroID = superhero.superheroID;
            var superheroName = superhero.superheroName;
            var description = superhero.description;
            var power = superhero.power;
            var organizations = superhero.organizations;
            var sightings = superhero.sightings;
            $('#superheroID').empty();
            $('#superheroInfoID').text(superheroID);
            $('#superheroName').empty();
            $('#superheroName').append(superheroName);
            $('#superPower').empty();
            $('#superPower').append(power);
            $('#superheroDescription').empty();
            $('#superheroDescription').text(description);

            //add to the edit fields: name, power, description to start
            $('#editSuperheroName').empty();
            $('#editSuperheroName').val(superheroName);
            $('#editSuperheroPower').empty();
            $('#editSuperheroPower').val(power);
            $('#editSuperheroDescription').empty();
            $('#editSuperheroDescription').text(description);

            $('#editSuperOrganizations').empty();
            $.each(organizations, function (index, organization) {
                var organizationSlot = '<span onclick="removeEditOrg(' + organization.organizationID + ')">' +
                        '<li id="editOrg' + organization.organizationID + '" class="editOrg" value="' + organization.organizationID + '">' +
                        organization.organizationName + '</li></span>';
                $('#editSuperOrganizations').append(organizationSlot);
            });

            $('#editSuperSightings').empty();
            if (sightings.length > 0) {
                $.each(sightings, function (index, sighting) {
                    //alert(organization.organizationName);
                    var sightingID = sighting.sightingID;
                    var sightingSlot = '<span onclick="removeEditSighting(' + sightingID + ')">' +
                            '<li id="editSighting' + sightingID + '" class="editSighting" value="' + sightingID + '">' +
                            sighting.sightingDate[1] + '-' + sighting.sightingDate[2] + '-' + sighting.sightingDate[0] + ' ' +
                            sighting.location.locationName + '</li></span>';
                    $('#editSuperSightings').append(sightingSlot);
                });
            }
        },
        error: function () {
            console.log("error loading that super");
        }
    });
    $('#superheroDetails').css('visibility', 'visible');
}

function deleteSuperhero() {
    var superheroID = $('#superheroInfoID').text();

    $.ajax({
        type: 'DELETE',
        url: 'http://localhost:8080/Superheroes/superhero/' + superheroID,
        success: function () {
            loadSupers();
        },
        error: function () {
        }
    });

}

function addSuper() {
    var superheroName = $('#addSuperheroName').val();
    var power = $('#addSuperheroPower').val();
    var description = $('#addSuperheroDescription').val();


//    var organizationIDs = new Array();
//    //sightings and orgs only need their IDs
//    $('.addOrg').each(function () {
//        var organizationID = $(this).val();
//        organizations.push(organizationID);
//    });

    var organizations = new Array();
    $('.addOrg').each(function () {
        var organization = {
            organizationID: $(this).val()
        };
        organizations.push(organization);
    });

    var sightings = new Array();
    $('.addSighting').each(function () {
        var sighting = {
            sightingID: $(this).val()
        };
        sightings.push(sighting);
    });



//    var sightingIDs = new Array();
//    $('.addSighting').each(function () {
//        var sightingID = $(this).val();
//        sightings.push(sightingID);
//    });

    var superhero = {
        superheroName: superheroName,
        description: description,
        power: power,
        organizations: organizations,
        sightings: sightings

//        sightings: {
//            sightings: sightings
//        }
    };

    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/Superheroes/superhero',
        data: JSON.stringify(superhero),
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        success: function () {
            //alert("post success");
            loadSupers();
        },
        error: function () {
            //console.log(data);


        }
    });
    loadSupers();
}

function editSuper() {
    var superheroName = $('#editSuperheroName').val();
    var power = $('#editSuperheroPower').val();
    var description = $('#editSuperheroDescription').val();
    var superheroID = $('#superheroInfoID').text();

    var organizations = new Array();
    $('.editOrg').each(function () {
        var organization = {
            organizationID: $(this).val()
        };
        organizations.push(organization);
    });

    var sightings = new Array();
    $('.editSighting').each(function () {
        var sighting = {
            sightingID: $(this).val()
        };
        sightings.push(sighting);
    });

    var superhero = {
        superheroName: superheroName,
        description: description,
        power: power,
        superheroID: superheroID,
        organizations: organizations,
        sightings: sightings
    };

    $.ajax({
        type: 'PUT',
        url: 'http://localhost:8080/Superheroes/superhero',
        data: JSON.stringify(superhero),
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        success: function () {
            //alert("post success");

        },
        error: function () {
            //console.log(data);
        }
    });

}

//select organization
function selectOrganization(organizationID) {
    console.log("organization" + organizationID);
    var organizationID = organizationID;

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/Superheroes/organization/' + organizationID,
        success: function (organization) {
            var organizationID = organization.organizationID;
            var organizationName = organization.organizationName;

            var organizationSlot = '<span onclick="removeAddOrg(' + organizationID + ')">' +
                    '<li id="addOrg' + organizationID + '" class="addOrg" value="' + organizationID + '">' +
                    organizationName + '</li></span>';

            var currentOrgs = new Array();
            $('.addOrg').each(function () {
                var organizationID = $(this).val();
                currentOrgs.push(organizationID);
            });
            if (!currentOrgs.includes(organizationID)) {
                $('#addSuperOrganizations').append(organizationSlot);
            }

        },
        error: function () {
            console.log("error selecting that organization");
        }
    });
}

//select sighting
function selectSighting(sightingID) {
    console.log("sighting" + sightingID);
    var sightingID = sightingID;

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/Superheroes/sighting/' + sightingID,
        success: function (sighting) {
            var sightingID = sighting.sightingID;
            var sightingLocation = sighting.location.locationName;
            //var sightingDate = sighting.sightingDate.dayOfMonth + ' ' + sighting.sightingDate.month;
            var sightingMonth = sighting.sightingDate[1];
            var sightingDay = sighting.sightingDate[2];
            var sightingYear = sighting.sightingDate[0];
            var sightingDate = sightingMonth + '-' + sightingDay + '-' + sightingYear;

            var sightingSlot = '<span onclick="removeAddSighting(' + sightingID + ')">' +
                    '<li id="addSighting' + sightingID + '" class="addSighting" value="' + sightingID + '">' +
                    sightingDate + ' ' + sightingLocation + '</li></span>';

            var currentSightings = new Array();
            $('.addSighting').each(function () {
                var sightingID = $(this).val();
                currentSightings.push(sightingID);
            });
            if (!currentSightings.includes(sightingID)) {
                $('#addSuperSightings').append(sightingSlot);
            }
        },
        error: function () {
            console.log("error selecting that sighting");
        }
    });
}

function selectEditOrganization(organizationID) {
    console.log("organization" + organizationID);
    var organizationID = organizationID;

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/Superheroes/organization/' + organizationID,
        success: function (organization) {
            var organizationID = organization.organizationID;
            var organizationName = organization.organizationName;

            var organizationSlot = '<span class="' + organizationID + '" onclick="removeEditOrg(' + organizationID + ')">' +
                    '<li id="editOrg' + organizationID + '" class="editOrg" value="' + organizationID + '">' +
                    organizationName + '</li></span>';

            var currentOrgs = new Array();
            $('.editOrg').each(function () {
                var organizationID = $(this).val();
                currentOrgs.push(organizationID);
            });
            if (!currentOrgs.includes(organizationID)) {
                $('#editSuperOrganizations').append(organizationSlot);
            }
        },
        error: function () {
            console.log("error selecting that organization");
        }
    });
}

function selectEditSighting(sightingID) {
    console.log("sighting" + sightingID);
    var sightingID = sightingID;

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/Superheroes/sighting/' + sightingID,
        success: function (sighting) {
            var sightingID = sighting.sightingID;
            var sightingLocation = sighting.location.locationName;
            //var sightingDate = sighting.sightingDate.dayOfMonth + ' ' + sighting.sightingDate.month;
            var sightingMonth = sighting.sightingDate[1];
            var sightingDay = sighting.sightingDate[2];
            var sightingYear = sighting.sightingDate[0];
            var sightingDate = sightingMonth + '-' + sightingDay + '-' + sightingYear;

            var sightingSlot = '<span onclick="removeEditSighting(' + sightingID + ')">' +
                    '<li id="editSighting' + sightingID + '" class="editSighting" value="' + sightingID + '">' +
                    sighting.sightingDate[1] + '-' + sighting.sightingDate[2] + '-' + sighting.sightingDate[0] + ' ' +
                    sighting.location.locationName + '</li></span>';

            var currentSightings = new Array();
            $('.editSighting').each(function () {
                var sightingID = $(this).val();
                currentSightings.push(sightingID);
            });
            if (!currentSightings.includes(sightingID)) {
                $('#editSuperSightings').append(sightingSlot);
            }
        },
        error: function () {
            console.log("error selecting that sighting");
        }
    });
}