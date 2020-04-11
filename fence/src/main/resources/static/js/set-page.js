
window.onload = function () {
    setPage();
}

// Set label and load projects into select box
function setPage() {

    document.getElementById('loading-label').innerHTML = '';
    document.getElementById('loading-label').style.color = 'black';

    loadSTCData();
}

// Load projects in selector
function loadSTCData() {

    const resProjects = mainResource + 'Projects/';
    var sel = document.getElementById('select-project');

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

        options.forEach(function (element, key) {
            sel[key] = new Option(element, key);
        });

        // Load latests meditions
        var project = document.getElementById('select-project').options[document.getElementById('select-project').selectedIndex].text;

        const resSTCEmployees = mainResource + 'EmployeesSTC/project/' + project;
        const resSTCTeams = mainResource + 'TeamsSTC/project/' + project;
        const restSTCProjects = mainResource + 'ProjectsSTC/project/' + project;
    
        getDataSTCEmployees(resSTCEmployees, 'table-stc-employees');
        getDataSTCTeams(resSTCTeams, 'table-stc-teams');
        getDataSTCProject(restSTCProjects, 'table-stc-projects');

        document.body.style.cursor = 'default';
    });
}
