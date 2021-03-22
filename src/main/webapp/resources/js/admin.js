//admin.js

//header mainmenu toggle
$(document).ready(function () {
    $(".mainmenu a:nth-child(5)").click(function() {
        var top = $(".mainmenu").offset().top + $(".mainmenu").height();
        var left = $(".mainmenu a:nth-child(5)").offset().left;
        $("#dropmenu").width($(".mainmenu a:nth-child(5)").width());
        $("#dropmenu").css("top", top);
        $("#dropmenu").css("left", left);
        $("#dropmenu").slideToggle(500);
    });
});