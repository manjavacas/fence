
/* Source: https://mdbootstrap.com/docs/jquery/tables/editable/#! */

const $tableTeamsID = $('#tableTeams');
const $BTNTeams = $('#export-btn-Teams');
const $EXPORTTeams = $('#export-Teams');

const newTrTeam = `
<tr class="hide">
  <td class="pt-3-half" contenteditable="true">Example</td>
  <td class="pt-3-half" contenteditable="true">Example</td>
  <td class="pt-3-half" contenteditable="true">Example</td>
  <td class="pt-3-half">
    <span class="table-up"><a href="#!" class="indigo-text"><i class="fas fa-long-arrow-alt-up" aria-hidden="true"></i></a></span>
    <span class="table-down"><a href="#!" class="indigo-text"><i class="fas fa-long-arrow-alt-down" aria-hidden="true"></i></a></span>
  </td>
  <td>
    <span class="table-remove-team"><button type="button" class="btn btn-danger btn-rounded btn-sm my-0 waves-effect waves-light">Remove</button></span>
  </td>
</tr>`;

$('.table-add-team').on('click', 'i', () => {

    const $clone = $tableTeamsID.find('tbody tr').last().clone(true).removeClass('hide table-line');

    if ($tableTeamsID.find('tbody tr').length === 0) {

        $('#tableTeams tbody').append(newTrTeam);
    }

    $tableTeamsID.find('table').append($clone);
});

$tableTeamsID.on('click', '.table-remove-team', function () {

    $(this).parents('tr').detach();
});

$tableTeamsID.on('click', '.table-up', function () {

    const $row = $(this).parents('tr');

    if ($row.index() === 1) {
        return;
    }

    $row.prev().before($row.get(0));
});

$tableTeamsID.on('click', '.table-down', function () {

    const $row = $(this).parents('tr');
    $row.next().after($row.get(0));
});

// A few jQuery helpers for exporting only
jQuery.fn.pop = [].pop;
jQuery.fn.shift = [].shift;

$BTNTeams.on('click', () => {

    const $rows = $tableTeamsID.find('tr:not(:hidden)');
    const headers = [];
    const data = [];

    // Get the headers (add special header logic here)
    $($rows.shift()).find('th:not(:empty)').each(function () {

        headers.push($(this).text().toLowerCase());
    });

    // Turn all existing rows into a loopable array
    $rows.each(function () {
        const $td = $(this).find('td');
        const h = {};

        // Use the headers from earlier to name our hash keys
        headers.forEach((header, i) => {

            h[header] = $td.eq(i).text();
        });

        data.push(h);
    });

    // Output the result
    $EXPORTTeams.text(JSON.stringify(data));
});