$(document).ready(() => {
    
    $("#products-types li").click(function (){
        check(this, "#products-types li");
    });
    
    $("#left-menu-nav > li").click(function (){
        if($(this).text() === $("#products-types").text()) {return;}
    });
    
    $("#manufs > *").click(function (){
        check(this, "#manufs > *");
    });
    
        
});

function show(el) {
    if ($(el).hasClass("disabled")) {
        $(el).removeClass("disabled");
    } else {
        $(el).addClass("disabled");
    }
}

function check(el, mass) {
    if (!$(el).hasClass("checked")) {
        clearChecked(mass);
        $(el).addClass("checked");
    } else {
        clearChecked(mass);
    }
}

function clearChecked(el) {
    $(el).map(function (){
        $(this).removeClass("checked");
    });
}
/*
function send(el) {
    $.ajax({
        url: "Servlet",
        type: "POST", 
        data: {manuf: "qwerty"},
        complete: function(data) {
                $("#left-menu-logo").html(data);
        }
    });     
}
*/