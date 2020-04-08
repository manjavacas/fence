
const $tableTeamsID = $('#tableTeams');
const $tabTeams = $('#tabTeams');

const newTrTeam = `
<tr class='hide'>
  <td class='pt-3-half' contenteditable='true'></td>
  <td class='pt-3-half' contenteditable='true'></td>
  <td class='pt-3-half' contenteditable='true'></td>
  <td class='pt-3-half'>
    <span class='table-up'><a href='#!' class='indigo-text'><i class='fas fa-long-arrow-alt-up' aria-hidden='true'></i></a></span>
    <span class='table-down'><a href='#!' class='indigo-text'><i class='fas fa-long-arrow-alt-down' aria-hidden='true'></i></a></span>
  </td>
  <td>
    <span class='table-remove-team'><button type='button' class='btn btn-danger btn-rounded btn-sm my-0 waves-effect waves-light'>Remove</button></span>
  </td>
</tr>`;

// Add team
$('.table-add-team').on('click', 'i', () => {
    $('#tableTeams tbody').append(newTrTeam);
});

// Remove team
$tableTeamsID.on('click', '.table-remove-team', function () {

    const resource = mainResource + 'Teams/';

    const headings = ['name', 'location', 'project'];

    var obj = {};

    for (let i = 0; i < headings.length; i++) {
        obj[headings[i]] = $(this).parents('tr')[0].cells[i].textContent;
    }

    const removeResource = resource + obj['name'];
    const removeData = JSON.stringify(obj);
    $.ajax({
        url: removeResource,
        type: 'DELETE',
        data: removeData,
        headers: {
            'Content-Type': 'application/json'
        }
    });

    $(this).parents('tr').detach();
});

// Move up
$tableTeamsID.on('click', '.table-up', function () {

    const $row = $(this).parents('tr');

    if ($row.index() === 0) {
        return;
    }

    $row.prev().before($row.get(0));
});

// Move down
$tableTeamsID.on('click', '.table-down', function () {
    const $row = $(this).parents('tr');
    $row.next().after($row.get(0));
});

// Load teams
$tabTeams.on('click', function () {

    const resource = mainResource + 'Teams/';

    $.ajax({
        url: resource,
        type: 'GET',
    }).done(function (data, textStatus, jqXHR) {

        // Fill table
        const bodyRef = '#dataTeams > tbody';
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
                    <td class='pt-3-half' contenteditable='true'>` + data[i]['name'] + `</td>
                    <td class='pt-3-half' contenteditable='true'>` + data[i]['location'] + `</td>
                    <td class='pt-3-half' contenteditable='true'>` + data[i]['project'] + `</td>
                    <td class='pt-3-half'>
                        <span class='table-up'><a href='#!' class='indigo-text'><i class='fas fa-long-arrow-alt-up' aria-hidden='true'></i></a></span>
                        <span class='table-down'><a href='#!' class='indigo-text'><i class='fas fa-long-arrow-alt-down' aria-hidden='true'></i></a></span>
                    </td>
                    <td>
                        <span class='table-remove-team'><button type='button' class='btn btn-danger btn-rounded btn-sm my-0 waves-effect waves-light'>Remove</button></span>
                    </td>
                </tr>`;
        }
        $(tableBody).append(rows);
    });

});

// Update teams
$('.table-update-teams').on('click', 'i', () => {

    const resource = mainResource + 'Teams/';

    const bodyRef = '#dataTeams > tbody';
    const tableBody = document.querySelector(bodyRef);

    const headings = ['name', 'location', 'project'];

    for (let i = 0, row; row = tableBody.rows[i]; i++) {

        var obj = {};

        for (let j = 0; j < headings.length; j++) {
            obj[headings[j]] = row.cells[j].textContent;
        }

        // Update data
        const putResource = resource + obj['name'];
        const putData = JSON.stringify(obj);
        $.ajax({
            url: putResource,
            type: 'PUT',
            data: putData,
            headers: {
                'Content-Type': 'application/json'
            }
        });

    }

    alert("Succesfully saved!");

});