function init() {
    renderCustomers();
}

async function getCustomers() {
    let url = URL_SERVER + '/customers';
    let config = {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        "Authorization": sessionStorage.token
      }
    }
    let response = await fetch(url, config);
    let json = await response.json();
  return json;
}

async function renderCustomers(){
    let customers = await getCustomers();
    let htmlCustomers = '';
    for(let customer of customers){
       htmlCustomers += getHtmlRowCustomer(customer);
    }
    let tbody =document.getElementById('tbody-customers');
    tbody.innerHTML = htmlCustomers;
}

async function onClickRemove(id){
    let response = confirm("Do you want remove this?")
    if(!response){
        return;
    }
    let url = URL_SERVER + '/customer/' + id;
    let config = {
        method: 'DELETE',
        headers: {
          "Content-Type": "application/json",
          "Authorization": sessionStorage.token
        }
    };    
    await fetch(url, config);
    renderCustomers();
}

async function onClickUpdate(id){
    window.location.href = 'form-customers.html?id='+ id
}

function getHtmlRowCustomer(customer) {
  return ` <tr>
    <td>${customer.id}</td>
    <td>${customer.name}</td>
    <td>${customer.email}</td>
    <td>${customer.phone}</td>
    <td>${customer.address}</td>
    <td>
      <a href="#" onClick="onClickUpdate(${customer.id})" class="btn btn-primary btn-circle btn-sm">
        <i class="fas fa-edit"></i>
      </a>
      <a href="#" onClick="onClickRemove(${customer.id})" class="btn btn-danger btn-circle btn-sm">
        <i class="fas fa-trash"></i>
      </a>
    </td>
  </tr>`;
}

init();
