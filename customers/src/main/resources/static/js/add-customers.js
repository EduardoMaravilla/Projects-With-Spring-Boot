function clickCreate(){
    let name = document.getElementById('exampleName').value;
    let email = document.getElementById('exampleEmail').value;
    let phone = document.getElementById('exampleTel').value;
    let address = document.getElementById('exampleAddress').value;
    let customer = {
        "name" : name,
        "email": email,
        "phone": phone,
        "address": address
    }
    saveCustomer(customer);
}

async function saveCustomer(customer){
    let urlComplement = '';
    let methodType = '';
    if(isNewCustomer()){
       urlComplement = '/customer';
       methodType = 'POST'
    }else {
        customer.id = getCustomerId();
        urlComplement = '/customer/' + customer.id;
         methodType = 'PUT'
    }

    let url = URL_SERVER + urlComplement;
    let config = {
        "method": methodType,
        "body": JSON.stringify(customer),
        "headers": {
            'Content-Type': 'application/json',            
            "Authorization": sessionStorage.token
        }
    };    
    await fetch(url, config);
    alert("Customer saved correctly")
    window.location.href = 'customers.html'
}

async function loadCustomer(){
    if(isNewCustomer()){
        document.getElementById('nameFormCustomer').innerHTML='Add Customer';
        document.getElementById('buttonAddCustomer').innerHTML='Register Customer';
        return;
    }    
    let customer = await getCustomerById(getCustomerId());
    document.getElementById('nameFormCustomer').innerHTML='Edit Customer';
    document.getElementById('buttonAddCustomer').innerHTML='Update Customer';
    document.getElementById('exampleName').value = customer.name;
    document.getElementById('exampleEmail').value=customer.email;
    document.getElementById('exampleTel').value=customer.phone;
    document.getElementById('exampleAddress').value=customer.address;
}

function getCustomerId(){
    let auxPlit = window.location.href.split('id=');
    return auxPlit[1];
}

async function getCustomerById(id) {
    let url = URL_SERVER + '/customer/'+id;
    let config = {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          "Authorization": sessionStorage.token
        }
      }
    let response = await fetch(url,config);
    let json = await response.json();
  return json;
}

function isNewCustomer(){
    return !window.location.href.includes('id=')
}

loadCustomer();