
const $tableEmployeesID = $('#tableEmployees');
const $tabEmployees = $('#tabEmployees');

const mainResource = 'http://localhost:8080/';

const newTrEmp = `
<tr class='hide'>
  <td class='pt-3-half' contenteditable='true'></td>
  <td class='pt-3-half' contenteditable='true'></td>
  <td class='pt-3-half' contenteditable='true'></td>
  <td class='pt-3-half' contenteditable='true'></td>
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
    <span class='table-remove-employee'><button type='button' class='btn btn-danger btn-rounded btn-sm my-0 waves-effect waves-light'>Remove</button></span>
  </td>
</tr>`;

// Add employee
$('.table-add-employee').on('click', 'i', () => {
  $('#tableEmployees tbody').append(newTrEmp);
});

// Remove employee
$tableEmployeesID.on('click', '.table-remove-employee', function () {

  const resource = mainResource + 'Employees/';

  const headings = ['dni', 'name', 'email', 'age',
    'role', 'timezone', 'country', 'englishLevel', 'experience', 'team'];

  var obj = {};

  for (let i = 0; i < headings.length; i++) {
    obj[headings[i]] = $(this).parents('tr')[0].cells[i].textContent;
  }

  const removeResource = resource + obj['dni'];
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
$tableEmployeesID.on('click', '.table-up', function () {

  const $row = $(this).parents('tr');

  if ($row.index() === 0) {
    return;
  }

  $row.prev().before($row.get(0));
});

// Move down
$tableEmployeesID.on('click', '.table-down', function () {
  const $row = $(this).parents('tr');
  $row.next().after($row.get(0));
});

// Load employees
$tabEmployees.on('click', function () {

  const resource = mainResource + 'Employees/';

  $.ajax({
    url: resource,
    type: 'GET',
  }).done(function (data, textStatus, jqXHR) {

    // Fill table
    const bodyRef = '#dataEmployees > tbody';
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
          <td class='pt-3-half' contenteditable='true'>` + data[i]['dni'] + `</td>
          <td class='pt-3-half' contenteditable='true'>` + data[i]['name'] + `</td>
          <td class='pt-3-half' contenteditable='true'>` + data[i]['email'] + `</td>
          <td class='pt-3-half' contenteditable='true'>` + data[i]['age'] + `</td>
          <td class='pt-3-half' contenteditable='true'>` + data[i]['role'] + `</td>
          <td class='pt-3-half' contenteditable='true'>` + data[i]['timezone'] + `</td>
          <td class='pt-3-half' contenteditable='true'>` + data[i]['country'] + `</td>
          <td class='pt-3-half' contenteditable='true'>` + data[i]['englishLevel'] + `</td>
          <td class='pt-3-half' contenteditable='true'>` + data[i]['experience'] + `</td>
          <td class='pt-3-half' contenteditable='true'>` + data[i]['team'] + `</td>
          <td class='pt-3-half'>
              <span class='table-up'><a href='#!' class='indigo-text'><i class='fas fa-long-arrow-alt-up' aria-hidden='true'></i></a></span>
              <span class='table-down'><a href='#!' class='indigo-text'><i class='fas fa-long-arrow-alt-down' aria-hidden='true'></i></a></span>
          </td>
          <td>
              <span class='table-remove-employee'><button type='button' class='btn btn-danger btn-rounded btn-sm my-0 waves-effect waves-light'>Remove</button></span>
          </td>
        </tr>`;
    }
    $(tableBody).append(rows);
  });

});

// Update employees
$('.table-update-employees').on('click', 'i', () => {

  const resource = mainResource + 'Employees/';

  const bodyRef = '#dataEmployees > tbody';
  const tableBody = document.querySelector(bodyRef);

  const headings = ['dni', 'name', 'email', 'age',
    'role', 'timezone', 'country', 'englishLevel', 'experience', 'team'];

  for (let i = 0, row; row = tableBody.rows[i]; i++) {

    var obj = {};

    for (let j = 0; j < headings.length; j++) {
      obj[headings[j]] = row.cells[j].textContent;
    }

    // Update data
    const putResource = resource + obj['dni'];
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