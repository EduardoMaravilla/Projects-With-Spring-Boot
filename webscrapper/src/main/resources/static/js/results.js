const URL_API='http://localhost:8080/api'

function onClickSearch(){
    let text = document.getElementById("txtSearch").value
    loadResults(text)   
}

async function loadResults(text){
    let url = URL_API + "/search?query=" + text    
    let result = await fetch(url)
    let jsonLoadExamples = await result.json()
    let html = ''
    for(let jsonElement of jsonLoadExamples){
       html += `<div id="resultSearch" class="bg-secondary text-white my-3 resultsSearchCSS" >
       <div class="row">         
             <img class="col-auto imgConfig2" src="${jsonElement.picture}"/>            
             <a class="col-auto my-2 mx-1" href="${jsonElement.url}" target="_blank"><p>${jsonElement.title}</p></a>
       </div>
       <div><p>${jsonElement.content}</p></div>
     </div>`    
    }
    document.getElementById("results").innerHTML=html
}

function load(){
    let query = window.location.href.split('?query=')[1]
    document.getElementById("txtSearch").value = query
    loadResults(query)
}

load();
