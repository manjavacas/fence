
const $tableCommunicationsID = $('#tableCommunications');
const $tabCommunications = $('#tabCommunications');

const newTrComm = `
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
</tr>`;

// Move up
$tableCommunicationsID.on('click', '.table-up', function () {

    const $row = $(this).parents('tr');

    if ($row.index() === 0) {
        return;
    }

    $row.prev().before($row.get(0));
});

// Move down
$tableCommunicationsID.on('click', '.table-down', function () {
    const $row = $(this).parents('tr');
    $row.next().after($row.get(0));
});

// Load communications
$tabCommunications.on('click', function () {

    const resource = mainResource + 'Communications/';

    $.ajax({
        url: resource,
        type: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    }).done(function (data, textStatus, jqXHR) {

        // Fill table
        const bodyRef = '#dataCommunications > tbody';
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
                    <td class='pt-3-half' contenteditable='true'>` + data[i]['user1'] + `</td>
                    <td class='pt-3-half' contenteditable='true'>` + data[i]['user2'] + `</td>
                    <td class='pt-3-half' contenteditable='true'>` + data[i]['duration'] + `</td>
                    <td class='pt-3-half' contenteditable='true'>` + data[i]['date'] + `</td>
                    <td class='pt-3-half' contenteditable='true'>` + data[i]['quality'] + `</td>
                    <td class='pt-3-half'>
                        <span class='table-up'><a href='#!' class='indigo-text'><i class='fas fa-long-arrow-alt-up' aria-hidden='true'></i></a></span>
                        <span class='table-down'><a href='#!' class='indigo-text'><i class='fas fa-long-arrow-alt-down' aria-hidden='true'></i></a></span>
                    </td>
                </tr>`;
        }
        $(tableBody).append(rows);
    });

});