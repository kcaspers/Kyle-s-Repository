/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    console.log("ohm.js is present.");
});

//function selectAmpOhm(){
//    var calculatedImpedance = $('#calculatedImpedance');
//    var ampOhm;
//    console.log("ampOhm() is being called");
//    console.log(calculatedImpedance);
//}

//this will display all of the conditional cabinet stuff
var cabinetsPresent = $('#cabPresent').val();
if (cabinetsPresent === "true") {
    console.log("There are cabinets");
    //$('#calculatedImpedance').css({'visibility': 'visible'});
    $('.displayWhenSpeakers').css({'visibility': 'visible'});
}

function closeModal() {
    //console.log("called closeModal");
    $('#saveLoadModal').modal('hide');
}

function selectAmpOhm() {
    var ampOhm = $("input[name = 'ampOhm']:checked").val();

    //console.log(ampOhm);
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/SpeakerOhmTool/selectAmpOhm/' + ampOhm + '',
        data: JSON.stringify({
            ampOhm
        }),
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        success: function () {
            console.log("Success");
        },
        error: function () {
            console.log("Success");
        }
    });
}
