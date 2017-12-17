/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    console.log("manage posts script is loaded");



});

$('.publishPostForm').submit(function () {
    var id = this.id;
    var expDate = $('#expDate' + id).val();
    if (expDate === undefined || expDate === "") {
        alert("Please enter a valid date");
        return false;
    } else {
    }
});

//function checkDate(blogPostID){
//    console.log(blogPostID);
//    var expDate = $('#expDate' + blogPostID).val();
//    if (expDate === undefined || expDate === "") {
//        alert("Please enter a valid date");
//    } else{
//        $('#publishPostForm').submit();
//    }
//}

