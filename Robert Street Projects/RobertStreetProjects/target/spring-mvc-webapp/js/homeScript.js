/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
if (sessionStorage.popupTriggered === false) {
    setTimeout(function () {
        //alert("Home popup function triggered"); 
        //this should open the welcomePopup modal
        $('#modalTrigger').click();
        //console.log(alreadyClicked);
        sessionStorage.popupTriggered = true;
    }, 2000); 
}



