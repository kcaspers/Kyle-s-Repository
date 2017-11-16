/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    console.log("Sightings script is working");
    loadSightings();
});


//function removeSuper(superID) {
//    
//    $('#addSuper' + superID).remove();
//}

function removeAddSuper(elementId) {
    $('#addSuper' + elementId).remove();
}

function removeEditSuper(elementId) {
    $('#editSuper' + elementId).remove();
    //this should alter the supers in the DB
    var sightingID = $('#edit-sighting-ID').val();
    var locationID = $('#editLocationList').val();

    var removeSuperObj = {
        sightingID: sightingID,
        superheroID: elementId
    };

    $.ajax({
        type: 'PUT',
        url: 'http://localhost:8080/Superheroes/removeFromSighting',
        data: JSON.stringify(removeSuperObj),
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        success: function () {

        },
        error: function () {

        }
    });
}

function loadSightings() {
    $('#sightingList').empty();
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/Superheroes/sightings',
        success: function (sightingList) {
            $.each(sightingList, function (index, sighting) {
                console.log(sightingList);
                var sightingID = sighting.sightingID;
                var locationName = sighting.location.locationName;
                var sightingDate = sighting.sightingDate;

                var sightingSlot = '<li id="selectList">' + '<span onclick="viewSightingDetails(' + sightingID +
                        ')">' + sightingDate[2] +
                        '-' + sightingDate[1] + '-' + sightingDate[0] + ' at ' + locationName + '</span>' + '</li>';
                $('#sightingList').append(sightingSlot);
            });
        },
        error: function () {
            console.log("error loading sightings");
        }
    });
}

function viewSightingDetails(sightingID) {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/Superheroes/sighting/' + sightingID,
        success: function (sighting) {
            var sightingID = sighting.sightingID;
            var locationName = sighting.location.locationName;
            var sightingYear = sighting.sightingDate[0];
            var sightingMonth = sighting.sightingDate[1];
            //the following code is needed for the date input to display properly
            if (sightingMonth < 10) {
                sightingMonth = "0" + sightingMonth;
            }
            var sightingDay = sighting.sightingDate[2];
            if (sightingDay < 10) {
                sightingDay = "0" + sightingDay;
            }
            var sightingDate = sightingYear + '-' + sightingMonth + '-' + sightingDay;
            var sightingDateDisplay = sightingMonth + '-' + sightingDay + '-' + sightingYear;
            //set up the details section
            $('#locationName').empty();
            $('#locationName').append(locationName);
            $('#sightingDate').empty();
            $('#sightingDate').append(sightingDateDisplay);
            $('#edit-sighting-ID').val(sightingID);
            $('#sightingID').empty();
            $('#sightingID').append(sightingID);
            $('#edit-sighting-date').val(sightingDate.toString());
            $('#sightingDetailsMain').css('visibility', 'visible');

            $.ajax({
                type: 'GET',
                url: 'http://localhost:8080/Superheroes/sighting/superheroes/' + sightingID,
                success: function (superList) {
                    $('#supersPresentList').empty();
                    $('#editSupersPresentTable').empty();
                    var superheroes = superList;
                    $.each(superheroes, function (index, superhero) {
                        var superheroID = superhero.superheroID;
                        var superRow = '<li>' + superhero.superheroName + '</li>';
                        $('#supersPresentList').append(superRow);

                        var editSuperSlot = '<span onclick="removeEditSuper(' + superheroID + ')">' +
                                '<li class="editSuper" id="editSuper' + superheroID + '"  value="' + superheroID + '">'
                                + superhero.superheroName + '</li></span>';
                        $('#editSupersPresentTable').append(editSuperSlot);
                    });
                },
                error: function () {
                    console.log("error getting supers by sighting");
                }
            });
        },
        error: function () {
            console.log("error getting sighting details");
        }

    });
}

function selectSuper(superID) {
    //var superID = superID;
    //get this super and put it on supersAddedList as a <li>
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/Superheroes/superhero/' + superID,
        success: function (superhero) {
            var superheroName = superhero.superheroName;
            var superheroID = superhero.superheroID;

            var superAddedSlot = '<span onclick="removeAddSuper(' + superheroID + ')">' +
                    '<li class="addSuper" id="addSuper' + superheroID + '"  value="' + superheroID + '">'
                    + superheroName + '</li></span>';


            var currentSupers = new Array();
            $('.addSuper').each(function () {
                var superheroID = $(this).val();
                currentSupers.push(superheroID);
            });

            if (!currentSupers.includes(superheroID)) {
                $('#supersAddedList').append(superAddedSlot);
            }
        },
        error: function () {
            console.log("error selecting that superhero");
        }


    });
}

function selectEditSuper(superID) {
    console.log("select super getting called");
    var superID = superID;
    //get this super and put it on supersAddedList as a <li>
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/Superheroes/superhero/' + superID,
        success: function (superhero) {
            var superheroName = superhero.superheroName;
            var superheroID = superhero.superheroID;
            var superAddedSlot = '<span onclick="removeEditSuper(' + superheroID + ')">' +
                    '<li class="editSuper" id="editSuper' + superheroID + '" value="' + superheroID + '">'
                    + superheroName + '</li></span>';

            var currentSupers = new Array();
            $('.editSuper').each(function () {
                var superheroID = $(this).val();
                currentSupers.push(superheroID);
            });

            if (!currentSupers.includes(superheroID)) {
                $('#editSupersPresentTable').append(superAddedSlot);
            }
        },
        error: function () {
            console.log("error selecting that superhero");
        }
    });
}

function  supplyEditData() {

    $('#addLocationList').empty();
    //$('#editLocationList').empty();
    $('#addHeroesList').empty();
    $('#editHeroesList').empty();

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/Superheroes/locations',
        success: function (locations) {
            $.each(locations, function (index, location) {
                var locationID = location.locationID;
                var locationName = location.locationName;
                var locationSlot = '<option value="' + locationID + '">' + locationName + '</option>';

                $('#editLocationList').append(locationSlot);
                $('#addLocationList').append(locationSlot);
            });

        },
        error: function () {
            console.log("error loading locations");
        }
        //get all supers
    });
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/Superheroes/superheroes',
        success: function (superheroes) {
            $.each(superheroes, function (index, superhero) {
                var superID = superhero.superheroID;
                var superName = superhero.superheroName;
                //this will populate the drop-down menus for the add and edit sighting modals
                var superSlot = '<option value=' + superID + '>' + superID + ' ' + superName +
                        '</option>';

                $('#addHeroesList').append(superSlot);
                $('#editHeroesList').append(superSlot);
            });
        },
        error: function () {
            console.log("error loading all superheroes");
        }
    });

    //get the details for this particular sighting
    var sightingID = $('#sightingID').text();
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/Superheroes/sighting/' + sightingID,
        success: function (sighting) {
            var sightingDate = new Date(sighting.sightingDate);
            var sightingID = sighting.sightingID;
            var locationID = sighting.location.locationID;
            $('#editLocationList').val(locationID);

            //$('#edit-sighting-ID').val(sighting.sightingID);
        },
        error: function () {
            console.log("error loading that sighting");
        }
    });

    $('#editSupersPresentList').empty();
    //need to get all supers associated with sighting
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/Superheroes/sighting/superheroes/' + sightingID,
        success: function (superheroes) {
            $.each(superheroes, function (index, superhero) {
                var superheroID = superhero.superheroID;
                var superheroName = superhero.superheroName;

                var superheroSlot = '<span onclick="removeSuper(' + superheroID + ')">' +
                        '<li id="superLi' + superheroID + '" value="' + superheroID + '">'
                        + superheroName + '</li></span>';


                $('#editSupersPresentList').append(superheroSlot);
            });
        },
        error: function () {
            console.log("error loading the superheroes to edit");
        }
    });
}



function addSighting() {
    //var sightingLocation = $('#addLocationList').val();
    var locationID = $('#addLocationList').val();
    var superheroID;

    var superheroes = new Array();
    $('.addSuper').each(function () {
        var superheroID = $(this).val();
        superheroes.push(superheroID);
    });
    try {
        var sightingDate = new Date($('#add-sighting-date').val()).toISOString().substring(0, 10);
    } catch (e) {
        alert("Please enter a valid date.");
    }


    var sighting = {
        sightingDate: sightingDate,
        location: {
            locationID: locationID
        }
    };
    var sightingAndHeroes = {
        sighting: sighting,
        superheroes: superheroes
    };

    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/Superheroes/sightings',
        data: JSON.stringify(sightingAndHeroes),
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        success: function () {
            loadSightings();
        },
        error: function () {
            //alert("error creating that sighting");
        }
    });


    //now update all of them so they are associated with this sighting



    //I might also need to seperately associate superheroes with this sighting, get their id's

}

function editSighting() {
    //first get all the values we need
    var sightingDate = $('#edit-sighting-date').val();
    var sightingID = $('#edit-sighting-ID').val();
    var locationID = $('#editLocationList').val();

    var superheroes = new Array();
    $('.editSuper').each(function () {
        var superheroID = $(this).val();
        superheroes.push(superheroID);
    });

    //get all supers from editSupersPresentTable
    var sighting = {
        sightingID: sightingID,
        sightingDate: sightingDate,
        location: {
            locationID: locationID
        }
    };
    var sightingAndHeroes = {
        sighting: sighting,
        superheroes: superheroes
    };

    if (sightingDate === "") {
        alert("Please enter a valid date");
    } else {
        $.ajax({
            type: 'PUT',
            url: 'http://localhost:8080/Superheroes/sighting',
            data: JSON.stringify(sightingAndHeroes),
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            success: function () {
                //alert("successfully connected with rest controller");

            },
            error: function () {
                //alert("error updating that sighting");
            }
        });
        loadSightings();
    }



}


function deleteSighting() {
    var sightingID = $('#sightingID').text();
    //console.log(sightingID);
    $.ajax({
        type: 'DELETE',
        url: 'http://localhost:8080/Superheroes/sighting/' + sightingID,
        success: function () {
            loadSightings();
            $('#sightingDetailsMain').css('visibility', 'hidden');
        },
        error: function () {
            console.log("Error deleting that sighting.");
        }
    });
}
