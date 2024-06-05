let idClient=0;
async function loadCreateClientForm() {
  const mainContainer = document.querySelector(".main-container");
  try {
    const response = await fetch("add-client.html");
    const html = await response.text();
    mainContainer.innerHTML = html;
  } catch (error) {
    console.error("Error loading create client form:", error);
  }
  if (isNewClient()) {
    document.getElementById("create-client-nav").innerHTML = "Create Client";
    document.getElementById("create-client-link").innerHTML = "Add Client";
    document.getElementById("createClientButton").innerHTML = "Register Client";
    return;
  }
  let client = await getClientById(getClientId());
  document.getElementById("create-client-nav").innerHTML = "Edit Client";
  document.getElementById("create-client-link").innerHTML = "Modify Client";
  document.getElementById("createClientButton").innerHTML = "Update Client";
  document.getElementById("firstName").value = client.firstName;
  document.getElementById("lastName").value = client.lastName;
  document.getElementById("email").value = client.email;
  document.getElementById("phone").value = client.phone;
  document.getElementById("address").value = client.address;
}

async function createNewClient() {
  let firstName = document.getElementById("firstName").value;
  let lastName = document.getElementById("lastName").value;
  let email = document.getElementById("email").value;
  let phone = document.getElementById("phone").value;
  let address = document.getElementById("address").value;

  let newClient = {
    firstName: firstName,
    lastName: lastName,
    email: email,
    phone: phone,
    address: address,
  };
  saveClient(newClient);
}

async function saveClient(client) {
  let urlComplement = "";
  let methodType = "";
  if (isNewClient()) {
    urlComplement = "/client";
    methodType = "POST";
  } else {
    client.id = getClientId();
    urlComplement = "/client/" + client.id;
    methodType = "PUT";
  }
  let url = config_url + urlComplement;
  let config = {
    method: methodType,
    body: JSON.stringify(client),
    headers: {
      "Content-Type": "application/json"
    }
  };
  await fetch(url, config);
  alert("Client saved correctly");
  idClient = 0;
  loadClientsData();
  document.getElementById("create-client-nav").innerHTML = "Create Client";
}

function getClientId() {
  return idClient;
}

function isNewClient() {
  return idClient == 0;
}

async function getClientById(id) {
  let url = config_url + "/client/" + id;
  let config = {
    method: "GET",
    headers: {
      "Content-Type": "application/json"
    }
  };
  let response = await fetch(url, config);
  let json = await response.json();
  return json;
}

function onClickUpdate(id) {
  idClient = id;  
  loadCreateClientForm();
}
