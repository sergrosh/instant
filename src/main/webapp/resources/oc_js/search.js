var suggestionClicked=false;
    $(function () {
      $('#category_hidden').val("all");
    });
/*
    $('#removing_keeps_structure').remove();
*/
    $(function () {
      $('#suggestionText').css({
            position: "absolute",
            top: ($('#query_input').offsetTop + $('#query_input').offsetHeight) + "px",
            left: $('#query_input').offsetLeft + "px"
        });
    });

    function setCategoryValue(data){
        $('#category_hidden').val(data);
        $('#category_visible').text(data);
    }

    function retrieveGuests() {
        var url = '/clients?query=' + $('#search_query').val();
        var data = $('#filterForm').serialize();
        url = url + '&view=2&' + data;
        $("#itemlist_results").load(url);
    }


    $( "#query_input" ).keyup(function() {

        $( "#suggestionList" ).empty();

        if($("#query_input").val().trim().length===0){
          $('#suggestionBox').hide();
          $( "#suggestionList" ).empty();
        }

        else{
            dynamic_width = $('#query_input').width() + 18;
            $('#suggestionBox').css({
                position: "absolute",
                top: (this.offsetTop + this.offsetHeight) + "px",
                left: this.offsetLeft + "px",
                width: dynamic_width + "px"
            });

            $.ajax({
              type: "GET",
              url: "/suggestions",
              data:
              {
                query: $('#query_input').val()
              },
              cache: false,
              success: function(data){
                for (var i = 0; i < data.venues.length; i++) {
                    $('#suggestionList').append( '<li style="margin: 4px; padding: 4px;"> <a href="javascript:void(0)" style="padding:5px;" onmousedown="triggerSearch(' + '&#39' + (data.venues[i].name).toString() + '&#39' +'); return false;">' + data.venues[i].name + '</a> </li>' );
                }
              }
            });

            $('#suggestionBox').show();
        }

    });

    function triggerSearch(data){
        suggestionClicked = true;
        $('#query_input').val(data);
        $('#search_form').trigger('submit');
    }

    function triggerSeoSearch(data){
        $('#query_input').val(data);
        $('#search_form').trigger('submit');
    }

    $( "#query_input" ).focusout(function() {
        if(!suggestionClicked){
            $('#suggestionBox').hide();
            $( "#suggestionList" ).empty();
        }

    })