
const $tableProjectsID = $('#tableProjects');
const $tabProjects = $('#tabProjects');

const newTrProj = `
<tr class='hide'>
  <td class='pt-3-half' contenteditable='true'></td>
  <td class='pt-3-half' contenteditable='true'></td>
  <td class='pt-3-half'>
    <span class='table-up'><a href='#!' class='indigo-text'><i class='fas fa-long-arrow-alt-up' aria-hidden='true'></i></a></span>
    <span class='table-down'><a href='#!' class='indigo-text'><i class='fas fa-long-arrow-alt-down' aria-hidden='true'></i></a></span>
  </td>
  <td>
    <span class='table-remove-project'><button type='button' class='btn btn-danger btn-rounded btn-sm my-0 waves-effect waves-light'>Remove</button></span>
  </td>
</tr>`;

// Add project
$('.table-add-project').on('click', 'i', () => {
    $('#tableProjects tbody').append(newTrProj);
});

// Remove project
$tableProjectsID.on('click', '.table-remove-project', function () {

    const resource = mainResource + 'Projects/';

    const headings = ['name', 'description'];

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
$tableProjectsID.on('click', '.table-up', function () {

    const $row = $(this).parents('tr');

    if ($row.index() === 0) {
        return;
    }

    $row.prev().before($row.get(0));
});

// Move down
$tableProjectsID.on('click', '.table-down', function () {
    const $row = $(this).parents('tr');
    $row.next().after($row.get(0));
});

// Load projects
$tabProjects.on('click', function () {

    const resource = mainResource + 'Projects/';

    $.ajax({
        url: resource,
        type: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    }).done(function (data, textStatus, jqXHR) {

        // Fill table
        const bodyRef = '#dataProjects > tbody';
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
                    <td class='pt-3-half' contenteditable='true'>` + data[i]['description'] + `</td>
                    <td class='pt-3-half'>
                        <span class='table-up'><a href='#!' class='indigo-text'><i class='fas fa-long-arrow-alt-up' aria-hidden='true'></i></a></span>
                        <span class='table-down'><a href='#!' class='indigo-text'><i class='fas fa-long-arrow-alt-down' aria-hidden='true'></i></a></span>
                    </td>
                    <td>
                        <span class='table-remove-project'><button type='button' class='btn btn-danger btn-rounded btn-sm my-0 waves-effect waves-light'>Remove</button></span>
                    </td>
                </tr>`;
        }
        $(tableBody).append(rows);
    });

});

// Update projects
$('.table-update-projects').on('click', 'i', () => {

    const resource = mainResource + 'Projects/';

    const bodyRef = '#dataProjects > tbody';
    const tableBody = document.querySelector(bodyRef);

    const headings = ['name', 'description'];

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