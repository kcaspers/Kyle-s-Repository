/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    console.log("addpost script is loaded");
    
    $('#searchForm').submit(function (event) {
        alert("search form submitted");
    });

});

function aboutButton() {
    var aboutlist = document.getElementById("about");
    if (aboutlist.className.indexOf("w3-show") == -1) {
        aboutlist.className += " w3-show";
    } else {
        aboutlist.className = x.className.replace(" w3-show", "");

    }
}
function addTag() {
    var inputValue = document.getElementById('getTags').value;
    $('#addTags').append(inputValue + " ");
    document.getElementById('getTags').value = "";
}



function addPhoto() {
    var photoVal = $('#addPostPhoto').val();
    //$('#photoInput').val(photoVal);
    $('#photoInput').attr("src", photoVal);

}
function addEditPhoto() {
    var photoVal = $('#addPostPhoto').val();
    $('#photoInput').val(photoVal);

}

$('#newPostForm').submit(function () {
    var category = $('#newPostCategory').val();
    if (category === undefined || category === "") {
        alert("Please select a post category");
        return false;
    }
});




