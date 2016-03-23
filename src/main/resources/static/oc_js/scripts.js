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


$(function(){
    $('.btn.add-schedule').click(function(){
        if($(".row.schedule_row.second").css('display') == 'none'){
                $(".row.schedule_row.second").css('display', 'block');
                return;
            }

            if($(".row.schedule_row.third").css('display') == 'none'){
                $(".row.schedule_row.third").css('display', 'block');
                $(".btn.add-schedule").addClass('disabled');
                return;
            }
    })
});

$(function(){
    $('.btn.add-menu-category').click(function(){
        if($(".row.menu_category.second").css('display') == 'none'){
                $(".row.menu_category.second").css('display', 'block');
                return;
            }

            if($(".row.menu_category.third").css('display') == 'none'){
                $(".row.menu_category.third").css('display', 'block');
                $(".btn.add-menu-category").addClass('disabled');
                return;
            }
    })
});