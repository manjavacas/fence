
/* Source: https://mdbootstrap.com/docs/jquery/tables/editable/#! */

const $tableProjectsID = $('#tableProjects');
const $BTNProjects = $('#export-btn-Projects');
const $EXPORTProjects = $('#export-Projects');

const newTrProj = `
<tr class="hide">
  <td class="pt-3-half" contenteditable="true">Example</td>
  <td class="pt-3-half" contenteditable="true">Example</td>
  <td class="pt-3-half">
    <span class="table-up"><a href="#!" class="indigo-text"><i class="fas fa-long-arrow-alt-up" aria-hidden="true"></i></a></span>
    <span class="table-down"><a href="#!" class="indigo-text"><i class="fas fa-long-arrow-alt-down" aria-hidden="true"></i></a></span>
  </td>
  <td>
    <span class="table-remove-project"><button type="button" class="btn btn-danger btn-rounded btn-sm my-0 waves-effect waves-light">Remove</button></span>
  </td>
</tr>`;

$('.table-add-project').on('click', 'i', () => {

    const $clone = $tableProjectsID.find('tbody tr').last().clone(true).removeClass('hide table-line');

    if ($tableProjectsID.find('tbody tr').length === 0) {

        $('tbody').append(newTrProj);
    }

    $tableProjectsID.find('table').append($clone);
});

$tableProjectsID.on('click', '.table-remove-project', function () {

    $(this).parents('tr').detach();
});

$tableProjectsID.on('click', '.table-up', function () {

    const $row = $(this).parents('tr');

    if ($row.index() === 1) {
        return;
    }

    $row.prev().before($row.get(0));
});

$tableProjectsID.on('click', '.table-down', function () {

    const $row = $(this).parents('tr');
    $row.next().after($row.get(0));
});

// A few jQuery helpers for exporting only
jQuery.fn.pop = [].pop;
jQuery.fn.shift = [].shift;

$BTNProjects.on('click', () => {

    const $rows = $tableProjectsID.find('tr:not(:hidden)');
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
    $EXPORTProjects.text(JSON.stringify(data));
});