/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

if (sessionStorage.getItem('popOnce') !== 'true') {
    
    setTimeout(function () {
        //alert("Home popup function triggered"); 
        //this should open the welcomePopup modal
        $('#modalTrigger').click();
        //console.log(alreadyClicked);
    }, 3000);
    sessionStorage.setItem('popOnce', 'true');
}



