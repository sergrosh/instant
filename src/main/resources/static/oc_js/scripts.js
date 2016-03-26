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

// Form validation actions - Schedule
$(function(){
    //Disable first row, except Starting Day
    $('.btn.add-schedule').addClass('disabled');
    $('.btn.add-schedule').attr('disabled', 'disabled');
    $("select[name='opening-time_r1_d2']").attr('disabled', 'disabled');
    $("select[name='opening-time_r1_h1']").attr('disabled', 'disabled');
    $("select[name='opening-time_r1_h2']").attr('disabled', 'disabled');
    $("select[name='opening-time_r1_h3']").attr('disabled', 'disabled');
    $("select[name='opening-time_r1_h4']").attr('disabled', 'disabled');

    $("select[name='opening-time_r2_d2']").attr('disabled', 'disabled');
    $("select[name='opening-time_r2_h1']").attr('disabled', 'disabled');
    $("select[name='opening-time_r2_h2']").attr('disabled', 'disabled');
    $("select[name='opening-time_r2_h3']").attr('disabled', 'disabled');
    $("select[name='opening-time_r2_h4']").attr('disabled', 'disabled');

    $("select[name='opening-time_r3_d2']").attr('disabled', 'disabled');
    $("select[name='opening-time_r3_h1']").attr('disabled', 'disabled');
    $("select[name='opening-time_r3_h2']").attr('disabled', 'disabled');
    $("select[name='opening-time_r3_h3']").attr('disabled', 'disabled');
    $("select[name='opening-time_r3_h4']").attr('disabled', 'disabled');

    // First Row

    $("select[name='opening-time_r1_d1']").change(function(){
        if($("select[name='opening-time_r1_d1']").val().length == 0){
            $("select[name='opening-time_r1_d2']").attr('disabled', 'disabled');
            $("select[name='opening-time_r1_d2']").removeAttr('required');
            $("select[name='opening-time_r1_h1']").attr('disabled', 'disabled');
            $("select[name='opening-time_r1_h2']").attr('disabled', 'disabled');
            $("select[name='opening-time_r1_h3']").attr('disabled', 'disabled');
            $("select[name='opening-time_r1_h4']").attr('disabled', 'disabled');
        }
        else{
            $("select[name='opening-time_r1_d2']").removeAttr('disabled');
            $("select[name='opening-time_r1_d2']").attr('required', 'required');
            $("select[name='opening-time_r1_h1']").removeAttr('disabled');
            $("select[name='opening-time_r1_h1']").attr('required', 'required');
            $("select[name='opening-time_r1_h2']").removeAttr('disabled');
            $("select[name='opening-time_r1_h2']").attr('required', 'required');
        }
    })

    //Enable first row start time first make it required
    $("select[name='opening-time_r1_h2']").change(function(){
        if($("select[name='opening-time_r1_h2']").val().length == 0){
            $("select[name='opening-time_r1_h3']").attr('disabled', 'disabled');
            $('.btn.add-schedule').addClass('disabled');
            $('.btn.add-schedule').attr('disabled', 'disabled');
        }
        else{
            $("select[name='opening-time_r1_h3']").removeAttr('disabled');
            $('.btn.add-schedule').removeClass('disabled');
            $('.btn.add-schedule').removeAttr('disabled');
        }
    })

    $("select[name='opening-time_r1_h3']").change(function(){
        if($("select[name='opening-time_r1_h3']").val().length == 0){
            $("select[name='opening-time_r1_h4']").attr('disabled');
            $("select[name='opening-time_r1_h4']").removeAttr('required');
        }
        else{
            $("select[name='opening-time_r1_h4']").removeAttr('disabled');
            $("select[name='opening-time_r1_h4']").attr('required', 'required');
        }
    })


    // Second Row

    $("select[name='opening-time_r2_d1']").change(function(){
            if($("select[name='opening-time_r2_d1']").val().length == 0){
                $("select[name='opening-time_r2_d2']").attr('disabled', 'disabled');
                $("select[name='opening-time_r2_d2']").removeAttr('required');
                $("select[name='opening-time_r2_h1']").attr('disabled', 'disabled');
                $("select[name='opening-time_r2_h2']").attr('disabled', 'disabled');
                $("select[name='opening-time_r2_h3']").attr('disabled', 'disabled');
                $("select[name='opening-time_r2_h4']").attr('disabled', 'disabled');
            }
            else{
                $("select[name='opening-time_r2_d2']").removeAttr('disabled');
                $("select[name='opening-time_r2_d2']").attr('required', 'required');
                $("select[name='opening-time_r2_h1']").removeAttr('disabled');
                $("select[name='opening-time_r2_h1']").attr('required', 'required');
                $("select[name='opening-time_r2_h2']").removeAttr('disabled');
                $("select[name='opening-time_r2_h2']").attr('required', 'required');
            }
        })

    //Enable first row start time first make it required
    $("select[name='opening-time_r2_h2']").change(function(){
        if($("select[name='opening-time_r2_h2']").val().length == 0){
            $("select[name='opening-time_r2_h3']").attr('disabled', 'disabled');
            $('.btn.add-schedule').addClass('disabled');
            $('.btn.add-schedule').attr('disabled', 'disabled');
        }
        else{
            $("select[name='opening-time_r2_h3']").removeAttr('disabled');
            $('.btn.add-schedule').removeClass('disabled');
            $('.btn.add-schedule').removeAttr('disabled');
        }
    })

    $("select[name='opening-time_r2_h3']").change(function(){
        if($("select[name='opening-time_r2_h3']").val().length == 0){
            $("select[name='opening-time_r2_h4']").attr('disabled');
            $("select[name='opening-time_r2_h4']").removeAttr('required');
        }
        else{
            $("select[name='opening-time_r2_h4']").removeAttr('disabled');
            $("select[name='opening-time_r2_h4']").attr('required', 'required');
        }
    })


    //Third Row

    $("select[name='opening-time_r3_d1']").change(function(){
                if($("select[name='opening-time_r3_d1']").val().length == 0){
                    $("select[name='opening-time_r3_d2']").attr('disabled', 'disabled');
                    $("select[name='opening-time_r3_d2']").removeAttr('required');
                    $("select[name='opening-time_r3_h1']").attr('disabled', 'disabled');
                    $("select[name='opening-time_r3_h2']").attr('disabled', 'disabled');
                    $("select[name='opening-time_r3_h3']").attr('disabled', 'disabled');
                    $("select[name='opening-time_r3_h4']").attr('disabled', 'disabled');
                }
                else{
                    $("select[name='opening-time_r3_d2']").removeAttr('disabled');
                    $("select[name='opening-time_r3_d2']").attr('required', 'required');
                    $("select[name='opening-time_r3_h1']").removeAttr('disabled');
                    $("select[name='opening-time_r3_h1']").attr('required', 'required');
                    $("select[name='opening-time_r3_h2']").removeAttr('disabled');
                    $("select[name='opening-time_r3_h2']").attr('required', 'required');
                }
            })

    //Enable first row start time first make it required
    $("select[name='opening-time_r3_h2']").change(function(){
        if($("select[name='opening-time_r3_h2']").val().length == 0){
            $("select[name='opening-time_r3_h3']").attr('disabled', 'disabled');
            $('.btn.add-schedule').addClass('disabled');
            $('.btn.add-schedule').attr('disabled', 'disabled');
        }
        else{
            $("select[name='opening-time_r3_h3']").removeAttr('disabled');
            $('.btn.add-schedule').removeClass('disabled');
            $('.btn.add-schedule').removeAttr('disabled');
        }
    })

    $("select[name='opening-time_r3_h3']").change(function(){
        if($("select[name='opening-time_r3_h3']").val().length == 0){
            $("select[name='opening-time_r3_h4']").attr('disabled');
            $("select[name='opening-time_r3_h4']").removeAttr('required');
        }
        else{
            $("select[name='opening-time_r3_h4']").removeAttr('disabled');
            $("select[name='opening-time_r3_h4']").attr('required', 'required');
        }
    })

});

//AddSchedule button
$(function(){
    $('.btn.add-schedule').click(function(){
        if($(".row.schedule_row.second").css('display') == 'none'){
                $(".row.schedule_row.second").css('display', 'block');
                $(".btn.remove-schedule").removeClass('disabled');
                $(".btn.remove-schedule").removeAttr('disabled');
                return;
            }

        if($(".row.schedule_row.third").css('display') == 'none'){
            $(".row.schedule_row.third").css('display', 'block');
            $(".btn.add-schedule").addClass('disabled');
            return;
        }
    })
});

//RemoveSchedule button
$(function(){
    $(".btn.remove-schedule").addClass('disabled');
    $(".btn.remove-schedule").attr('disabled', 'disabled');

    $('.btn.remove-schedule').click(function(){
        if($(".row.schedule_row.third").css('display') == 'block'){
            $(".row.schedule_row.third").css('display', 'none');
            return;
        }

        if($(".row.schedule_row.second").css('display') == 'block'){
            $(".row.schedule_row.second").css('display', 'none');
            $(".btn.remove-schedule").addClass('disabled');
            $(".btn.remove-schedule").attr('disabled', 'disabled');
            return;
        }
    })
});

//add category button
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