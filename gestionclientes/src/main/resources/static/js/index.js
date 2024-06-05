async function loadClientsData() {
  const mainContainer = document.querySelector(".main-container");
  try {    
    const response = await fetch("data-clients.html");
    const html = await response.text();
    mainContainer.innerHTML = html;
  } catch (error) {
    console.error("Error loading create client form:", error);
  }
  let tbody = document.getElementById('tbody-clients');
    tbody.innerHTML = await renderClients();
    idClient = 0;
}

async function renderClients() {
  let clients = await getClients();
  let htmlClients = "";
  for (let client of clients) {
    htmlClients += getHtmlRowClient(client);
  }
    return htmlClients;
}

async function getClients() {
  let url = config_url + "/clients";
  let config = {
    method: "GET",
    headers: {
      "Content-Type": "application/json"
    },
  };
  let response = await fetch(url, config);
  let json = await response.json();
  return json;
}

function getHtmlRowClient(client) {
  return `<tr>
      <td>${client.id}</td>
      <td>${client.firstName}</td>
      <td>${client.lastName}</td>
      <td>${client.email}</td>
      <td>${client.phone}</td>
      <td>${client.address}</td>
      <td>
        <a href="#" onClick="onClickUpdate(${client.id})" class="btn btn-primary">
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil" viewBox="0 0 16 16">
        <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325"/>
      </svg>
        </a>
        <a href="#" onClick="onClickRemove(${client.id})" class="btn btn-danger">
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash-fill" viewBox="0 0 16 16">
        <path d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5M8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5m3 .5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 1 0"/>
      </svg>
        </a>
      </td>
    </tr>`;
}

async function onClickRemove(id){
    let response = confirm("Do you want remove this?")
    if(!response){
        return;
    }
    let url = config_url + '/client/' + id;
    let config = {
        method: 'DELETE',
        headers: {
          "Content-Type": "application/json",          
        }
    };    
    await fetch(url, config);
    loadClientsData();
}