
/* Source: https://mdbootstrap.com/docs/jquery/tables/editable/#! */

const $tableEmployeesID = $('#tableEmployees');
const $BTNEmployees = $('#export-btn-Employees');
const $EXPORTEmployees = $('#export-Employees');

const newTrEmp = `
<tr class="hide">
  <td class="pt-3-half" contenteditable="true">Example</td>
  <td class="pt-3-half" contenteditable="true">Example</td>
  <td class="pt-3-half" contenteditable="true">Example</td>
  <td class="pt-3-half" contenteditable="true">Example</td>
  <td class="pt-3-half" contenteditable="true">Example</td>
  <td class="pt-3-half">
    <span class="table-up"><a href="#!" class="indigo-text"><i class="fas fa-long-arrow-alt-up" aria-hidden="true"></i></a></span>
    <span class="table-down"><a href="#!" class="indigo-text"><i class="fas fa-long-arrow-alt-down" aria-hidden="true"></i></a></span>
  </td>
  <td>
    <span class="table-remove-employee"><button type="button" class="btn btn-danger btn-rounded btn-sm my-0 waves-effect waves-light">Remove</button></span>
  </td>
</tr>`;

$('.table-add-employee').on('click', 'i', () => {

    const $clone = $tableEmployeesID.find('tbody tr').last().clone(true).removeClass('hide table-line');

    if ($tableEmployeesID.find('tbody tr').length === 0) {

        $('#tableEmployees tbody').append(newTrEmp);
    }

    $tableEmployeesID.find('table').append($clone);
});

$tableEmployeesID.on('click', '.table-remove-employee', function () {

    $(this).parents('tr').detach();
});

$tableEmployeesID.on('click', '.table-up', function () {

    const $row = $(this).parents('tr');

    if ($row.index() === 1) {
        return;
    }

    $row.prev().before($row.get(0));
});

$tableEmployeesID.on('click', '.table-down', function () {

    const $row = $(this).parents('tr');
    $row.next().after($row.get(0));
});

// A few jQuery helpers for exporting only
jQuery.fn.pop = [].pop;
jQuery.fn.shift = [].shift;

$BTNEmployees.on('click', () => {

    const $rows = $tableEmployeesID.find('tr:not(:hidden)');
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
    $EXPORTEmployees.text(JSON.stringify(data));
});