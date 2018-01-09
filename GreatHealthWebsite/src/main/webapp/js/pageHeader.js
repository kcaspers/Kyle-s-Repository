/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    $('#pageTitle').append('<h3>' + parent.document.title + '</h3>')
    //var notMenu = $('div').not('.sideMenu');
    //var notMenu = $('body').not('#dontBlur');
    let count = 1;
    $('#menulink').click(function () {
        if (count % 2 != 0)
        {
            $('#left-menu').css('z-index', 1);
            $('#left-menu').animate({"marginLeft": "-15px"}, 500, function () {
                //$('#menulink').html("Close");
            });
            $('.canBlur').fadeTo(500, 0.5);

            count++;
        } else
        {
            $('#left-menu').animate({"marginLeft": "-30%"}, 500, function () {
                //$('#menulink').html("Menu");
            });
            $('.canBlur').fadeTo(500, 1);
            count++;
        }
    });
});