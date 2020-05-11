
const $tableTasksID = $('#tableTasks');
const $tabTasks = $('#tabTasks');

const newTrTask = `
<tr class='hide'>
  <td class='pt-3-half' contenteditable='true'></td>
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
    <span class='table-remove-task'><button type='button' class='btn btn-danger btn-rounded btn-sm my-0 waves-effect waves-light'>Remove</button></span>
  </td>
</tr>`;

// Add task
$('.table-add-task').on('click', 'i', () => {
    $('#tableTasks tbody').append(newTrTask);
});

// Remove task
$tableTasksID.on('click', '.table-remove-task', function () {

    const resource = mainResource + 'Tasks/';

    const headings = ['reference', 'description', 'duration_days', 'priority', 'done', 'project'];

    var obj = {};

    for (let i = 0; i < headings.length; i++) {
        obj[headings[i]] = $(this).parents('tr')[0].cells[i].textContent;
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
$tableTasksID.on('click', '.table-up', function () {

    const $row = $(this).parents('tr');

    if ($row.index() === 0) {
        return;
    }

    $row.prev().before($row.get(0));
});

// Move down
$tableTasksID.on('click', '.table-down', function () {
    const $row = $(this).parents('tr');
    $row.next().after($row.get(0));
});

// Load tasks
$tabTasks.on('click', function () {

    const resource = mainResource + 'Tasks/';

    $.ajax({
        url: resource,
        type: 'GET',
    }).done(function (data, textStatus, jqXHR) {

        // Fill table
        const bodyRef = '#dataTasks > tbody';
        const tableBody = document.querySelector(bodyRef);

        // Clear table
        while (tableBody.firstChild) {
            tableBody.removeChild(tableBody.firstChild);
        }

        var rows = '';

        // Load records
        for (let i = 0; i < data.length; i++) {

            if (data[i]['done'] == 'true') {
                doneText = 'YES';
            } else {
                doneText = 'NO';
            }

            rows +=
                `<tr class='hide'>
                    <td class='pt-3-half' contenteditable='true'>` + data[i]['reference'] + `</td>
                    <td class='pt-3-half' contenteditable='true'>` + data[i]['description'] + `</td>
                    <td class='pt-3-half' contenteditable='true'>` + data[i]['duration_days'] + `</td>
                    <td class='pt-3-half' contenteditable='true'>` + data[i]['priority'] + `</td>
                    <td class='pt-3-half' contenteditable='true'>` + doneText + `</td>
                    <td class='pt-3-half' contenteditable='true'>` + data[i]['project'] + `</td>
                    <td class='pt-3-half'>
                        <span class='table-up'><a href='#!' class='indigo-text'><i class='fas fa-long-arrow-alt-up' aria-hidden='true'></i></a></span>
                        <span class='table-down'><a href='#!' class='indigo-text'><i class='fas fa-long-arrow-alt-down' aria-hidden='true'></i></a></span>
                    </td>
                    <td>
                        <span class='table-remove-task'><button type='button' class='btn btn-danger btn-rounded btn-sm my-0 waves-effect waves-light'>Remove</button></span>
                    </td>
                </tr>`;
        }
        $(tableBody).append(rows);
    });

});

// Update tasks
$('.table-update-tasks').on('click', 'i', () => {

    const resource = mainResource + 'Tasks/';

    const bodyRef = '#dataTasks > tbody';
    const tableBody = document.querySelector(bodyRef);

    const headings = ['reference', 'description', 'duration_days', 'priority', 'done', 'project'];

    for (let i = 0, row; row = tableBody.rows[i]; i++) {

        var obj = {};

        for (let j = 0; j < headings.length; j++) {
            if (headings[j] == 'done') {
                if (row.cells[j].textContent == 'YES') {
                    obj[headings[j]] = 'true';
                } else {
                    obj[headings[j]] = 'false';
                }
            } else {
                obj[headings[j]] = row.cells[j].textContent;
            }
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