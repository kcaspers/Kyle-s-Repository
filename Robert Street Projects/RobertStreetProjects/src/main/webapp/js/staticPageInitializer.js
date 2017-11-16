/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    console.log("the static page script is working");

   
    loadStaticPages();

});

//I need the header element to contain an li for each static page
function loadStaticPages() {
    //we may need to empty the staticPageList before we start throwing stuff in there
    $('#staticPageList').empty();
    
    $.ajax({
         type: 'GET',
         url: 'http://localhost:8080/RobertStreetProjects/staticPages',
         success: function (staticpages){
             $.each(staticpages, function(index, page){
                 //console.log(page);
                 var staticPageListItem = '<li><a href="/RobertStreetProjects' + 
                         '/displayStaticPage/' + page.staticPageId + '">' + page.name + '</a></li>';
                 
                 $('#staticPageList').append(staticPageListItem);
             });
             
             
         },
         error: function(){
             console.log("error loading static pages");
         }
         
        
        
    });
}

