const mainResource = 'http://localhost:8080/';

document.getElementById('btn-calculate-stc').addEventListener('click', function () {
    // Calculate STC
    const resSTC = mainResource + 'STC/' + project;
    calculateSTC(resSTC);
});

// Petition to STC measurer
function calculateSTC(resource) {

    $.ajax({
        url: resource,
        type: 'GET',
        beforeSend: function () {
            document.getElementById('loading-label').innerHTML = 'Calculating STC...';
            document.body.style.cursor = 'wait';
        }
    }).done(function (data, textStatus, jqXHR) {
        if (data == true) {

            document.getElementById('loading-label').innerHTML = 'Done!';
            document.getElementById('loading-label').style.color = 'green';

            // Retrieve data
            project = document.getElementById('select-project').options[document.getElementById('select-project').selectedIndex].text;

            const resSTCEmployees = mainResource + 'EmployeesSTC/project/' + project;
            const resSTCTeams = mainResource + 'TeamsSTC/project/' + project;
            const restSTCProjects = mainResource + 'ProjectsSTC/project/' + project;

            getDataSTCEmployees(resSTCEmployees, 'table-stc-employees');
            getDataSTCTeams(resSTCTeams, 'table-stc-teams');
            getDataSTCProject(restSTCProjects, 'table-stc-projects');

        } else {
            document.getElementById('loading-label').innerHTML = 'Error!';
            document.getElementById('loading-label').style.color = 'red';
        }
        document.body.style.cursor = 'default';
    });
}

// Get employees STC registers and fill table
function getDataSTCEmployees(resource, tableId) {

    $.ajax({
        url: resource,
        type: 'GET',
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
                    <td class='pt-3-half'>` + data[i]['employee'] + `</td>
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
    }).done(function (data, textStatus, jqXHR) {

        // Fill table
        const bodyRef = '#' + tableId + ' > tbody';
        const tableBody = document.querySelector(bodyRef);

        // Clear table
        while (tableBody.firstChild) {
            tableBody.removeChild(tableBody.firstChild);
        }

        // Load records
        var row =
            `<tr class='hide'>
                <td class='pt-3-half'>` + data['project'] + `</td>
                <td class='pt-3-half'>` + data['stc'] + `</td>
            </tr>`;

        $(tableBody).append(row);
    });

}

