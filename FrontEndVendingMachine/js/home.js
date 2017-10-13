jQuery(document).ready(function(){
loadItems();


});

function loadItems(){
//clearInventoryTable();
var vendingRows = $('#vendingRows');
$('#vendingRows').append('<tr class="horizontalElement" id = "row0"></tr>');
var counter = 0;
var rowNumber = 0;
$.ajax({
    type: 'GET',
    url: 'http://localhost:8080/items',
    success: function(itemArray){
        $.each(itemArray, function(index, item){
            //need ID, Name, cost and quantity
            var itemId = item.id;
            var itemName = item.name;
            var itemPrice = item.price;
            var itemQuantity = item.quantity;

            //each row will have three slots
            var itemInfo = '<div class="itemElement">'
                            + '<div>' + itemId + '</div>'
                            + '<h4 style="text-align: center">' + itemName + '</h4>'
                            + '<div style="text-align: center">$' + itemPrice + '</div>'
                            + '<div style="text-align: center">Quantity left: ' + itemQuantity + '</div>' 
                            + '</div>';
                        
            var itemSlot = '<td onclick="addToPurchase('+itemId+')">' + itemInfo + '</td>';
            var rowToAppend = 'row' + rowNumber;
            
            counter++;
            //if we have three slots filled, make a new row
            //ideally it should be 'three slots filled with more data on its way'
            if(counter != 1 && counter % 3 == 0){
                $('#' + rowToAppend).append(itemSlot);
                rowNumber++;
                rowToAppend = 'row' + rowNumber;
                $('#vendingRows').append('<tr class="horizontalElement" id="'+ rowToAppend + '"></tr>');
                
            } else{
                $('#' + rowToAppend).append(itemSlot);
            }
        })
        $('tr').each(function(index){
            var cell = $(this);
            if($(cell).find('td').length==0){
                $(this).remove();
            }
        });
    },
    error: function(){
        alert("something went wrong!");
    }
})
$(function() {
    $('.vendingColumn').matchHeight();
});

$(function(){
    $('.horizontalElement').matchHeight();
});
}
var moneyTotal = 0;

function addDollar(){
moneyTotal += 1.00;     
$('#totalMoney').val(Number((moneyTotal).toFixed(2))); 
}
function addQuarter(){
moneyTotal += 0.25;  
$('#totalMoney').val(Number((moneyTotal).toFixed(2))); 
}
function addDime(){
moneyTotal += 0.10; 
$('#totalMoney').val(Number((moneyTotal).toFixed(2)));   
}
function addNickel(){
moneyTotal += 0.05;  
$('#totalMoney').val(Number((moneyTotal).toFixed(2))); 
}

function addToPurchase(itemId){
    $('#itemInput').val(itemId);
}

function makePurchase(){
    var itemToPurchase = $('#itemInput').val();
    var money = $('#totalMoney').val();
    var changeToReturn = "";
    if(money == "" || money == 0){
        $('#messagesTextArea').val("Please input money.");
    }else if(!$('#itemInput').val()){
        $('#messagesTextArea').val("No item selected");
    }else{
        $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/money/' + money + '/item/' + itemToPurchase +'',
        success: function(change){
            console.log(change);
            $('#messagesTextArea').val("Thank You!");
            //display the change by looping through data
            $.each(change, function(k, v){
                if(v > 0){
                changeToReturn += v + " " + k + "\n";
                }
            })
            $('#changeTextArea').val(changeToReturn);
            //clear out their money so they cant keep buying things
            $('#totalMoney').val(0);
            moneyTotal = 0;

            clearInventoryTable();
            loadItems();
            
        },
        error: function(data){
            console.log(data.responseJSON.message);
            $('#messagesTextArea').val(data.responseJSON.message);
        }
    })
    }
}

 function changeReturn(){
//clear messages, item, total amount
$('#messagesTextArea').val("");
$('#itemInput').val("");

//need to give them back their money
var moneyDue = $('#totalMoney').val() * 100; //this is the total penny value e.g. 325
var quartersDue = Math.floor(moneyDue/25);
moneyDue = moneyDue - (quartersDue * 25);
var dimesDue = Math.floor(moneyDue/10);
moneyDue = moneyDue - (dimesDue * 10);
var nickelsDue = Math.floor(moneyDue/5);
moneyDue = moneyDue - (nickelsDue * 5);
var penniesDue = moneyDue;

var changeToReturn = "";
if(quartersDue > 0){
    changeToReturn += quartersDue + " quarters\n";
}
if(dimesDue > 0){
    changeToReturn += dimesDue + " dimes\n";
}
if(nickelsDue > 0){
    changeToReturn += nickelsDue + " nickels\n";
}
if(penniesDue > 0){
    changeToReturn += penniesDue + " pennies\n";
}

$('#changeTextArea').val(changeToReturn);
$('#totalMoney').val("");
moneyTotal = 0;
}

function clearInventoryTable(){
$('#vendingRows').empty();
}