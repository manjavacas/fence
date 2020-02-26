function checkLogin() {

    var user = document.getElementById("tfUsername").value;
    var pass = document.getElementById("tfPassword").value;

    var credentials = { username: user, password: pass };
    credentials = JSON.stringify(credentials);

    var resource = "http://localhost:8080/login";

    setTimeout(
        $.ajax({
            url: resource,
            type: "POST",
            data: credentials,
            headers: {
                "Content-Type": "application/json"
            }
        }).done(function (data, textStatus, jqXHR) {
            if (data.type == "OK") {
                location.href = "views/main.html";
            } else {
                location.href = "login.html";
            }
        }), 1);

}

