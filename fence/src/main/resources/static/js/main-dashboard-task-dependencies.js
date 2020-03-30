
const $tableTaskDependenciesID = $('#tableTaskDependencies');
const $tabTaskDependencies = $('#tabTaskDependencies');

const newTrTaskDependencies = `
<tr class='hide'>
  <td class='pt-3-half' contenteditable='true'></td>
  <td class='pt-3-half' contenteditable='true'></td>
  <td class='pt-3-half' contenteditable='true'></td>
  <td class='pt-3-half' contenteditable='true'></td>
  <td class='pt-3-half' contenteditable='true'></td>
  <td class='pt-3-half'>
    <span class='table-up'><a href='#!' class='indigo-text'><i class='fas fa-long-arrow-alt-up' aria-hidden='true'></i></a></span>
    <span class='table-down'><a href='#!' class='indigo-text'><i class='fas fa-long-arrow-alt-down' aria-hidden='true'></i></a></span>
  </td>
  <td>
    <span class='table-remove-taskDependency'><button type='button' class='btn btn-danger btn-rounded btn-sm my-0 waves-effect waves-light'>Remove</button></span>
  </td>
</tr>`;

// Add taskDependencies
$('.table-add-taskDependency').on('click', 'i', () => {
    $('#tableTaskDependencies tbody').append(newTrTaskDependencies);
});

// Remove task dependency
$tableTaskDependenciesID.on('click', '.table-remove-taskDependency', function () {

    const resource = mainResource + 'TaskDependencies/';

    const headings = ['reference', 'task1', 'task2', 'project', 'weight'];

    var obj = {};

    for (let i = 0; i < headings.length; i++) {
        obj[headings[i]] = $(this).parents('tr')[0].cells[i].innerText;
    }

    const removeResource = resource + obj['reference'];
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
$tableTaskDependenciesID.on('click', '.table-up', function () {

    const $row = $(this).parents('tr');

    if ($row.index() === 0) {
        return;
    }

    $row.prev().before($row.get(0));
});

// Move down
$tableTaskDependenciesID.on('click', '.table-down', function () {
    const $row = $(this).parents('tr');
    $row.next().after($row.get(0));
});

// Load task dependencies
$tabTaskDependencies.on('click', function () {

    const resource = mainResource + 'TaskDependencies/';

    $.ajax({
        url: resource,
        type: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    }).done(function (data, textStatus, jqXHR) {

        // Fill table
        const bodyRef = '#dataTaskDependencies > tbody';
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
                    <td class='pt-3-half' contenteditable='true'>` + data[i]['reference'] + `</td>
                    <td class='pt-3-half' contenteditable='true'>` + data[i]['task1'] + `</td>
                    <td class='pt-3-half' contenteditable='true'>` + data[i]['task2'] + `</td>
                    <td class='pt-3-half' contenteditable='true'>` + data[i]['project'] + `</td>
                    <td class='pt-3-half' contenteditable='true'>` + data[i]['weight'] + `</td>
                    <td class='pt-3-half'>
                        <span class='table-up'><a href='#!' class='indigo-text'><i class='fas fa-long-arrow-alt-up' aria-hidden='true'></i></a></span>
                        <span class='table-down'><a href='#!' class='indigo-text'><i class='fas fa-long-arrow-alt-down' aria-hidden='true'></i></a></span>
                    </td>
                    <td>
                        <span class='table-remove-taskDependency'><button type='button' class='btn btn-danger btn-rounded btn-sm my-0 waves-effect waves-light'>Remove</button></span>
                    </td>
                </tr>`;
        }
        $(tableBody).append(rows);
    });

});

// Update task dependencies
$('.table-update-taskDependency').on('click', 'i', () => {

    const resource = mainResource + 'TaskDependencies/';

    const bodyRef = '#dataTaskDependencies > tbody';
    const tableBody = document.querySelector(bodyRef);

    const headings = ['reference', 'task1', 'task2', 'project', 'weight'];

    for (let i = 0, row; row = tableBody.rows[i]; i++) {

        var obj = {};

        for (let j = 0; j < headings.length; j++) {
            obj[headings[j]] = row.cells[j].innerText;
        }

        // Update data
        const putResource = resource + obj['reference'];
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