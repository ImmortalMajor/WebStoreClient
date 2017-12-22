$(document).ready(() => {
    
   // $("#basket").addClass("disabled");
    //alert(window.location.href);
    if(window.location.href !== "http://localhost:8080/WebStoreClient/faces/main.xhtml")
        $("#products-types").css("display", "none");
    else
        $("#products-types").css("display", "block");
    
    $("#products-types li").click(function (){
        check(this, "#products-types li");
    });
    
    $("#left-menu-nav > li").click(function (){
        if($(this).text() === $("#products-types").text()) {return;}
    });
    
    $("#manufs > *").click(function (){
        check(this, "#manufs > *");
    });
    
    $("textarea.textarea").keyup(function() {
        var $textarea = $(this);
        var max = 300;

        if ($textarea.val().length > max) {
            $textarea.val($textarea.val().substr(0, max));
        }
    });
    
    $("textarea.textarea").focusout(function (){
        if($(this).val().length < 1)
            $(this).css("box-shadow", "0 0 0 1px rgb(255,0,0) inset");
        else
            $(this).css("box-shadow", "0 0 0 1px rgb(30,30,30) inset");
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

function email(el){
    if($(el).val().search(/^(\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,6})$/) === -1)
        $(el).css("box-shadow", "0 0 0 1px rgb(255,0,0) inset");
    else
        $(el).css("box-shadow", "0 0 0 1px rgb(30,30,30) inset");
}

function fio(el){
    if($(el).val().search(/^([A-ZА-Яa-zа-я_ ]{1,})$/) === -1)
        $(el).css("box-shadow", "0 0 0 1px rgb(255,0,0) inset");
    else
        $(el).css("box-shadow", "0 0 0 1px rgb(30,30,30) inset");
}

function phone(el){
    if($(el).val().search(/^(\+38([0-9]{3})[0-9]{3}[0-9]{4})$/) === -1)
        $(el).css("box-shadow", "0 0 0 1px rgb(255,0,0) inset");
    else
        $(el).css("box-shadow", "0 0 0 1px rgb(30,30,30) inset");
}

function sizeValid(el) {
    if($(el).val() < 38 || $(el).val() > 56) $("#sizeValid").html("<small>*размер должен быть не менее 38 и не более 56</small>");
    else $("#sizeValid").empty();   
}

function amountValid(el) {
    if($(el).val() < 1) $("#amountValid").html("<small>*количество не может быть меньше 1</small>");
    else $("#amountValid").empty(); 
}

function commentValid() {
 
}