function checkLogin() {

    var user = document.getElementById("tfUsername").value;
    var pass = document.getElementById("tfPassword").value;

    if (user == "") {
        alert("Please, enter an username.");
    } else if (pass == "") {
        alert("Please, enter a password.");
    } else {
        var resource = "http://localhost:8080/login";
        var credentials = { username: user, password: pass };
        credentials = JSON.stringify(credentials);

        $.ajax({
            url: resource,
            type: "POST",
            data: credentials,
            headers: {
                "Content-Type": "application/json"
            }
        }).done(function (data, textStatus, jqXHR) {
            if (data.type == "SUCCESS") {
                location.href = "../views/main.html";
            } else {
                alert("Invalid user or password.");
            }
        });
    }
}

