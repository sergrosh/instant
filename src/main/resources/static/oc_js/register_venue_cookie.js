
// Run on page load
    window.onload = function() {

        // If sessionStorage is storing default values (ex. name), exit the function and do not restore data
        if (sessionStorage.getItem('name') == "name") {
            return;
        }

        if($('#venue_name_field').length){
                // If values are not blank, restore them to the fields
                console.log("loading data once");
                var name = sessionStorage.getItem('name');
                if (name !== null) $('#venue_name_field').val(name);

                var email = sessionStorage.getItem('email');
                if (email !== null) $('#inputEmail').val(email);

                var subject= sessionStorage.getItem('subject');
                if (subject!== null) $('#inputSubject').val(subject);

                var message= sessionStorage.getItem('message');
                if (message!== null) $('#inputMessage').val(message);

                sessionStorage.clear();
        }

    }

    // Before refreshing the page, save the form data to sessionStorage
    window.onbeforeunload = function() {
        if($('#venue_name_field').length){
                console.log("saving data once");
                sessionStorage.setItem("name", $('#venue_name_field').val());
                sessionStorage.setItem("email", $('#inputEmail').val());
                sessionStorage.setItem("subject", $('#inputSubject').val());
                sessionStorage.setItem("message", $('#inputMessage').val());
        }

    }
