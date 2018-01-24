/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function () {
    console.log('hidepicture called');

    var windowSize = $(window).width();
    if (windowSize < 767) {
        $('#imageCol').css('display', 'none');
    }
    if (windowSize > 767) {
        $('#imageCol').css('display', 'block');
    }
});

$(window).resize(function hidePicture() {
    console.log('hidepicture called');

    var windowSize = $(window).width();
    if (windowSize < 767) {
        $('#imageCol').css('display', 'none');
    }
    if (windowSize > 767) {
        $('#imageCol').css('display', 'block');
    }
});