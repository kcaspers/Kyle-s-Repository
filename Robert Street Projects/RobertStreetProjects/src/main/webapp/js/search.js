/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    //console.log("search script is working");

    $('#searchButton').click(function (event) {
        console.log($('#searchBar').val());
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/RobertStreetProjects/search',
            data: JSON.stringify({
                searchTerm: $('#searchBar').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json',
            success: function (data) { //make sure the controller method sends back the list
                alert("success");
                //need to send to search results page (main page?) and fill it with the correct list
            },
            error: function () {
                
            }
        });

    });


});