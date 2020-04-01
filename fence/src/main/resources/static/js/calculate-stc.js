const mainResource = 'http://localhost:8080/';

document.getElementById('btn-calculate-stc').addEventListener('click', function () {

    project = 'FENCE'; // TODO: GET PROJECT

    // Calculate STC
    document.getElementById('loading-label').innerHTML = 'Calculating STC...';

    const resSTC = mainResource + 'STC/' + project;
    calculateSTC(resSTC);

    // Retrieve data
    const resSTCEmployees = mainResource + 'EmployeesSTC/project/' + project;
    const resSTCTeams = mainResource + 'TeamsSTC/project/' + project;
    const restSTCProjects = mainResource + 'ProjectSTC/' + project;

    getDataSTCEmployees(resSTCEmployees, 'table-stc-employees');
    getDataSTCTeams(resSTCTeams, 'table-stc-teams');
    getDataSTCProject(restSTCProjects, 'table-stc-projects');

    document.getElementById('loading-label').innerHTML = '';
    document.getElementById('loading-label').style.color='black';
});

// Petition to STC measurer
function calculateSTC(resource) {
    $.ajax({
        url: resource,
        type: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    }).done(function (data, textStatus, jqXHR) {
        if (data == true) {
            document.getElementById('loading-label').innerHTML = 'Done!';
            document.getElementById('loading-label').style.color='green';
        } else {
            document.getElementById('loading-label').innerHTML = 'Error!';
            document.getElementById('loading-label').style.color='red';
        }
    });
}

// Get employees STC registers and fill table
function getDataSTCEmployees(resource, tableId) {

    $.ajax({
        url: resource,
        type: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    }).done(function (data, textStatus, jqXHR) {

        // Fill table
        const bodyRef = '#' + tableId + ' > tbody';
        const tableBody = document.querySelector(bodyRef);

        // Clear table
        while (tableBody.firstChild) {
            tableBody.removeChild(tableBody.firstChild);
        }

        var rows = '';

        // Load records
        for (let i = 0; i < data.length; i++) {
            rows +=
                `<tr class='hide'>
                    <td class='pt-3-half'>` + data[i]['dni'] + `</td>
                    <td class='pt-3-half'>` + data[i]['stc'] + `</td>
                </tr>`;
        }
        $(tableBody).append(rows);
    });

}

// Get teams STC registers and fill table
function getDataSTCTeams(resource, tableId) {

    $.ajax({
        url: resource,
        type: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    }).done(function (data, textStatus, jqXHR) {

        // Fill table
        const bodyRef = '#' + tableId + ' > tbody';
        const tableBody = document.querySelector(bodyRef);

        // Clear table
        while (tableBody.firstChild) {
            tableBody.removeChild(tableBody.firstChild);
        }

        var rows = '';

        // Load records
        for (let i = 0; i < data.length; i++) {
            rows +=
                `<tr class='hide'>
                    <td class='pt-3-half'>` + data[i]['team'] + `</td>
                    <td class='pt-3-half'>` + data[i]['stc'] + `</td>
                </tr>`;
        }
        $(tableBody).append(rows);
    });

}

// Get project STC registers and fill table
function getDataSTCProject(resource, tableId) {

    $.ajax({
        url: resource,
        type: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    }).done(function (data, textStatus, jqXHR) {

        // Fill table
        const bodyRef = '#' + tableId + ' > tbody';
        const tableBody = document.querySelector(bodyRef);

        // Clear table
        while (tableBody.firstChild) {
            tableBody.removeChild(tableBody.firstChild);
        }

        var rows = '';

        // Load records
        for (let i = 0; i < data.length; i++) {
            rows +=
                `<tr class='hide'>
                    <td class='pt-3-half'>` + data[i]['project'] + `</td>
                    <td class='pt-3-half'>` + data[i]['stc'] + `</td>
                </tr>`;
        }
        $(tableBody).append(rows);
    });

}

