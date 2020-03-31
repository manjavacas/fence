const mainResource = 'http://localhost:8080/';

document.getElementById('btn-calculate-stc').addEventListener('click', function () {

    const resSTCEmployees = mainResource + 'EmployeeSTCMeditions/';
    const restSTCProjects = mainResource + 'ProjectSTCMeditions/';

    getDataSTCEmployees(resSTCEmployees, 'table-stc-employees');
    getDataSTCTeams(resSTCTeams, 'table-stc-teams');
    getDataSTCProjects(restSTCProjects, 'table-stc-projects');

});

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

function getDataSTCProjects(resource, tableId) {

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