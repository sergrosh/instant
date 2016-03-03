// Empty JS for your own code to be here

$(function(){


	//make text area bigger
	var textarea_height = $('textarea.expand').height();
    $('textarea.expand').focus(function () {
        $(this).animate({ height: "200px" }, 500);
    });
    $('textarea.expand').focusout(function () {
        $(this).animate({ height: textarea_height }, 500);
    });

});