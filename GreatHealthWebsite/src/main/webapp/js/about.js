/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//function hidePicture(){
//    var windowSize = $(window).width();
//    if(windowSize < 990){
//        $('#imageCol').css('display: none');
//    }
//    if(windowSize > 990){
//        $('#imageCol').css('display: block');
//    }
//}

$(window).ready(function hidePicture(){
    console.log('hidepicture called');
    
    var windowSize = $(window).width();
    if(windowSize < 768){
        $('#imageCol').css('display', 'none');
    }
    if(windowSize > 768){
        $('#imageCol').css('display', 'block');
    }
});

$(window).resize(function hidePicture(){
    console.log('hidepicture called');
    
    var windowSize = $(window).width();
    if(windowSize < 768){
        $('#imageCol').css('display', 'none');
    }
    if(windowSize > 768){
        $('#imageCol').css('display', 'block');
    }
});