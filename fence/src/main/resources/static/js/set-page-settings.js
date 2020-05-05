
const mainResource = 'http://localhost:8080/';

window.onload = function () {
    getDefaultMinGap();
    fillSelectors();
    // Show users threshold
    document.getElementById('sldr-users-threshold').value = document.getElementById('sldr-default-threshold').value;
    document.getElementById('users-threshold').innerHTML = document.getElementById('sldr-users-threshold').value;
}


function getDefaultMinGap() {

    const resMinGapDefault = mainResource + 'MinGap/default';

    // Show default threshold
    $.ajax({
        url: resMinGapDefault,
        type: 'GET',
        beforeSend: function () {
            document.body.style.cursor = 'wait';
        }
    }).done(function (data, textStatus, jqXHR) {
        document.getElementById('sldr-default-threshold').value = data;
        document.getElementById('default-threshold').innerHTML = data;
        document.body.style.cursor = 'default';
    });

}


function fillSelectors() {

    const resEmployees = mainResource + 'Employees/';
    const resProjects = mainResource + 'Projects/';

    // Fill users select box
    $.ajax({
        url: resEmployees,
        type: 'GET',
        beforeSend: function () {
            document.body.style.cursor = 'wait';
        }
    }).done(function (data, textStatus, jqXHR) {

        var options = [];

        for (let i = 0; i < data.length; i++) {
            options[i] = data[i]['dni'];
        }

        var selUser1 = document.getElementById('select-user1');
        var selUser2 = document.getElementById('select-user2');

        options.forEach(function (element, key) {
            selUser1[key] = new Option(element, key);
            selUser2[key] = new Option(element, key);
        });

        document.body.style.cursor = 'default';

    });

    // Fill projects select boxes
    $.ajax({
        url: resProjects,
        type: 'GET',
        beforeSend: function () {
            document.body.style.cursor = 'wait';
        }
    }).done(function (data, textStatus, jqXHR) {

        var options = [];

        for (let i = 0; i < data.length; i++) {
            options[i] = data[i]['name'];
        }

        var selProject = document.getElementById('select-project');
        var selProjectReset = document.getElementById('select-project-reset');

        options.forEach(function (element, key) {
            selProject[key] = new Option(element, key);
            selProjectReset[key] = new Option(element, key);
        });

        document.body.style.cursor = 'default';

    });

}